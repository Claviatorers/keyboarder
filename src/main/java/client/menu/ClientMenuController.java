package client.menu;

import client.DifficultyLevel;
import client.Exercise;
import client.Mode;
import client.training.TrainingForm;
import client.editAccount.EditAccount;
import common.DataBase;
import common.UserInfo;
import common.about.About;
import common.auth.Authorization;
import common.changePassword.ChangePassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ClientMenuController {
    public ToggleGroup modeGroup;
    @FXML private VBox exercisePane;
    @FXML private Label lastTrainingDate;
    @FXML private VBox buttonsBox;
    @FXML private ListView exerciseListView;
    private DataBase dataBase;
    private String login;
    private Stage stage;
    @FXML
    Label name;

    @SuppressWarnings("unchecked")
    @FXML
    public void initialize(){
        dataBase = new DataBase();
        exerciseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                stage.close();
                ToggleButton selected = (ToggleButton) modeGroup.getSelectedToggle();
                Mode mode;
                if (selected.getText().equals("На время")){
                    mode = Mode.Time;
                } else {
                    mode = Mode.Score;
                }
                TrainingForm trainingForm = new TrainingForm((Exercise) newValue, mode, login);
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

    void setLogin(String login){
        this.login = login;
        setName();
//        List<UserInfo> userInfoList = dataBase.getUserInformation(login);
//        final Date[] lastDate = {new Date(0)};
//        userInfoList.forEach(userInfo -> {
//            if (userInfo.getTrainingDate().after(lastDate[0])) {
//                lastDate[0] = userInfo.getTrainingDate();
//            }
//        });
//        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
//        lastTrainingDate.setText(formatter.format(lastDate[0]));
    }

    private void setName(){
        name.setText(dataBase.getName(login));
    }

    public void editAccount(ActionEvent actionEvent) throws Exception {
        stage.hide();
        EditAccount editAccount = new EditAccount(login, name.getText());
        editAccount.show();
    }

    public void beginnerPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Elementary);
    }

    public void easyPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Easy);
    }

    public void mediumPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Medium);
    }

    public void hardPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Hard);
    }

    public void veryHardPressed(ActionEvent actionEvent) {
        setListView(DifficultyLevel.Master);
    }

    private void setListView(DifficultyLevel difficultyLevel){
        String level = difficultyLevel.toRussian();
        String[] levelParameters = dataBase.getLevelSets(level);
        List<String[]> exerciseTextsWithId = dataBase.getExercises(difficultyLevel);
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        for (String[] exerciseText : exerciseTextsWithId) {
            exercises.add(new Exercise(Integer.parseInt(exerciseText[0]), exerciseText[1], difficultyLevel, Double.parseDouble(levelParameters[0]), Integer.parseInt(levelParameters[2])));
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
}
