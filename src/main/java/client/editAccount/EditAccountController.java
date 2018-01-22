package client.editAccount;

import client.menu.ClientMenu;
import common.DataBase;
import common.auth.Authorization;
import common.changePassword.ChangePassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EditAccountController {
    private DataBase dataBase;
    private Stage stage;
    private String login;
    @FXML
    TextField name;

    @FXML
    public void initialize(){
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[а-яА-Я]{0,20}")) {
                name.textProperty().setValue(oldValue);
            }
        });
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void setInfo(String login, String name){
        this.login = login;
        this.name.setText(name);
    }


    void close() throws Exception {
        stage.hide();
        ClientMenu clientMenu = new ClientMenu(login);
        clientMenu.show();
    }

    public void save(ActionEvent actionEvent) throws Exception {
        dataBase = new DataBase();
        dataBase.editUserName(login, name.getText());
        stage.hide();
        ClientMenu clientMenu = new ClientMenu(login);
        clientMenu.show();
    }


    public void changePassword(ActionEvent actionEvent) throws Exception {
        stage.close();
        ChangePassword changePassword = new ChangePassword(login);
        changePassword.show();
    }

    public void DeleteUser(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Удалить учетную запись?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dataBase = new DataBase();
            dataBase.deleteUser(login);
            stage.hide();
            Authorization authorization = new Authorization();
            authorization.show();

        }
    }
}
