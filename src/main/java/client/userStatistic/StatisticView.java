package client.userStatistic;

/**
 * Created by Александр on 18.12.2018 in 16:29.
 */
public class StatisticView {
    private String exerciseDifficulty;
    private String exerciseText;
    private int time;

    public StatisticView(String exerciseDifficulty, String exerciseText, int time) {
        this.exerciseDifficulty = exerciseDifficulty;
        this.exerciseText = exerciseText;
        this.time = time;
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
}
