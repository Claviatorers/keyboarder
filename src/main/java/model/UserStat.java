package model;

import java.util.Date;
import java.util.Map;

/**
 * Created by Александр on 17.12.2018 in 0:34.
 */
public class UserStat {
    private final String userLogin;
    private Date lastTrainingDate;
    private final Map<Integer, ExerciseStat> averageTimeForExercises;

    public UserStat(String userLogin, Date lastTrainingDate, Map<Integer, ExerciseStat> averageTimeForExercises) {
        this.userLogin = userLogin;
        this.lastTrainingDate = lastTrainingDate;
        this.averageTimeForExercises = averageTimeForExercises;
    }

    public void setLastTrainingDate(Date lastTrainingDate) {
        this.lastTrainingDate = lastTrainingDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public Date getLastTrainingDate() {
        return lastTrainingDate;
    }

    public Map<Integer, ExerciseStat> getAverageTimeForExercises() {
        return averageTimeForExercises;
    }
}
