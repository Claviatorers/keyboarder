package admin.userAccounts;

import admin.Menu;
import client.userStatistic.StatisticView;
import common.DataBase;
import common.JsonFileHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserAccountsController {

    private Stage stage;

    private ObservableList<StatisticView> statisticsData = FXCollections.observableArrayList();

    private JsonFileHelper helper;
    @FXML
    ListView<String> users;
    @FXML
    TableView<StatisticView> statistics;

    @FXML
    private TableColumn<StatisticView, String> textColumn;

    @FXML
    private TableColumn<StatisticView, String> levelColumn;

    @FXML
    private TableColumn<StatisticView, Integer> timeColumn;

    @FXML
    public void initialize() {
        helper = JsonFileHelper.getInstance();
        users.setItems(FXCollections.observableArrayList());
        statisticsData = FXCollections.observableArrayList();
        textColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseText"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseDifficulty"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
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
