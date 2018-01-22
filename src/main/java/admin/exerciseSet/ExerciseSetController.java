package admin.exerciseSet;

import admin.Menu;
import admin.addExercise.AddExercise;
import common.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ExerciseSetController {
    private Stage stage;
    DataBase dataBase;
    @FXML
    ComboBox<String> level;
    @FXML
    ListView<String> exercises;
    @FXML
    Button editBut;
    @FXML
    Button deleteBut;

    public void setLevel(String curLevel){
        level.getItems().addAll("Начальный", "Легкий", "Средний", "Сложный", "Очень сложный");
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

    public void setExercises(){
        dataBase = new DataBase();
        int levelNum = level.getItems().indexOf(level.getValue()) + 1;
        exercises.setItems(dataBase.getExercise(levelNum));
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
        int levelNum = level.getItems().indexOf(level.getValue()) + 1;
        AddExercise addExercise = new AddExercise(levelNum);
        addExercise.show();
    }

    public void delete(ActionEvent actionEvent) {
       int levelNum = level.getItems().indexOf(level.getValue()) + 1;
       String text = exercises.getSelectionModel().getSelectedItem();
       if (text == null) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Ошибка!");
           alert.setHeaderText("Выберите упражнение");
           alert.showAndWait();
           return;
       }
       dataBase = new DataBase();
       dataBase.deleteExercise(levelNum, text);
       exercises.setItems(dataBase.getExercise(levelNum));
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
        int levelNum = level.getItems().indexOf(level.getValue()) + 1;
        String text = exercises.getSelectionModel().getSelectedItem();
        if (text == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Выберите упражнение");
            alert.showAndWait();
            return;
        }
        stage.close();
        AddExercise addExercise = new AddExercise(levelNum, text);
        addExercise.show();
    }
}
