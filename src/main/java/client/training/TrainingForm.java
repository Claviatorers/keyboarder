package client.training;

import client.Exercise;
import client.Mode;
import form.Form;
import form.PathConstants;

import java.io.IOException;

public class TrainingForm extends Form {

    public TrainingForm(Exercise exercise, Mode mode, String login) throws IOException {
        super(PathConstants.TRAINING_PATH, "Тренировка");

        TrainingController trainingController = (TrainingController) controller;
        TrainingModel trainingModel = TrainingModel.getTrainingModel(mode, exercise);
        trainingController.setTrainingModel(trainingModel);
        trainingController.setSceneHandlers(stage.getScene());
        trainingController.setForm(this);
        trainingController.setLogin(login);
        stage.setOnCloseRequest(event -> {
            try {
                trainingController.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
