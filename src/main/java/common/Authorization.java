package common;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/auth.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 300, 275);
        AuthController controller = loader.getController();
        controller.setStage(stage);

        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }
}
