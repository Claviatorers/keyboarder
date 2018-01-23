package client.userStatistic;

import admin.Menu;
import admin.userAccounts.Statistic;
import client.menu.ClientMenu;
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

public class UserStatisticController {
    private Stage stage;
    private String login;

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

    }


    void init(Stage stage) {
        this.stage = stage;
        dataBase = new DataBase();
        statiticsData = dataBase.getUserStatistics(login);
        statistics.setItems(statiticsData);
        loginColumn.setVisible(false);
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
