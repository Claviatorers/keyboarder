package common;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Registration {
    

    public Registration() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reg.fxml"));
        Parent root = loader.load();
        RegController regController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
