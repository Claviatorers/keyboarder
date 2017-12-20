package common.registration;

import common.DataBase;
import common.auth.Authorization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class RegistrationController {
    private Stage stage;
    @FXML
    private TextField name;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    private DataBase dataBase;

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();
    }


    public void register(ActionEvent actionEvent) throws IOException {
        dataBase = new DataBase();
        String nameText = name.getText();
        String loginText = login.getText();
        String passwordText = password.getText();
        String password2Text = password2.getText();
        if(nameText.isEmpty() || loginText.isEmpty() || passwordText.isEmpty() || password2Text.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Заполните все поля!");
            alert.showAndWait();
            return;
        }
        if(!passwordText.equals(password2Text)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Пароли не совпадают!");
            alert.showAndWait();
            return;
        }
        if(dataBase.isExistLogin(loginText)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Такой логин уже существует!");
            alert.showAndWait();
            return;
        }
        dataBase.registration(nameText, loginText,passwordText, password2Text);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Регистрация завершена успешно!");
        alert.showAndWait();
        stage.hide();
    }
}