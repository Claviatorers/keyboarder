package common;

import admin.userAccounts.Statistic;
import callback.Callback;
import client.DifficultyLevel;
import client.Exercise;
import client.userStatistic.StatisticView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import jsonUtil.ListOfGeneric;
import model.Difficulty;
import model.ExerciseDao;
import model.ExerciseStat;
import model.User;
import model.UserStat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 15.12.2018 in 14:08.
 */
@SuppressWarnings("ALL")
public class JsonFileHelper {
    private static final String ERR_READ_USER_STATS = "Ошибка при чтении из файла статистики пользователей!";
    private static final String ERR_WRITE_USER_STAT = "Ошибка при записи в файл статистики пользователей!";
    private static final String ERR_READ_EXERCISE_STATS = "Ошибка при чтении файла статистики упражнений!";
    private static final String ERR_WRITE_EXERCISES_STATS = "Ошибка при записи в файл статистики упражнений!";
    private static final String ERR_WRITE_DIFFICULTIES = "Ошибка при записи в файл сложности упражнений!";
    private static JsonFileHelper instance = null;

    private static final File DIR = new File("files");
    private static final File USERS;
    private static final File DIFFICULTIES;
    private static final File EXERCISES;
    private static final File STATS_USER;
    private static final File STATS_EXERCISES;

    // error messages
    private static final String ERR_EXERCISE_NOT_FOUND = "Упражнение не найдено!";
    private static final String ERR_USER_READ = "Ошибка при чтении файла пользователя!";
    private static final String ERR_USER_WRITE = "Ошибка при записи пользователей!";
    private static final String ERR_USER_NOT_FOUND = "Пользователь не найден!";
    private static final String ERR_READ_EXERCISE = "Ошибка при чтении файла упражений!";
    private static final String ERR_WRITE_EXERCISES = "Ошибка при записи в файл упражений!";
    private static final String ERR_READ_DIFFICULTIES = "Ошибка при чтении файла сложностей!";

    static {
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        if (!DIR.exists()) {
            DIR.mkdir();
        }
        try {
            USERS = createFileIfNotExist("users.json");
            DIFFICULTIES = createFileIfNotExist("difficulties.json");
            if (readFromJson(DIFFICULTIES, Difficulty.class).isEmpty()){
                List<Difficulty> difficulties = new ArrayList<>();
                String easyChars = "фываолдж";
                String mediumChars = easyChars + "енприткгмь";
                String hardChars = mediumChars + "ячсбю.йцукгшщзхъэё";
                Difficulty easy = new Difficulty(DifficultyLevel.Easy, 1.0, 10, 10, easyChars);
                Difficulty normal = new Difficulty(DifficultyLevel.Medium, 0.9, 9, 15, mediumChars);
                Difficulty hard = new Difficulty(DifficultyLevel.Hard, 0.8, 8, 20, hardChars);
                difficulties.add(easy);
                difficulties.add(normal);
                difficulties.add(hard);
                writeToJson(difficulties, DIFFICULTIES);
            }
            EXERCISES = createFileIfNotExist("excercises.json");
            STATS_USER = createFileIfNotExist("stats_user.json");
            STATS_EXERCISES = createFileIfNotExist("stats_exercise.json");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Ошибка при создании JSON файлов", e);
        }
    }

    public static synchronized JsonFileHelper getInstance() {
        if (instance == null) {
            instance = new JsonFileHelper();
        }

        return instance;
    }

    private static final Gson gson;

    private static File createFileIfNotExist(String filePath) throws IOException {
        File file = new File(DIR, filePath);
        if (!file.exists()) {
            file.createNewFile();
            writeToJson(new ArrayList<>(), file);
        }

        return file;
    }

    public void addUser(User user, Callback<User> callback) {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFail("Ошибка при чтении файла пользователя!");
            return;
        }

        for (User existedUser : users) {
            if (existedUser.getLogin().equals(user.getLogin())) {
                callback.onFail("Пользователь с таким логином уже существует");
                return;
            }
        }

        users.add(user);
        try {
            writeToJson(users, USERS);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFail("Ошибка при записи файла пользователей!");
        }

        callback.onSuccess(user);
    }

    private static <T> void writeToJson(List<T> existedElements, File filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(gson.toJson(existedElements));
        writer.close();
    }

    private static <T> List<T> readFromJson(File filePath, Class<T> elementClass) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<T> elementsList = gson.fromJson(reader.readLine(), new ListOfGeneric<T>(elementClass));
        reader.close();

        if (elementsList == null)
            elementsList = new ArrayList<>();
        return elementsList;
    }

    public void deleteUser(String login) {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            throw new Error(ERR_USER_READ);
        }

        User user = null;
        for (User u : users) {
            if (u.getLogin().equals(login)){
                user = u;
                break;
            }
        }

        if (users.remove(user)) {
            try {
                writeToJson(users, USERS);
            } catch (IOException e) {
                throw new Error(ERR_USER_NOT_FOUND);
            }
        }
    }

    public void updateUser(User oldUser, User newUser, Callback callback) {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            callback.onFail(ERR_USER_READ);
            e.printStackTrace();
            return;
        }

        if (users.contains(oldUser)) {
            int index = users.indexOf(oldUser);
            users.set(index, newUser);
            try {
                writeToJson(users, USERS);
            } catch (IOException e) {
                callback.onFail(ERR_USER_WRITE);
                e.printStackTrace();
            }
        } else {
            callback.onFail(ERR_USER_NOT_FOUND);
        }
    }

    public void addExercie(String exerciseText, DifficultyLevel difficultyLevel) {
        List<ExerciseDao> exercises = null;
        try {
            exercises = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }

        int id;
        if (!exercises.isEmpty()){
            id = exercises.get(exercises.size() - 1).getId() + 1;
        } else {
            id = 0;
        }
        ExerciseDao exercise = new ExerciseDao(id, exerciseText, difficultyLevel);
        exercises.add(exercise);
        try {
            writeToJson(exercises, EXERCISES);
        } catch (IOException e) {
            throw new Error(ERR_WRITE_EXERCISES);
        }
    }

    public void updateExercise(int oldExerciseId, String newText, DifficultyLevel difficultyLevel) {
        List<ExerciseDao> exercises = null;
        try {
            exercises = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }

        ExerciseDao oldExercise = null;
        for (ExerciseDao ex : exercises) {
            if (ex.getId() == oldExerciseId){
                oldExercise = ex;
            }
        }
        if (oldExercise != null) {
            int indexOfExcerciseToChange = exercises.indexOf(oldExercise);
            ExerciseDao newExercise = new ExerciseDao(oldExerciseId, newText, difficultyLevel);
            exercises.set(indexOfExcerciseToChange, newExercise);
            try {
                writeToJson(exercises, EXERCISES);
            } catch (IOException e) {
                throw new Error(ERR_WRITE_EXERCISES);
            }
        }
    }

    public void deleteExerciseById(int exerciseId) {
        List<ExerciseDao> exercises = null;
        try {
            exercises = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }

        ExerciseDao exercise = null;
        for (ExerciseDao exerciseDao: exercises) {
            if (exerciseDao.getId() == exerciseId){
                exercise = exerciseDao;
                break;
            }
        }
        if (exercises.remove(exercise)) {
            try {
                writeToJson(exercises, EXERCISES);
            } catch (IOException e) {
                throw new Error(ERR_WRITE_EXERCISES);
            }
        }
    }

    public User getUserByLogin(String login) {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            throw new Error(ERR_USER_READ, e);
        }

        for (User user : users) {
            if (user.getLogin().equals(login)){
                return user;
            }
        }

        return null;
    }

    public Difficulty getDifficultyByLevel(DifficultyLevel level) {
        List<Difficulty> difficulties = null;
        try {
            difficulties = readFromJson(DIFFICULTIES, Difficulty.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_DIFFICULTIES);
        }

        for (Difficulty difficulty: difficulties){
            if (difficulty.getLevel().equals(level)) {
                return difficulty;
            }
        }

        return null;
    }

    public List<ExerciseDao> getExercisesByLevel(DifficultyLevel difficultyLevel) {
        List<ExerciseDao> exerciseDaos = null;
        try {
            exerciseDaos = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }
        List<ExerciseDao> filteredExercises = new ArrayList<>();
        for (ExerciseDao ex : exerciseDaos) {
            if (ex.getDifficulty().equals(difficultyLevel)){
                filteredExercises.add(ex);
            }
        }

        return filteredExercises;
    }

    public void updateStats(Date trainingDate, int usedTime, String login, int exerxiseId) {
        List<UserStat> userStats = null;
        try {
            userStats = readFromJson(STATS_USER, UserStat.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_USER_STATS);
        }

        UserStat userStatToUpdate = null;
        for (UserStat stat : userStats) {
            if (stat.getUserLogin().equals(login)){
                userStatToUpdate = stat;
                break;
            }
        }
        if (userStatToUpdate == null){
            userStatToUpdate = new UserStat(login, trainingDate, new HashMap<>());
        }

        userStatToUpdate.setLastTrainingDate(trainingDate);

        List<ExerciseStat> stats = new ArrayList<>();
        stats.addAll(userStatToUpdate.getAverageTimeForExercises().values());

        ExerciseStat exerciseStatToUpdate = userStatToUpdate.getAverageTimeForExercises().get(exerxiseId);
        ExerciseStat updatedExerciseStat = updateExerciseAverageTimeOrCreate(exerciseStatToUpdate, usedTime, stats);

        Map<Integer, ExerciseStat> updatedExerciseStatMap = userStatToUpdate.getAverageTimeForExercises();
        updatedExerciseStatMap.put(updatedExerciseStat.getExerciseId(), updatedExerciseStat);

        UserStat updatedUserStat = new UserStat(
                userStatToUpdate.getUserLogin(),
                trainingDate,
                updatedExerciseStatMap
        );

        int indexOfStatToUpdate = userStats.indexOf(userStatToUpdate);
        if (indexOfStatToUpdate != -1)
            userStats.set(indexOfStatToUpdate, updatedUserStat);
        else
            userStats.add(updatedUserStat);

        try {
            writeToJson(userStats, STATS_USER);
        } catch (IOException e) {
            throw new Error(ERR_WRITE_USER_STAT);
        }

        // update exercise stats
        List<ExerciseStat> exerciseStats = null;
        try {
            exerciseStats = readFromJson(STATS_EXERCISES, ExerciseStat.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE_STATS);
        }

        ExerciseStat exerciseStat = null;
        for (ExerciseStat stat : exerciseStats) {
            if (stat.getExerciseId() == exerxiseId){
                exerciseStat = stat;
                break;
            }
        }

        ExerciseStat updatedStat = updateExerciseAverageTimeOrCreate(exerciseStat, usedTime, exerciseStats);
        int index = exerciseStats.indexOf(exerciseStat);
        if (index != -1){
            exerciseStats.set(index, updatedStat);
        } else {
            exerciseStats.add(updatedStat);
        }

        try {
            writeToJson(exerciseStats, STATS_EXERCISES);
        } catch (IOException e) {
            throw new Error(ERR_WRITE_EXERCISES_STATS);
        }
    }

    private ExerciseStat updateExerciseAverageTimeOrCreate(ExerciseStat oldExerciseStat, int timeToAdd, List<ExerciseStat> exercises){
        if (oldExerciseStat != null) {
            int previousAverageTime = oldExerciseStat.getAverageTime();
            int previousCompletedTimes = oldExerciseStat.getCompletedTimes();

            int nowCompletedTimes = previousCompletedTimes + 1;
            int nowAverageTime = (previousAverageTime * previousCompletedTimes + timeToAdd) / nowCompletedTimes;
            return new ExerciseStat(
                    oldExerciseStat.getExerciseId(), nowAverageTime, nowCompletedTimes);
        } else {
            int id;
            if (!exercises.isEmpty()){
                id = exercises.get(exercises.size() - 1).getExerciseId() + 1;
            } else {
                id = 0;
            }
            return new ExerciseStat(
                    id, timeToAdd, 1);
        }
    }

    public Date getLastTrainingDate(String login) {
        List<UserStat> userStats = null;
        try {
            userStats = readFromJson(STATS_USER, UserStat.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_USER_STATS);
        }

        for (UserStat stat : userStats) {
            if (stat.getUserLogin().equals(login)){
                return  stat.getLastTrainingDate();
            }
        }

        return null;
    }

    public void editUserName(String login, String newName) {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            throw new Error(ERR_USER_READ);
        }

        User user = null;
        for (User u : users) {
            if(u.getLogin().equals(login)){
                user = u;
                break;
            }
        }

        if (user != null){
            int index = users.indexOf(user);
            User newUser = new User(login, newName);
            users.set(index, newUser);
            try {
                writeToJson(users, USERS);
            } catch (IOException e) {
                throw new Error(ERR_USER_WRITE);
            }
        }
    }

    public List<StatisticView> getUserStatisticWithExerciseNames(String login) {
        List<UserStat> userStats = null;
        try {
            userStats = readFromJson(STATS_USER, UserStat.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_USER_STATS);
        }

        UserStat userStat = null;
        for (UserStat u : userStats) {
            if (u.getUserLogin().equals(login)){
                userStat = u;
                break;
            }
        }
        List<ExerciseDao> exercises = null;
        try {
            exercises = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }

        List<StatisticView> statistics = new ArrayList<>();
        if (userStat != null){
            Set<Integer> keys = userStat.getAverageTimeForExercises().keySet();

            for (int id : keys) {
                ExerciseDao exerciseDao = null;
                for (ExerciseDao ex : exercises) {
                    if (ex.getId() == id){
                        exerciseDao = ex;
                    }
                }
                if (exerciseDao != null) {
                    StatisticView statistic = new StatisticView(exerciseDao.getDifficulty().toRussian(), exerciseDao.getText(),
                            userStat.getAverageTimeForExercises().get(id).getAverageTime());
                    statistics.add(statistic);
                }
            }
        }

        return statistics;
    }

    public void updateDifficulty(DifficultyLevel difficultyLevel, Double keyPressTime, Integer maxLength, Integer mistakePercent) {
        List<Difficulty> difficulties = null;
        try {
            difficulties = readFromJson(DIFFICULTIES, Difficulty.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_DIFFICULTIES);
        }

        Difficulty difficultyToUpdate = null;

        for (Difficulty difficulty: difficulties){
            if (difficulty.getLevel().equals(difficultyLevel)) {
                difficultyToUpdate = difficulty;
            }
        }

        if (difficultyToUpdate != null){
            int index = difficulties.indexOf(difficultyToUpdate);
            Difficulty updatedDifficulty = new Difficulty(difficultyLevel, keyPressTime, mistakePercent, maxLength, difficultyToUpdate.getAvailableChars());

            difficulties.set(index, updatedDifficulty);
            try {
                writeToJson(difficulties, DIFFICULTIES);
            } catch (IOException e) {
                throw new Error(ERR_WRITE_DIFFICULTIES);
            }
        }
    }

    public List<String> getUsersLogins() {
        List<User> users = null;
        try {
            users = readFromJson(USERS, User.class);
        } catch (IOException e) {
            throw new Error(ERR_USER_READ);
        }
        List<String> logins = new ArrayList<>();
        for (User user : users) {
            logins.add(user.getLogin());
        }
        return logins;
    }

    public List<StatisticView> getShareStatistic() {
        List<ExerciseStat> stats = null;
        try {
            stats = readFromJson(STATS_EXERCISES, ExerciseStat.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE_STATS);
        }

        List<ExerciseDao> exercises = null;
        try {
            exercises = readFromJson(EXERCISES, ExerciseDao.class);
        } catch (IOException e) {
            throw new Error(ERR_READ_EXERCISE);
        }

        List<StatisticView> statisticViewList = new ArrayList<>();

        for (ExerciseStat stat : stats) {
            for (ExerciseDao exercise : exercises) {
                if (stat.getExerciseId() == exercise.getId()){
                    statisticViewList.add(new StatisticView(exercise.getDifficulty().toRussian(), exercise.getText(), stat.getAverageTime()));
                }
            }
        }

        return statisticViewList;
    }
}
