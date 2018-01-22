package client.menu;

import client.editAccount.EditAccount;
import common.DataBase;
import common.about.About;
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

    public void help(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(true, login);
        about.show();
    }

    public void info(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(false, login);
        about.show();
    }
}
