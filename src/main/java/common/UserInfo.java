package common;

import client.DifficultyLevel;

import java.util.Date;

/**
 * Created by Александр on 22.01.2018 in 12:51.
 */
public class UserInfo {
    private Date trainingDate;
    private DifficultyLevel difficultyLevel;

    public UserInfo(Date trainingDate, DifficultyLevel difficultyLevel) {
        this.trainingDate = trainingDate;
        this.difficultyLevel = difficultyLevel;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
