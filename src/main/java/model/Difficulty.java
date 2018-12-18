package model;

import client.DifficultyLevel;

/**
 * Created by Александр on 16.12.2018 in 15:21.
 */
public class Difficulty {
    private final DifficultyLevel level;
    private final double keyPressTime;
    private final int maxMistakesInPercent;
    private final int maxExerciseLength;
    private final String availableChars;

    public Difficulty(DifficultyLevel level, double keyPressTime, int maxMistakesInPercent, int maxExerciseLength, String availableChars) {
        this.level = level;
        this.keyPressTime = keyPressTime;
        this.maxMistakesInPercent = maxMistakesInPercent;
        this.maxExerciseLength = maxExerciseLength;
        this.availableChars = availableChars;
    }

    public String getAvailableChars() {
        return availableChars;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public double getKeyPressTime() {
        return keyPressTime;
    }

    public int getMaxMistakesInPercent() {
        return maxMistakesInPercent;
    }

    public int getMaxExerciseLength() {
        return maxExerciseLength;
    }
}
