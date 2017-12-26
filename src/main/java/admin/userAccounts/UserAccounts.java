package admin.userAccounts;

import admin.exerciseSet.ExerciseSetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserAccounts {
    private Stage stage;

    public UserAccounts() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/users.fxml"));
        Parent root = loader.load();
        UserAccountsController userAccountsController = loader.getController();
        stage = new Stage();
        stage.setTitle("Настройка упражнений");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setOnCloseRequest(event -> {
            try {
                userAccountsController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        userAccountsController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
