package client.menu;

import client.DifficultyLevel;
import client.Exercise;
import client.training.TrainingForm;
import client.editAccount.EditAccount;
import client.userStatistic.UserStatistic;
import common.JsonFileHelper;
import common.about.About;
import common.auth.Authorization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Difficulty;
import model.ExerciseDao;
import model.User;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientMenuController {
    @FXML private Label difficultyLevel;
    @FXML private VBox exercisePane;
    @FXML private Label lastTrainingDate;
    @FXML private VBox buttonsBox;
    @FXML private ListView exerciseListView;
    private JsonFileHelper helper;
    private String login;
    private Stage stage;
    @FXML
    Label name;

    @SuppressWarnings("unchecked")
    @FXML
    public void initialize(){
        helper = JsonFileHelper.getInstance();
        exerciseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                stage.close();
                TrainingForm trainingForm = new TrainingForm((Exercise) newValue, login);
                trainingForm.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void init(Stage stage) {
        this.stage = stage;
    }

    void backToAuthorization() throws IOException {
        stage.hide();
        Authorization authorization = new Authorization();
        authorization.show();

    }

    void setUserByLogin(String login){
        User user = helper.getUserByLogin(login);
        this.login = user.getLogin();
        name.setText(user.getName());
        Date lastTrainingDateUnformatted = helper.getLastTrainingDate(login);
        if(lastTrainingDateUnformatted != null){
            DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            lastTrainingDate.setText(simpleDateFormat.format(lastTrainingDateUnformatted));
        } else {
            lastTrainingDate.setText("");
        }
    }

    public void editAccount(ActionEvent actionEvent) throws Exception {
        stage.hide();
        EditAccount editAccount = new EditAccount(login, name.getText());
        editAccount.show();
    }

    public void easyPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Easy);
        difficultyLevel.setText("Легкий");
    }

    public void mediumPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Medium);
        difficultyLevel.setText("Средний");
    }

    public void hardPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Hard);
        difficultyLevel.setText("Сложный");
    }

    private void setListView(DifficultyLevel difficultyLevel){
        Difficulty difficulty = helper.getDifficultyByLevel(difficultyLevel);
        List<ExerciseDao> exerciseList = helper.getExercisesByLevel(difficultyLevel);
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        for (ExerciseDao exerciseDao : exerciseList) {
            exercises.add(new Exercise(
                    exerciseDao.getId(), exerciseDao.getText(), difficultyLevel, difficulty.getKeyPressTime(), difficulty.getMaxMistakesInPercent()
                    )
            );
        }
        //noinspection unchecked
        exerciseListView.setItems(exercises);
        buttonsBox.setVisible(false);
        exercisePane.setVisible(true);
    }

    public void backToLevels(ActionEvent actionEvent) {
        exercisePane.setVisible(false);
        buttonsBox.setVisible(true);
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        setListView(difficultyLevel);
    }

    public void help(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(true, login);
        about.show();
    }

    public void info(ActionEvent actionEvent) throws IOException {
        stage.hide();
        About about = new About(false, login);
        about.show();
    }

    public void statistic(ActionEvent actionEvent) throws Exception {
        stage.hide();
        UserStatistic userStatistic = new UserStatistic(login);
        userStatistic.show();
    }
}
