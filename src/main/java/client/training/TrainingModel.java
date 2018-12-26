package client.training;

import client.DifficultyLevel;
import client.Exercise;
import client.Mode;


abstract class TrainingModel {
    private int leftTime = 0;

    protected Exercise exercise;
    protected int currentPosition = 0;

    TrainingModel(Exercise exercise) {
        this.exercise = exercise;
    }

    static TrainingModel getTrainingModel(Exercise exercise){
        return new TrainingModelTime(exercise);
    }

    public void inputSymbol(char symbol) {
        if (isDone() || isFailed()) return;

        if (exercise.getText().charAt(currentPosition) == symbol) {
            rightSymbolPressed();
        }
        else{
            wrongSymbolPressed();
        }
    }

    protected abstract void rightSymbolPressed();

    protected abstract void wrongSymbolPressed();

    int getMaxTime() {
        return (int) Math.round(exercise.getKeyPressTime() * exercise.getText().length()) + 1;
    }

    abstract boolean isDone();

    abstract boolean isFailed();

    void decreaseTime() {
        leftTime--;
    }

    void reset() {
        leftTime = getMaxTime();
        currentPosition = 0;
        resetSpecial();
    }

    protected abstract void resetSpecial();

    int getLeftTime() {
        return leftTime;
    }


    String getText() {
        return exercise.getText();
    }

    int getCurrentPosition(){
        return currentPosition;
    }

    public DifficultyLevel getDifficultyLevel() {
        return exercise.getDifficultyLevel();
    }

    public int getID() {
        return exercise.getId();
    }

    public char getCurrentCharacter(){
        return exercise.getText().charAt(currentPosition);
    }
}
