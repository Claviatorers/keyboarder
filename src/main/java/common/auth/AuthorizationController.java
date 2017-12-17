package common.auth;

import admin.Menu;
import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {
    private Stage stage;
    @FXML
    private TextField login;


    public void toRegistration(ActionEvent actionEvent) throws Exception {
        stage.hide();
        Registration registration = new Registration();
        registration.show();
    }

    public void enter(ActionEvent actionEvent) throws Exception {
        String loginText = login.getText();
        if (loginText.equals("Admin")) {
            stage.hide();
            Menu menu = new Menu();
            menu.show();
        }

    }

    void init(Stage stage) {
        this.stage = stage;
    }

}
