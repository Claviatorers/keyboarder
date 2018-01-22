package common.registration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {
    private Stage stage;

    public Registration() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reg.fxml"));
        Parent root = loader.load();
        RegistrationController registrationController = loader.getController();
        stage = new Stage();
        stage.setTitle("Регистрация");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnHidden(event -> {
            try {
                registrationController.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registrationController.init(stage);
    }

    public void show() {
        stage.show();
    }

}
