package common.about;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class About {
    private Stage stage;

    public About(boolean help, String parent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/about.fxml"));
        Parent root = loader.load();
        AboutController aboutController = loader.getController();
        aboutController.setInfo(help, parent);
        stage = new Stage();
        stage.setTitle("Справка");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                aboutController.back();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        aboutController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
