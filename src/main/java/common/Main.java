package common;

import common.auth.Authorization;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Authorization authorization = new Authorization();
        authorization.show();
    }
}
