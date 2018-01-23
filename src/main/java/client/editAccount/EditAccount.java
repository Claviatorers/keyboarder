package client.editAccount;

import admin.addExercise.AddExerciseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditAccount {
    private Stage stage;

    public EditAccount(String login, String name) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editAccount.fxml"));
        Parent root = loader.load();
        EditAccountController editAccountController = loader.getController();
        editAccountController.setInfo(login,name);
        stage = new Stage();
        stage.setTitle("Учетная запись");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                editAccountController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        editAccountController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
