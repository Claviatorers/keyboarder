package client.menu;

import client.editAccount.EditAccount;
import common.DataBase;
import common.auth.Authorization;
import common.changePassword.ChangePassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMenuController {
    private DataBase dataBase;
    private String login;
    private Stage stage;
    @FXML
    Label name;

    void init(Stage stage) {
        this.stage = stage;
    }

    void backToAuthorization() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();

    }
    public void changePassword(ActionEvent actionEvent) throws Exception {
        stage.close();
        ChangePassword changePassword = new ChangePassword(login);
        changePassword.show();
    }

    void setLogin(String login){
        this.login = login;
        setName();
    }

    private void setName(){
        dataBase = new DataBase();
        name.setText(dataBase.getName(login));
    }

    public void editAccount(ActionEvent actionEvent) throws Exception {
        stage.hide();
        EditAccount editAccount = new EditAccount(login, name.getText());
        editAccount.show();
    }
}
