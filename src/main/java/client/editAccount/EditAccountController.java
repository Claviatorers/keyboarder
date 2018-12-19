package client.editAccount;

import client.menu.ClientMenu;
import common.JsonFileHelper;
import common.auth.Authorization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EditAccountController {
    private JsonFileHelper helper;
    private Stage stage;
    private String login;
    @FXML
    TextField name;

    @FXML
    public void initialize(){
        helper = JsonFileHelper.getInstance();
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
        helper.editUserName(login, name.getText());
        stage.hide();
        ClientMenu clientMenu = new ClientMenu(login);
        clientMenu.show();
    }

    public void DeleteUser(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Удалить учетную запись?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            helper.deleteUser(login);
            stage.hide();
            Authorization authorization = new Authorization();
            authorization.show();
        }
    }
}
