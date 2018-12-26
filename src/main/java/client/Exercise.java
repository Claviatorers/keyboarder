package client;


public class Exercise {
    private final int id;
    private String text;
    private DifficultyLevel difficultyLevel;
    private double keyPressTime;
    private int maxMistakes;

    public Exercise(int id, String text, DifficultyLevel difficultyLevel, double keyPressTime, int maxMistakes) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
