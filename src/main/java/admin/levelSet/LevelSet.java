package admin.levelSet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LevelSet {
    private Stage stage;

    public LevelSet() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/levelSet.fxml"));
        Parent root = loader.load();
        LevelSetController levelSetController = loader.getController();
        levelSetController.setValues();
        stage = new Stage();
        stage.setTitle("Настройка тренажера");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setOnHidden(event -> {
            try {
                levelSetController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        levelSetController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
