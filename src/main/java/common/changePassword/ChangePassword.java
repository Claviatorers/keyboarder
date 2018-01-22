package common.changePassword;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ChangePassword {
    private Stage stage;

    public ChangePassword(String login) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassword.fxml"));
        Parent root = loader.load();
        ChangePasswordController changePasswordController = loader.getController();
        changePasswordController.setLogin(login);
        stage = new Stage();
        stage.setTitle("Изменить пароль");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                changePasswordController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        changePasswordController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
