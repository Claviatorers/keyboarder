package model;


public class ExerciseStat {
    private final int exerciseId;
    private final int averageTime;
    private final int completedTimes;
    private final int averageCharsPerMinute;
    private final double averageMistakes;

    public ExerciseStat(int exerciseId, int averageTime, int completedTimes, int averageCharsPerMinute, double averageMistakes) {
        this.exerciseId = exerciseId;
        this.averageTime = averageTime;
        this.completedTimes = completedTimes;
        this.averageCharsPerMinute = averageCharsPerMinute;
        this.averageMistakes = averageMistakes;
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

    public int getAverageCharsPerMinute() {
        return averageCharsPerMinute;
    }

    public double getAverageMistakes() {
        return averageMistakes;
    }
}
