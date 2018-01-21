package form;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Form {
    private static final String IMAGES_ICON_LOGO = "images/iconLogo.png";

    protected final Stage stage;
    protected final FXMLController controller;

    public Form(String fxmlPath, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        controller = loader.getController();
        stage = new Stage();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Image ico = new Image(IMAGES_ICON_LOGO);
        stage.getIcons().add(ico);
    }

    public void show(){
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
