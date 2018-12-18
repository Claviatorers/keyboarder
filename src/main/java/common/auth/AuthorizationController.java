package common.auth;

import static common.JavaFXHelper.*;

import admin.Menu;
import callback.Callback;
import client.menu.ClientMenu;
import common.JsonFileHelper;
import common.about.About;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class AuthorizationController {
    JsonFileHelper helper = null;
    private Stage stage;
    @FXML
    private TextField login;

    @FXML
    public void initialize(){
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[a-zA-Z]{0,10}")) {
                login.textProperty().setValue(oldValue);
            }
        });
        helper = JsonFileHelper.getInstance();
    }

    public void enterPressed(ActionEvent actionEvent) throws Exception {
        String loginText = login.getText();
        if(loginText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Заполните поле логина!");
            alert.showAndWait();
        }
        else if (loginText.equals("admin")) {
            stage.hide();
            Menu menu = new Menu();
            menu.show();
        }
        else {
            User user = helper.getUserByLogin(loginText);
            if (user != null) {
                stage.hide();
                ClientMenu clientMenu = new ClientMenu(user.getLogin());
                clientMenu.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Пользователя с таким логином не существует!");
                alert.showAndWait();
            }
        }
    }

    void init(Stage stage) {
        this.stage = stage;
    }


    public void help(ActionEvent actionEvent) throws IOException {
        stage.hide();
       About about = new About(true, "auth");
       about.show();
    }

    public void info(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(false, "auth");
        about.show();
    }

    public void registrationPressed(ActionEvent event) {
        String loginText = login.getText();
        User user = new User(loginText, loginText);
        helper.addUser(user, new Callback<User>() {
            @Override
            public void onSuccess(User object) {
                showMessage("Новый пользователь зарегистрирован!", Alert.AlertType.INFORMATION);
            }

            @Override
            public void onFail(String errorMessage) {
                showMessage(errorMessage, Alert.AlertType.ERROR);
            }
        });
    }
}
