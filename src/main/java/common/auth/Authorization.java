package common.auth;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Authorization{
    private Stage stage;

    public Authorization() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/auth.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.setTitle("Авторизация");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AuthorizationController authorizationController = loader.getController();
        authorizationController.init(stage);
    }

    public void show(){
        stage.show();
    }
}
