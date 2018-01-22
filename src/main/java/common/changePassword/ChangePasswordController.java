package common.changePassword;

import admin.Menu;
import client.menu.ClientMenu;
import common.DataBase;
import common.auth.Authorization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ChangePasswordController {
    DataBase dataBase;
    private Stage stage;
    private String login;

    @FXML
    PasswordField curPassword;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField confirmPassword;

    @FXML
    public void initialize(){

        newPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[^\\s]{0,10}")) {
                newPassword.textProperty().setValue(oldValue);
            }
        });
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        if(login !="admin"){
            ClientMenu clientMenu = new ClientMenu(login);
            clientMenu.show();
        } else {
            Menu menu = new Menu();
            menu.show();
        }
    }

    void setLogin(String login){
        this.login = login;
    }

    public void change(ActionEvent actionEvent) throws Exception {
        String curPasswordText = curPassword.getText();
        String newPasswordText = newPassword.getText();
        String confirmPasswordText = confirmPassword.getText();
        dataBase = new DataBase();
        if(curPasswordText.isEmpty() || newPasswordText.isEmpty() || confirmPasswordText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Заполните все поля!");
            alert.showAndWait();
        }
        else if(newPasswordText.length() < 5){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Новый пароль слишком короткий!");
            alert.showAndWait();
        }
        else if(!newPasswordText.equals(confirmPasswordText)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Пароли не совпадают!");
            alert.showAndWait();
        }
        else if(!curPasswordText.equals(dataBase.getPassword(login))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Текущий пароль неверный!");
            alert.showAndWait();
        }
        else {
            dataBase.changePassword(login, newPassword.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Сообщение");
            alert.setHeaderText("Пароль успешно изменен");
            alert.showAndWait();
            stage.hide();
            if(login !="admin"){
                ClientMenu clientMenu = new ClientMenu(login);
                clientMenu.show();
            } else {
                Menu menu = new Menu();
                menu.show();
            }
        }
    }
}
