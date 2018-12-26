package common;

import javafx.scene.control.Alert;

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
