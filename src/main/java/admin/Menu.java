package admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/adminMenu.fxml"));

        Scene scene = new Scene(root, 450, 350);

        stage.setTitle("Меню");
        stage.setScene(scene);
        stage.show();
    }
}
