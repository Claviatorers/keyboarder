package model;

/**
 * Created by Александр on 15.12.2018 in 16:28.
 */
public class User {
    private final String login;
    private final String name;

    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
