package common.registration;

import common.auth.Authorization;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    private Stage stage;

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();
    }
}