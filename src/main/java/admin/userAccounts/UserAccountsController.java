package admin.userAccounts;

import admin.Menu;
import common.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class UserAccountsController {
    private Stage stage;
    DataBase dataBase;
    @FXML
    ListView<String> users;

    @FXML
    public void initialize(){
        setUsers();
    }

    public void setUsers(){
        dataBase = new DataBase();
        users.setItems(dataBase.getUsers());
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }
}
