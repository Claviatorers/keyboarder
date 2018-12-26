package model;

import java.util.Date;


public class Result {
    private User user;
    private long milliseconds;
    private long averageMilliseconds;
    private int exceptions;
    private ExerciseDao exercise;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (milliseconds != result.milliseconds) return false;
        if (averageMilliseconds != result.averageMilliseconds) return false;
        if (exceptions != result.exceptions) return false;
        if (user != null ? !user.equals(result.user) : result.user != null) return false;
        return exercise != null ? exercise.equals(result.exercise) : result.exercise == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (int) (milliseconds ^ (milliseconds >>> 32));
        result = 31 * result + (int) (averageMilliseconds ^ (averageMilliseconds >>> 32));
        result = 31 * result + exceptions;
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        Role role = new Role();
        return "Result{" +
                "user=" + user +
                ", milliseconds=" + milliseconds +
                ", averageMilliseconds=" + averageMilliseconds +
                ", exceptions=" + exceptions +
                ", exercise=" + exercise + role +
                '}';
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getAverageMilliseconds() {
        return averageMilliseconds;
    }

    public void setAverageMilliseconds(long averageMilliseconds) {
        this.averageMilliseconds = averageMilliseconds;
    }

    public int getExceptions() {
        return exceptions;
    }

    public void setExceptions(int exceptions) {
        this.exceptions = exceptions;
    }

    public ExerciseDao getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseDao exercise) {
        this.exercise = exercise;
    }

}
