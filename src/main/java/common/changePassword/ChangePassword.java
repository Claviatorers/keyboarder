package common.changePassword;

import common.registration.RegistrationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePassword {
    private Stage stage;

    public ChangePassword() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassword.fxml"));
        Parent root = loader.load();
        ChangePasswordController changePasswordController = loader.getController();
        stage = new Stage();
        stage.setTitle("Изменить пароль");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnHidden(event -> {
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
