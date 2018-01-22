package client.menu;

import client.DifficultyLevel;
import client.Exercise;
import client.Mode;
import client.training.TrainingForm;
import common.DataBase;
import common.UserInfo;
import common.auth.Authorization;
import common.changePassword.ChangePassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ClientMenuController {
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
                TrainingForm trainingForm = new TrainingForm((Exercise) newValue, Mode.Time);
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
    public void changePassword(ActionEvent actionEvent) throws Exception {
        stage.close();
        ChangePassword changePassword = new ChangePassword(login);
        changePassword.show();
    }

    void setLogin(String login){
        this.login = login;
        setName();
        List<UserInfo> userInfoList = dataBase.getUserInformation(login);
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
        ObservableList<String> exerciseTexts = dataBase.getExercises(difficultyLevel);
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        for (String exerciseText : exerciseTexts) {
            exercises.add(new Exercise(exerciseText, difficultyLevel, Double.parseDouble(levelParameters[0]), Integer.parseInt(levelParameters[2])));
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
}
