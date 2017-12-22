package admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setOnCloseRequest(event -> {
            try {
                menuController.backToAuthorization();
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