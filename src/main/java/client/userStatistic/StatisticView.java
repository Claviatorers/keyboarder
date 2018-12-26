package client.userStatistic;

/**
 * Created by Александр on 18.12.2018 in 16:29.
 */
public class StatisticView {
    private String exerciseDifficulty;
    private String exerciseText;
    private int time;
    private int averageCharsPerMinute;
    private double averageMistakes;

    public StatisticView(String exerciseDifficulty, String exerciseText, int time, int averageCharsPerMinute, double averageMistakes) {
        this.exerciseDifficulty = exerciseDifficulty;
        this.exerciseText = exerciseText;
        this.time = time;
        this.averageCharsPerMinute = averageCharsPerMinute;
        this.averageMistakes = averageMistakes;
    }

    public String getExerciseDifficulty() {
        return exerciseDifficulty;
    }

    public void setExerciseDifficulty(String exerciseDifficulty) {
        this.exerciseDifficulty = exerciseDifficulty;
    }

    public String getExerciseText() {
        return exerciseText;
    }

    public void setExerciseText(String exerciseText) {
        this.exerciseText = exerciseText;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAverageCharsPerMinute() {
        return averageCharsPerMinute;
    }

    public void setAverageCharsPerMinute(int averageCharsPerMinute) {
        this.averageCharsPerMinute = averageCharsPerMinute;
    }

    public double getAverageMistakes() {
        return averageMistakes;
    }

    public void setAverageMistakes(double averageMistakes) {
        this.averageMistakes = averageMistakes;
    }
}
