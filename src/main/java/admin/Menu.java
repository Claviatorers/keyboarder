package admin;

import common.registration.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Menu {
    private Stage stage;

    public Menu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminMenu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        stage = new Stage();
        stage.setTitle("Администратор");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnHidden(event -> {
            try {
                menuController.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        menuController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
