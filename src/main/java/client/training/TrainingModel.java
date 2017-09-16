package client.training;

import client.Exercise;

/**
 * Created by User on 16.09.2017
 */
public class TrainingModel {
    private Exercise exercise;
    private int currentMistakes = 0;
    //todo Timer
    private int currentPosition = 0;


    public TrainingModel(Exercise exercise) {
        this.exercise = exercise;
    }

    public boolean checkSymbol(char symbol) {
        if (exercise.getText().charAt(currentPosition) == symbol) {
            currentPosition++;
            return true;
        }
        else{
            currentMistakes++;
            return false;
        }
    }

    public boolean isDone() {
        return currentPosition >= exercise.getText().length();
    }

    public boolean isFailed() {
        return currentMistakes >= exercise.getMaxMistakes();
    }
}
