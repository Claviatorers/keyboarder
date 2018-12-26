package client.training;

import client.menu.ClientMenu;
import common.JsonFileHelper;
import form.FXMLController;
import form.Form;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.Optional;

public class TrainingController extends FXMLController {
    private static final String fontFamily = "Monospace";
    private static final double fontSize = 40;
    private static final Color NORMAL_COLOR = Color.BLACK;
    private static final Color ERROR_COLOR = Color.RED;
    private static final Color PRINTED_COLOR = Color.GREY;
    private static final Color CURRENT_COLOR = Color.BLUE;

    private final Timeline timer = new Timeline();
    public ToggleButton keyboardStateButton;
    private VirtualKeyboard vk;
    private static final int SEC_IN_MIN = 60;
    private JsonFileHelper helper;

    private TrainingModel trainingModel;

    @FXML private VBox virtualKeyboard;
    @FXML private Label countNameLabel;
    @FXML private TextFlow exerciseText;
    @FXML private Label countLabel;
    @FXML private Label timeLabel;
    private String login;

    private void setTime(int timeInSecs) {
        int minutes = timeInSecs / SEC_IN_MIN;
        int seconds = timeInSecs - minutes * SEC_IN_MIN;

        timeLabel.setText( (minutes > 9 ? minutes : "0" + minutes) + ":" + (seconds > 9 ? seconds : "0" + seconds));
    }

    @Override
    public void initialize() {
        vk = new VirtualKeyboard(virtualKeyboard);
        helper = JsonFileHelper.getInstance();
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
            timer.getKeyFrames().removeAll(timer.getKeyFrames());
        }
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            trainingModel.decreaseTime();
            setTime(trainingModel.getLeftTime());
            if(trainingModel.getLeftTime() <= 0) {
                timer.stop();
                if (trainingModel.isFailed()) {
                    saveResultTimeMode();
                    showFailedMessage("Время вышло!");
                } else {
                    TrainingModelScore trainingModelScore = (TrainingModelScore) trainingModel;
                    //saveResultScoreMode();
                    if (trainingModelScore.getScore() >= trainingModelScore.getPassScore()) {
                        showCompletedMessage();
                    } else {
                        showFailedMessage("Набрано недостаточно очков!");
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
            backToParentForm();
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
                    if (trainingModel.isFailed() && trainingModel.getLeftTime() > 0) {
                        if (timer.getStatus() != Animation.Status.STOPPED) {
                            timer.stop();
                            scene.removeEventHandler(event1.getEventType(), this);
                            saveResultTimeMode();
                            showFailedMessage("Допущено слишком много ошибок!");
                        }
                    } else if (trainingModel.isDone()) {
                        timer.stop();
                        saveResultTimeMode();
                        showCompletedMessage();
                    }
                });
            }
        };
        scene.setOnKeyTyped(eventHandler);
    }

    private void saveResultTimeMode() {
        helper.updateStats(Calendar.getInstance().getTime(),
                trainingModel.getMaxTime() - trainingModel.getLeftTime(),
                ((TrainingModelTime) trainingModel).getCurrentMistakes(),
                trainingModel.exercise.getText().length(),
                login, trainingModel.getID());
    }

    private void showFailedMessage(String message) {
        Platform.runLater(() -> {
            ButtonType finish = new ButtonType("Закончить", ButtonBar.ButtonData.FINISH);
            ButtonType again = new ButtonType("Еще раз", ButtonBar.ButtonData.OK_DONE);

            Alert alert = new Alert(Alert.AlertType.NONE, message, finish, again);
            alert.setTitle("Упражнение не пройдено!");
            ((Button)alert.getDialogPane().lookupButton( finish )).setDefaultButton(false);
            ((Button)alert.getDialogPane().lookupButton( again )).setDefaultButton(false);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == finish) {
                backToParentForm();
            } else if (result.isPresent() && result.get() == again) {
                startTraining();
                repaintText();
            }
        });
    }

    private void backToParentForm(){
        form.close();
        try {
            ClientMenu clientMenu = new ClientMenu(login, trainingModel.getDifficultyLevel());
            clientMenu.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            vk.lightLetter(trainingModel.getCurrentCharacter());
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void keyboardChangeState(ActionEvent actionEvent) {
        if (keyboardStateButton.isSelected()) {
            virtualKeyboard.setVisible(true);
        } else {
            virtualKeyboard.setVisible(false);
        }
    }

    void hide(){
        if (!timer.getKeyFrames().isEmpty()) {
            timer.getKeyFrames().removeAll(timer.getKeyFrames());
        }
        form.close();
        backToParentForm();
    }
}