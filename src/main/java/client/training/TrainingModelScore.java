package client.training;

import client.Exercise;

public class TrainingModelScore extends TrainingModel {
    private int score = 0;

    TrainingModelScore(Exercise exercise) {
        super(exercise);
    }

    @Override
    protected void rightSymbolPressed() {
        if(!isDone()) {
            currentPosition++;
            score++;
            if (currentPosition >= getText().length()) {
                currentPosition = 0;
            }
        }
    }

    @Override
    protected void wrongSymbolPressed() {
        currentPosition++;
        score--;
        if (currentPosition >= getText().length()) {
            currentPosition = 0;
        }
    }

    @Override
    boolean isDone() {
        return getLeftTime() <= 0;
    }

    @Override
    boolean isFailed() {
        return false;
    }

    int getPassScore(){
        return getText().length();
    }

    public int getScore() {
        return score;
    }
}
