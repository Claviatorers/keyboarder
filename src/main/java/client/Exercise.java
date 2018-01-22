package client;

/**
 * Created by User on 16.09.2017
 */
public class Exercise {
    private String text;
    private DifficultyLevel difficultyLevel;
    private double keyPressTime;
    private int maxMistakes;

    public Exercise(String text, DifficultyLevel difficultyLevel, double keyPressTime, int maxMistakes) {
        this.text = text;
        this.difficultyLevel = difficultyLevel;
        this.keyPressTime = keyPressTime;
        this.maxMistakes = maxMistakes;
    }

    public String getText() {
        return text;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public double getKeyPressTime() {
        return keyPressTime;
    }

    public int getMaxMistakes() {
        return (int) Math.round((double) maxMistakes / 100 * getText().length()) + 1;
    }

    @Override
    public String toString() {
        return text;
    }
}
