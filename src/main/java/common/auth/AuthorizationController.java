package common.auth;

import admin.Menu;
import common.DataBase;
import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            alert.setHeaderText("Заполните все поля!");
            alert.showAndWait();
        }
        else if (loginText.equals("admin") && passwordText.equals(dataBase.getPassword("admin"))) {
            stage.hide();
            Menu menu = new Menu();
            menu.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Неверный пароль!");
            alert.showAndWait();
        }

    }

    void init(Stage stage) {
        this.stage = stage;
    }

}
