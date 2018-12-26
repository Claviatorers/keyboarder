package model;

import client.DifficultyLevel;


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
