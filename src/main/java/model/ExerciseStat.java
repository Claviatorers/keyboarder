package model;


public class ExerciseStat {
    private final int exerciseId;
    private final int averageTime;
    private final int completedTimes;

    public ExerciseStat(int exerciseId, int averageTime, int completedTimes) {
        this.exerciseId = exerciseId;
        this.averageTime = averageTime;
        this.completedTimes = completedTimes;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public int getCompletedTimes() {
        return completedTimes;
    }
}
