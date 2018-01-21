package client.training;

import client.Exercise;

public class TrainingModelTime extends TrainingModel {
    private int currentMistakes = 0;

    TrainingModelTime(Exercise exercise) {
        super(exercise);
    }

    @Override
    protected void rightSymbolPressed() {
        if(!isDone() && currentPosition != exercise.getText().length())
            currentPosition++;
    }

    @Override
    protected void wrongSymbolPressed() {
        currentMistakes++;
    }

    @Override
    boolean isDone() {
        return currentPosition == exercise.getText().length();
    }

    @Override
    boolean isFailed() {
        return currentMistakes == getMaxMistakes() || getLeftTime() <= 0;
    }

    int getMaxMistakes() {
        return exercise.getMaxMistakes();
    }

    int getCurrentMistakes() {
        return currentMistakes;
    }
}
