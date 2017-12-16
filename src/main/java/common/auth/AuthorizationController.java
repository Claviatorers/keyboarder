package common.auth;

import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AuthorizationController {
    private Stage stage;

    public void toRegistration(ActionEvent actionEvent) throws Exception {
        stage.hide();
        Registration registration = new Registration();
        registration.show();
    }

    void init(Stage stage) {
        this.stage = stage;
    }

}
