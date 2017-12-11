package common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {
    private Stage stage;

    public void toRegistration(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/reg.fxml"));

        Scene scene = new Scene(root, 379, 300);

        stage.setTitle("Регистрация");
        stage.setScene(scene);
        stage.show();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }
}
