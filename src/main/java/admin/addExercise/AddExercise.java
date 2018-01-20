package admin.addExercise;

import admin.exerciseSet.ExerciseSetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AddExercise {
    private Stage stage;

    public AddExercise(int level) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addExercise.fxml"));
        Parent root = loader.load();
        AddExerciseController addExerciseController = loader.getController();
        addExerciseController.setLevelNum(level);
        stage = new Stage();
        stage.setTitle("Настройка упражнения");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setOnCloseRequest(event -> {
            try {
                addExerciseController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        addExerciseController.init(stage);
    }

    public void show() {
        stage.show();
    }
}
