package common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {


    public void toRegistration(ActionEvent actionEvent) throws Exception {
        new Registration();
    }


}
