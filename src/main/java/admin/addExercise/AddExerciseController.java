package admin.addExercise;

import admin.Menu;
import admin.exerciseSet.ExerciseSet;
import client.Exercise;
import common.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;


public class AddExerciseController {
    private Stage stage;
    private int levelNum;
    private int length;
    private String chars;
    @FXML
    private TextArea text;
    DataBase dataBase;

    @FXML
    public void initialize(){

    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void setLevelNum(int level){
        levelNum = level;
        dataBase = new DataBase();
        String[] sets = dataBase.getCharAndLength(levelNum);
        chars = sets[0] + " ";
        length = Integer.parseInt(sets[1]);
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("["+ chars +"]{0,"+ length +"}")) {
                text.textProperty().setValue(oldValue);
            }
        });
    }

    void close() throws Exception {
        stage.hide();
        ExerciseSet exerciseSet = new ExerciseSet();
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
        dataBase = new DataBase();
        dataBase.addExercise(levelNum, text.getText());
        this.close();

    }

    public void generate(ActionEvent actionEvent) {
        String generatedText = "";
        Random rnd = new Random(System.currentTimeMillis());
        while(generatedText.length() < length){
            int index = rnd.nextInt(chars.length());
            generatedText += chars.charAt(index);
        }
        text.setText(generatedText);
    }
}
