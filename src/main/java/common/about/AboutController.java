package common.about;

import admin.Menu;
import client.menu.ClientMenu;
import common.auth.Authorization;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {
    @FXML
    WebView webViewProject;

    private Stage stage;

    boolean help;
    String parent;

    void init(Stage stage) {
        this.stage = stage;
        try {
            if(help) {
                webViewProject.getEngine().load(getClass().getResource("/AboutSystem.html").toString());
            } else {
                webViewProject.getEngine().load(getClass().getResource("/AboutDevelopers.html").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setInfo(boolean help, String parent){
        this.help = help;
        this.parent = parent;
    }

    void back() throws Exception {
        stage.hide();
        if(parent == "auth") {
            Authorization authorization = new Authorization();
            authorization.show();
        } else if (parent == "admin") {
            Menu menu = new Menu();
            menu.show();
        } else {
            ClientMenu clientMenu = new ClientMenu(parent);
            clientMenu.show();
        }

    }

}