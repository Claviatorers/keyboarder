package admin.addExercise;

import admin.exerciseSet.ExerciseSet;
import client.DifficultyLevel;
import common.JsonFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Difficulty;
import model.ExerciseDao;

import java.util.Random;


public class AddExerciseController {
    @FXML
    public TextArea charsField;

    private Stage stage;
    private ExerciseDao exerciseDao;
    private int length;
    private String chars;
    private JsonFileHelper helper;
    private boolean newEx = true;
    @FXML
    private TextArea text;


    @FXML
    public void initialize(){
        helper = JsonFileHelper.getInstance();
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void setExercise(ExerciseDao exercise){
        this.exerciseDao = exercise;
        Difficulty difficulty = helper.getDifficultyByLevel(exercise.getDifficulty());
        chars = difficulty.getAvailableChars() + " ";
        length = difficulty.getMaxExerciseLength();
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("["+ chars +"]{0,"+ length +"}")) {
                text.textProperty().setValue(oldValue);
            }
        });
        charsField.setText(chars);
        text.setText(exercise.getText());
    }

    void close() throws Exception {
        stage.hide();
        ExerciseSet exerciseSet = new ExerciseSet(exerciseDao.getDifficulty().toRussian());// todo
        exerciseSet.show();
    }

    public void save(ActionEvent actionEvent) throws Exception {
        if(text.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Введите текст упражнения");
            alert.showAndWait();
            return;
        }
        if(newEx) {
            helper.addExercie(text.getText(),exerciseDao.getDifficulty());
        }else {
            helper.updateExercise(exerciseDao.getId(), text.getText(), exerciseDao.getDifficulty());
        }
        this.close();

    }

    public void generate(ActionEvent actionEvent) {
        StringBuilder generatedText = new StringBuilder();
        Random rnd = new Random(System.currentTimeMillis());
        Random rndLegth = new Random(System.currentTimeMillis());
        int curLength = rndLegth.nextInt(length - 5) + 1 + 5;
        while(generatedText.length() < curLength){
            int index = rnd.nextInt(chars.length());
            generatedText.append(chars.charAt(index));
        }
        text.setText(generatedText.toString());
    }

    public void cancel(ActionEvent actionEvent) throws Exception {
        this.close();
    }
}
