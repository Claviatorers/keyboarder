package common;

import javafx.scene.control.Alert;

/**
 * Created by Александр on 16.12.2018 in 14:47.
 */
public class JavaFXHelper {
    private JavaFXHelper(){
        throw new UnsupportedOperationException();
    }

    public static void showMessage(String header, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
