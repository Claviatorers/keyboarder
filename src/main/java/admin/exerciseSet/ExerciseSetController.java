package admin.exerciseSet;

import admin.Menu;
import common.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void initialize(){
        level.getItems().addAll("Начальный", "Легкий", "Средний", "Сложный", "Очень сложный");
        level.setValue("Начальный");
        setExercises();
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
    }
}
