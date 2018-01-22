package admin.exerciseSet;

import admin.levelSet.LevelSetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ExerciseSet {
    private Stage stage;

    public ExerciseSet(String level) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/exerciseSet.fxml"));
        Parent root = loader.load();
        ExerciseSetController exerciseSetController = loader.getController();
        exerciseSetController.setLevel(level);
        stage = new Stage();
        stage.setTitle("Настройка упражнений");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                exerciseSetController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        exerciseSetController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
