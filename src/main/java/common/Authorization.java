package common;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization{


    public Authorization() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/auth.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Авторизация");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
