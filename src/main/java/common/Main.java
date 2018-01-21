package common;

import client.DifficultyLevel;
import client.Exercise;
import client.Mode;
import client.training.TrainingForm;
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
