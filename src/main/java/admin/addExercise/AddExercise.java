package admin.addExercise;

import client.DifficultyLevel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ExerciseDao;

public class AddExercise {
    private Stage stage;

    public AddExercise(DifficultyLevel difficultyLevel) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addExercise.fxml"));
        Parent root = loader.load();
        AddExerciseController addExerciseController = loader.getController();
        addExerciseController.setExercise(new ExerciseDao(0, "", difficultyLevel));
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

    public AddExercise(ExerciseDao exerciseDao) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addExercise.fxml"));
        Parent root = loader.load();
        AddExerciseController addExerciseController = loader.getController();
        addExerciseController.setExercise(exerciseDao);
        stage = new Stage();
        stage.setTitle("Настройка упражнения");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image ico = new Image("images/iconLogo.png");
        stage.getIcons().add(ico);
        stage.setResizable(false);
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
