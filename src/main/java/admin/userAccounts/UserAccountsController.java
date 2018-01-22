package admin.userAccounts;

import admin.Menu;
import common.DataBase;
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

    private ObservableList<Statistic> statiticsData = FXCollections.observableArrayList();

    DataBase dataBase;
    @FXML
    ListView<String> users;
    @FXML
    TableView<Statistic> statistics;

    @FXML
    private TableColumn<Statistic, String> loginColumn;

    @FXML
    private TableColumn<Statistic, String> dateColumn;

    @FXML
    private TableColumn<Statistic, String> levelColumn;

    @FXML
    private TableColumn<Statistic, Integer> mistakeColumn;

    @FXML
    private TableColumn<Statistic, Integer> timeColumn;

    @FXML
    private TableColumn<Statistic, Integer> scoreColumn;

    @FXML
    public void initialize(){

        loginColumn.setCellValueFactory(new PropertyValueFactory<Statistic, String>("login"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Statistic, String>("date"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<Statistic, String>("level"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Statistic, Integer>("time"));
        mistakeColumn.setCellValueFactory(new PropertyValueFactory<Statistic, Integer>("mistake"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Statistic, Integer>("score"));
        setUsers();
    }

    public void setUsers(){
        dataBase = new DataBase();
        users.setItems(dataBase.getUsers());
        statiticsData = dataBase.getStatistics();
        statistics.setItems(statiticsData);
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
        loginColumn.setVisible(true);
    }

    public void chooseUser(MouseEvent mouseEvent) {
        dataBase = new DataBase();
        statiticsData = dataBase.getUserStatistics(users.getSelectionModel().getSelectedItem());
        statistics.setItems(statiticsData);
        loginColumn.setVisible(false);
    }
}
