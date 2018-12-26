package admin.userAccounts;

import admin.Menu;
import client.userStatistic.StatisticView;
import common.JsonFileHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class UserAccountsController {


    private Stage stage;

    private ObservableList<StatisticView> statisticsData = FXCollections.observableArrayList();

    private JsonFileHelper helper;
    @FXML
    ListView<String> users;
    @FXML
    TableView<StatisticView> statistics;

    @FXML
    private TableColumn<StatisticView, Double> mistakesColumn;

    @FXML
    private TableColumn<StatisticView, Integer> avgCharsPerMinuteColumn;

    @FXML
    private TableColumn<StatisticView, String> textColumn;

    @FXML
    private TableColumn<StatisticView, String> levelColumn;

    @FXML
    private TableColumn<StatisticView, Integer> timeColumn;

    @FXML
    public void initialize() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        helper = JsonFileHelper.getInstance();
        users.setItems(FXCollections.observableArrayList());
        statisticsData = FXCollections.observableArrayList();
        textColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseText"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseDifficulty"));
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
        setUsers();
    }

    public void setUsers() {
        users.getItems().clear();
        users.getItems().addAll(helper.getUsersLogins());

        statisticsData.clear();
        statisticsData.addAll(helper.getShareStatistic());
        statistics.setItems(statisticsData);
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }

    public void allStatistic(ActionEvent actionEvent) {
        setUsers();
    }

    public void chooseUser(MouseEvent mouseEvent) {
        statisticsData.clear();
        statisticsData.addAll(helper.getUserStatisticWithExerciseNames(users.getSelectionModel().getSelectedItem()));
        statistics.setItems(statisticsData);
    }
}
