package client.userStatistic;

import client.menu.ClientMenu;
import common.JsonFileHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class UserStatisticController {
    private Stage stage;
    private String login;

    JsonFileHelper helper;
    @FXML
    ListView<String> users;
    @FXML
    TableView<StatisticView> statistics;

    @FXML
    private TableColumn<StatisticView, Integer> avgCharsPerMinuteColumn;

    @FXML
    private TableColumn<StatisticView, Double> mistakesColumn;

    @FXML
    private TableColumn<StatisticView, String> levelColumn;

    @FXML
    private TableColumn<StatisticView, Integer> timeColumn;

    @FXML
    private TableColumn<StatisticView, String> difficultyColumn;

    @FXML
    public void initialize(){
        DecimalFormat formatter = new DecimalFormat("#.##");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseDifficulty"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseText"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        mistakesColumn.setCellValueFactory(new PropertyValueFactory<>("averageMistakes"));
        mistakesColumn.setCellFactory(tc -> new TableCell<StatisticView, Double>() {
            @Override
            protected void updateItem(Double mistakes, boolean empty) {
                super.updateItem(mistakes, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(mistakes));
                }
            }
        });
        avgCharsPerMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("averageCharsPerMinute"));
    }

    void init(Stage stage) {
        this.stage = stage;
        helper = JsonFileHelper.getInstance();
        ObservableList<StatisticView> statisticsData = FXCollections.observableArrayList();

        List<StatisticView> userStatistic = helper.getUserStatisticWithExerciseNames(login);
        statisticsData.addAll(userStatistic);

        statistics.setItems(statisticsData);
    }

    void setLogin(String login){
        this.login = login;
    }

    void close() throws Exception {
        stage.hide();
        ClientMenu menu = new ClientMenu(login);
        menu.show();
    }
}
