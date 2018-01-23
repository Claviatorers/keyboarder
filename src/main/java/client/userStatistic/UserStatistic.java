package client.userStatistic;

import admin.userAccounts.UserAccountsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserStatistic {
    private Stage stage;

    public UserStatistic(String login) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userStatistic.fxml"));
        Parent root = loader.load();
        UserStatisticController userStatisticController = loader.getController();
        userStatisticController.setLogin(login);
        stage = new Stage();
        stage.setTitle("Статистика");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                userStatisticController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        userStatisticController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
