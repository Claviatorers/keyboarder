package admin.userAccounts;

public class Statistic {
    private String login;
    private String date;
    private String level;
    private int mistake;
    private int time;
    private int score;


    public Statistic() {
    }

    public Statistic(String login, String date, String level, int mistake, int time, int score) {
        this.login = login;
        this.date = date;
        this.level = level;
        this.mistake = mistake;
        this.time = time;
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getMistake() {
        return mistake;
    }

    public void setMistakes(int mistake) {
        this.mistake = mistake;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
