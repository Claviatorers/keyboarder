package client.training;

import form.FXMLController;
import form.Form;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.util.Optional;

public class TrainingController extends FXMLController {
    private static final String fontFamily = "Monospace";
    private static final double fontSize = 40;
    private static final Color NORMAL_COLOR = Color.BLACK;
    private static final Color ERROR_COLOR = Color.RED;
    private static final Color PRINTED_COLOR = Color.GREY;
    private static final Color CURRENT_COLOR = Color.BLUE;

    private final Timeline timer = new Timeline();
    private static final int SEC_IN_MIN = 60;
    private TrainingModel trainingModel;

    @FXML private Label countNameLabel;
    @FXML private TextFlow exerciseText;
    @FXML private Label countLabel;
    @FXML private Label timeLabel;

    private void setTime(int timeInSecs) {
        int minutes = timeInSecs / SEC_IN_MIN;
        int seconds = timeInSecs - minutes * SEC_IN_MIN;

        timeLabel.setText( (minutes > 9 ? minutes : "0" + minutes) + ":" + (seconds > 9 ? seconds : "0" + seconds));
    }

    @Override
    public void initialize() {

    }

    private void setStartParameters(){
        setCounts();

        setTime(trainingModel.getLeftTime());

        Text[] exerciseLetters = new Text[trainingModel.getText().length()];
        for (int i = 0; i < exerciseLetters.length; i++) {
            exerciseLetters[i] = new Text(String.valueOf(trainingModel.getText().charAt(i)));
        }
        exerciseText.getChildren().addAll(exerciseLetters);
        setDefaultColors();
        repaintText();
    }

    private void setCounts(){
        if (trainingModel instanceof TrainingModelTime) {
            countNameLabel.setText("Ошибки");
            countLabel.setText(((TrainingModelTime) trainingModel).getCurrentMistakes() + "/" + ((TrainingModelTime) trainingModel).getMaxMistakes());
        } else {
            countNameLabel.setText("Очки");
            countLabel.setText(((TrainingModelScore)trainingModel).getScore() + "/" + ((TrainingModelScore)trainingModel).getPassScore());
        }
    }

    private void setDefaultColors(){
        for (int i = 0; i < exerciseText.getChildren().size(); i++) {
            Text text = (Text) exerciseText.getChildren().get(i);
            setDefaultColor(text);
            text.setUnderline(false);
        }
    }

    private void setDefaultColor(Text text) {
        text.setFont(Font.font(fontFamily, fontSize));
        text.setFill(NORMAL_COLOR);
    }

    /**
     * Вызов этого метода запускает тренировку
     */
    private synchronized void startTraining(){
        trainingModel.reset();
        setDefaultColors();
        if (!timer.getKeyFrames().isEmpty()) {
            timer.getKeyFrames().remove(0);
        }
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            trainingModel.decreaseTime();
            setTime(trainingModel.getLeftTime());
            if(trainingModel.getLeftTime() <= 0 && trainingModel.isFailed()) {
                timer.stop();
                if (trainingModel.isFailed()) {
                    showFailedMessage("Время вышло!");
                } else {
                    TrainingModelScore trainingModelScore = (TrainingModelScore) trainingModel;
                    if (trainingModelScore.getScore() >= trainingModelScore.getPassScore()) {
                        showCompletedMessage();
                    }
                }
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    private void showCompletedMessage() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.NONE, "Упражнение выполнено!", ButtonType.OK);
            alert.setTitle("Успех!");

            alert.showAndWait();
            form.close();
        });
    }

    public void setTrainingModel(TrainingModel trainingModel) {
        this.trainingModel = trainingModel;
        trainingModel.reset();
        setStartParameters();
    }

    public void setSceneHandlers(Scene scene) {
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                scene.removeEventHandler(event.getEventType(), this);
                startTraining();
                System.out.println(event.getCharacter());
                trainingModel.inputSymbol(event.getCharacter().charAt(0));
                repaintText();
                scene.setOnKeyTyped(event1 -> {
                    System.out.println(event1.getCharacter());
                    trainingModel.inputSymbol((event1.getCharacter().charAt(0)));
                    repaintText();
                    if (trainingModel.isFailed()) {
                        timer.stop();
                        showFailedMessage("Допущено слишком много ошибок!");
                    } else if (trainingModel.isDone()) {
                        timer.stop();
                        showCompletedMessage();
                    }
                });
            }
        };
        scene.setOnKeyTyped(eventHandler);
    }

    private void showFailedMessage(String message) {
        Platform.runLater(() -> {
            ButtonType finish = new ButtonType("Закончить", ButtonBar.ButtonData.FINISH);
            ButtonType again = new ButtonType("Еще раз", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.NONE, message, finish, again);
            alert.setTitle("Упражнение не пройдено!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == finish) {
                form.close();
            } else if (result.isPresent() && result.get() == again) {
                startTraining();
                repaintText();
            }
        });
    }

    private void repaintText() {
        setCounts();
        int currentPosition = trainingModel.getCurrentPosition();
        if (currentPosition == 0) {
            setDefaultColors();
        }
        if (!trainingModel.isDone()) {
            Text currentLetter = (Text) exerciseText.getChildren().get(currentPosition);
            currentLetter.setFill(CURRENT_COLOR);
            currentLetter.setUnderline(true);
        }
        if(currentPosition > 0){
            Text prevLetter  = (Text) exerciseText.getChildren().get(currentPosition - 1);
            prevLetter.setFill(PRINTED_COLOR);
            prevLetter.setUnderline(false);
        }
    }

    void setForm(Form form) {
        this.form = form;
    }
}