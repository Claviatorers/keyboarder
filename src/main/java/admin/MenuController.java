package admin;

import common.auth.Authorization;
import common.changePassword.ChangePassword;
import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage;

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();

    } public void changePassword(ActionEvent actionEvent) throws Exception {
        stage.hide();
        ChangePassword changePassword = new ChangePassword();
        changePassword.show();
    }


}
