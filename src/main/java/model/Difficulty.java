package model;

import client.DifficultyLevel;

import java.util.List;

/**
 * Created by Александр on 16.12.2018 in 15:21.
 */
public class Difficulty {
    private final DifficultyLevel level;
    private final double keyPressTime;
    private final int maxMistakesInPercent;
    private final int maxExerciseLength;
    private final List<Zone> availableZones;

    public Difficulty(DifficultyLevel level, double keyPressTime, int maxMistakesInPercent, int maxExerciseLength, List<Zone> availableZones) {
        this.level = level;
        this.keyPressTime = keyPressTime;
        this.maxMistakesInPercent = maxMistakesInPercent;
        this.maxExerciseLength = maxExerciseLength;
        this.availableZones = availableZones;
    }

    public List<Zone> getAvailableZones() {
        return availableZones;
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
