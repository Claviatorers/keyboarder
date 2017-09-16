package client;

import java.time.LocalTime;

/**
 * Created by User on 16.09.2017
 */
public class Exercise {
    private final int number;

    private String text;
    private DifficultyLevel difficultyLevel;
    private LocalTime keyPressTime;
    private int maxMistakes;

    public Exercise(int number, String text, DifficultyLevel difficultyLevel, LocalTime keyPressTime, int maxMistakes) {
        this.number = number;
        this.text = text;
        this.difficultyLevel = difficultyLevel;
        this.keyPressTime = keyPressTime;
        this.maxMistakes = maxMistakes;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public LocalTime getKeyPressTime() {
        return keyPressTime;
    }

    public int getMaxMistakes() {
        return maxMistakes;
    }
}
