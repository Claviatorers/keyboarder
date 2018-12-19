package admin.levelSet;

import admin.Menu;
import client.DifficultyLevel;
import common.JsonFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import model.Difficulty;

public class LevelSetController {
    private Stage stage;
    private JsonFileHelper helper;
    @FXML
    private ComboBox<String> level;
    @FXML
    Spinner<Double> pressTime;
    @FXML
    Spinner<Integer> maxLength;
    @FXML
    Spinner<Integer> mistakePercent;

    @FXML
    public void initialize(){
        helper = JsonFileHelper.getInstance();
        level.getItems().addAll("Легкий", "Средний", "Сложный");
        level.setValue("Легкий");
        SpinnerValueFactory<Double> timeFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.2, 2, 0.2, 0.1);
        SpinnerValueFactory<Integer> lengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(15,150,15);
        SpinnerValueFactory<Integer> mistakeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        pressTime.setValueFactory(timeFactory);
        maxLength.setValueFactory(lengthFactory);
        mistakePercent.setValueFactory(mistakeFactory);
        pressTime.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*\\,?\\d*")) {
                pressTime.getEditor().textProperty().setValue(oldValue);
            }
        });
        maxLength.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")) {
                maxLength.getEditor().textProperty().setValue(oldValue);
            }
        });
        mistakePercent.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")) {
                mistakePercent.getEditor().textProperty().setValue(oldValue);
            }
        });
    }


    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }

    public void setValues(){

        getValues();
    }

    public void getValues(){
        String curLevel = level.getValue();
        Difficulty difficulty = helper.getDifficultyByLevel(DifficultyLevel.getLevelByName(curLevel));
        pressTime.getValueFactory().setValue(difficulty.getKeyPressTime());
        maxLength.getValueFactory().setValue(difficulty.getMaxExerciseLength());
        mistakePercent.getValueFactory().setValue(difficulty.getMaxMistakesInPercent());
    }


    public void changeLevel(ActionEvent actionEvent) {
        getValues();
    }

    public void cancel(ActionEvent actionEvent) throws Exception {
        stage.hide();
    }

    public void save(ActionEvent actionEvent) throws Exception {
        helper.updateDifficulty(DifficultyLevel.getLevelByName(level.getValue()), pressTime.getValue(), maxLength.getValue(), mistakePercent.getValue());
        stage.hide();
    }
}
