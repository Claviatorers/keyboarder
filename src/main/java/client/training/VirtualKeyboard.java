package client.training;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class VirtualKeyboard {
    private static final String NONE_BACKGROUND = "white";
    private final VBox root;
    private Label lightningLabel = null;
    private final Map<Character, Label> labelMap;

    public VirtualKeyboard(VBox root) {
        this.root = root;
        labelMap = new HashMap<>();
        fillLabelMap();
    }

    private void fillLabelMap() {
        ObservableList<Node> hboxes = root.getChildren();
        for(Node hbox: hboxes) {
            for(Node label: ((HBox)hbox).getChildren())
            labelMap.put(((Label)label).getText().charAt(0), (Label) label);
        }
    }

    public void lightLetter(Character character) {
        if(lightningLabel != null) {
            lightningLabel.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-radius: 20%; -fx-border-width: 1");
        }
        if (character == ' '){
            lightningLabel = labelMap.get(' ');
        } else {
            lightningLabel = labelMap.get(character);
        }
        lightningLabel.setStyle("-fx-background-color: yellow; -fx-border-style: solid; -fx-border-radius: 20%; -fx-border-width: 3");
    }
}
