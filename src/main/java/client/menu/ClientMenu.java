package client.menu;

import client.DifficultyLevel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMenu {
    private Stage stage;
    private ClientMenuController clientMenuController;

    public ClientMenu(String login, DifficultyLevel difficultyLevel) throws Exception {
        this(login);
        clientMenuController.setDifficultyLevel(difficultyLevel);
    }

    public ClientMenu(String login) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientMenu.fxml"));
        Parent root = loader.load();
        clientMenuController = loader.getController();
        clientMenuController.setUserByLogin(login);
        stage = new Stage();
        stage.setTitle("Обучение");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                clientMenuController.backToAuthorization();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clientMenuController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
