package admin;

import admin.exerciseSet.ExerciseSet;
import admin.levelSet.LevelSet;
import admin.userAccounts.UserAccounts;
import common.about.About;
import common.auth.Authorization;
import common.changePassword.ChangePassword;
import common.registration.Registration;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController{
    private Stage stage;

    void init(Stage stage) {
        this.stage = stage;
    }

    void backToAuthorization() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();

    }

    public void levelSet(ActionEvent actionEvent) throws Exception {
        stage.close();
        LevelSet levelSet = new LevelSet();
        levelSet.show();
    }

    public void exerciseSet(ActionEvent actionEvent) throws Exception {
        stage.close();
        ExerciseSet exerciseSet = new ExerciseSet("Легкий");
        exerciseSet.show();
    }

    public void userAccounts(ActionEvent actionEvent) throws Exception {
        stage.close();
        UserAccounts userAccounts = new UserAccounts();
        userAccounts.show();
    }

    public void help(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(true, "admin");
        about.show();
    }

    public void info(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(false, "admin");
        about.show();
    }
}
