package common.about;

import common.auth.Authorization;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {
    @FXML
    WebView webViewProject;

    private Stage stage;

    void init(Stage stage) {
        this.stage = stage;
    }

    void backToAuthorization() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();

    }


    public void init() {
        try {
           // webViewProject.getEngine().load(getClass().getResource("/AboutSystem.html").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}