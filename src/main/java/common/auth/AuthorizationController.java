package common.auth;

import admin.Menu;
import client.menu.ClientMenu;
import common.DataBase;
import common.about.About;
import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationController {
    DataBase dataBase;
    private Stage stage;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;


    public void toRegistration(ActionEvent actionEvent) throws Exception {
        stage.hide();
        Registration registration = new Registration();
        registration.show();
    }

    public void enter(ActionEvent actionEvent) throws Exception {
        dataBase = new DataBase();
        String loginText = login.getText();
        String passwordText = password.getText();
        if(loginText.isEmpty() || passwordText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Заполните все поля!");
            alert.showAndWait();
        }
        else if (loginText.equals("admin") && passwordText.equals(dataBase.getPassword("admin"))) {
            stage.hide();
            Menu menu = new Menu();
            menu.show();
        }
        else if (!loginText.equals("admin") && dataBase.isExistLogin(loginText) &&  passwordText.equals(dataBase.getPassword(loginText))) {
            stage.hide();
            ClientMenu clientMenu = new ClientMenu(loginText);
            clientMenu.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Неверный пароль или логин!");
            alert.showAndWait();
        }
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[a-zA-Z]{0,10}")) {
                login.textProperty().setValue(oldValue);
            }
        });
    }

    public void help(ActionEvent actionEvent) throws IOException {
        stage.hide();
       About about = new About();
       about.show();
    }

    public void info(ActionEvent actionEvent) {

    }
}
