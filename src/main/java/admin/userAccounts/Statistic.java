package admin.userAccounts;

public class Statistic {
    private String login;
    private String difficulty;
    private int time;
    private String text;

    public Statistic() {
    }

    public Statistic(String login, String difficulty, int time, String text) {
        this.login = login;
        this.difficulty = difficulty;
        this.time = time;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
