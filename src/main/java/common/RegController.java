package common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegController {
    private Stage prevStage;

    void init(Stage prevStage) {
        this.prevStage = prevStage;
    }

    void close(){
        prevStage.show();
    }
}

