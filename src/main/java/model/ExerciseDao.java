package model;

import client.DifficultyLevel;

/**
 * Created by Александр on 15.12.2018 in 19:12.
 */
public class ExerciseDao {
    private final int id;
    private final String text;
    private final DifficultyLevel difficulty;

    public ExerciseDao(int id, String text, DifficultyLevel difficulty) {
        this.id = id;
        this.text = text;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return getText();
    }
}
