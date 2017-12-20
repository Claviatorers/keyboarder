package common.changePassword;

import admin.Menu;
import common.auth.Authorization;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordController {
    private Stage stage;

    void init(Stage stage) {
        this.stage = stage;
    }

    void close() throws Exception {
        stage.hide();
        Menu menu = new Menu();
        menu.show();
    }
}
