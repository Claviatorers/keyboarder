package admin.levelSet;

import admin.Menu;
import common.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class LevelSetController {
    private Stage stage;
    DataBase dataBase;
    @FXML
    ComboBox<String> level;
    @FXML
    Spinner<Double> pressTime;
    @FXML
    Spinner<Integer> maxLength;
    @FXML
    Spinner<Integer> mistakePercent;

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }

    public void setValues(){
        level.getItems().addAll("Начальный", "Легкий", "Средний", "Сложный", "Очень сложный");
        level.setValue("Начальный");
        SpinnerValueFactory<Double> timeFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.2, 2, 0.2, 0.1);
        SpinnerValueFactory<Integer> lengthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(15,150,15);
        SpinnerValueFactory<Integer> mistakeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        pressTime.setValueFactory(timeFactory);
        maxLength.setValueFactory(lengthFactory);
        mistakePercent.setValueFactory(mistakeFactory);
        getValues();
    }

    public void getValues(){
        dataBase = new DataBase();
        String curLevel = level.getValue();
        String[] sets = dataBase.getLevelSets(curLevel);
        pressTime.getValueFactory().setValue(Double.parseDouble(sets[0]));
        maxLength.getValueFactory().setValue(Integer.parseInt(sets[1]));
        mistakePercent.getValueFactory().setValue(Integer.parseInt(sets[2]));
    }


    public void changeLevel(ActionEvent actionEvent) {
        getValues();
    }

    public void cancel(ActionEvent actionEvent) throws Exception {
        stage.hide();
    }

    public void save(ActionEvent actionEvent) throws Exception {
        dataBase = new DataBase();
        dataBase.setLevelSets(this.level.getValue(), pressTime.getValue(), maxLength.getValue(), mistakePercent.getValue());
        stage.hide();
    }
}
