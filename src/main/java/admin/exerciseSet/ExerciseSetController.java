package admin.exerciseSet;

import admin.Menu;
import admin.addExercise.AddExercise;
import client.DifficultyLevel;
import common.JsonFileHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.ExerciseDao;

import java.util.List;

public class ExerciseSetController {
    private Stage stage;
    private JsonFileHelper helper;
    @FXML
    private ComboBox<String> level;
    @FXML
    ListView<ExerciseDao> exercises;
    @FXML
    Button editBut;
    @FXML
    Button deleteBut;

    @FXML
    public void initialize(){
        helper = JsonFileHelper.getInstance();
    }

    public void setLevel(String curLevel){
        level.getItems().addAll("Легкий", "Средний", "Сложный");
        level.setValue(curLevel);
        setExercises();
        if(exercises.getItems().size() == 0){
            editBut.setDisable(true);
            deleteBut.setDisable(true);
        }
        else{
            editBut.setDisable(false);
            deleteBut.setDisable(false);
        }
    }

    private void setExercises(){
        List<ExerciseDao> exerciseList = helper.getExercisesByLevel(DifficultyLevel.getLevelByName(level.getValue()));
        ObservableList<ExerciseDao> exercisesObservable = FXCollections.observableArrayList();
        exercisesObservable.addAll(exerciseList);

        exercises.setItems(exercisesObservable);
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }

    public void changeLevel(ActionEvent actionEvent) {
        setExercises();
        if(exercises.getItems().size() == 0){
            editBut.setDisable(true);
            deleteBut.setDisable(true);
        }
        else{
            editBut.setDisable(false);
            deleteBut.setDisable(false);
        }
    }

    public void add(ActionEvent actionEvent) throws Exception {
        stage.close();
        DifficultyLevel difficultyLevel = DifficultyLevel.getLevelByName(level.getValue());
        AddExercise addExercise = new AddExercise(difficultyLevel);
        addExercise.show();
    }

    public void delete(ActionEvent actionEvent) {
       int levelNum = level.getItems().indexOf(level.getValue()) + 1;
       String text = exercises.getSelectionModel().getSelectedItem().getText();
       if (text == null) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Ошибка!");
           alert.setHeaderText("Выберите упражнение");
           alert.showAndWait();
           return;
       }

       helper.deleteExerciseById(exercises.getSelectionModel().getSelectedItem().getId());
       setExercises();
        if(exercises.getItems().size() == 0){
            editBut.setDisable(true);
            deleteBut.setDisable(true);
        }
        else{
            editBut.setDisable(false);
            deleteBut.setDisable(false);
        }
    }

    public void edit(ActionEvent actionEvent) throws Exception {
        ExerciseDao chosenExercise = exercises.getSelectionModel().getSelectedItem();
        if (chosenExercise == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Выберите упражнение");
            alert.showAndWait();
            return;
        }
        stage.close();
        AddExercise addExercise = new AddExercise(chosenExercise);
        addExercise.show();
    }
}
