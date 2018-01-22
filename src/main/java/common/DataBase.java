package common;

import client.DifficultyLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DataBase {
    private static final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11194797";
    private static final String user = "sql11194797";
    private static final String password = "L9HUrPWabh";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String DATE_SQL = "%Y-%m-%d";
    private static final DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Properties p;

    public DataBase(){
        p = new Properties();
        p.setProperty("user",user);
        p.setProperty("password", this.password);
        p.setProperty("useUnicode","true");
        p.setProperty("characterEncoding","cp1251");
    }

    public void registration(String name, String login, String password, String password2) {
        String query = "INSERT INTO User (Login, Name, Password) \n VALUES ('" + login + "', '" + name + "', '" + password2 + "');";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void addExercise(int levelNum, String text){
        String query = "INSERT INTO Execirse (Number_difficulty_level, Text_execirse, Number_execirse) \n VALUES ('" + levelNum + "', '" + text + "', '" + 0 + "');";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void editExercise(int levelNum, String text, String newText){
        String query = "UPDATE Execirse SET Text_execirse ='" + newText + "' WHERE Number_difficulty_level = '" + levelNum + "' AND Text_execirse ='" + text + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void deleteExercise(int levelNum, String text){
        String query = "DELETE FROM Execirse WHERE Number_difficulty_level = '" + levelNum + "' AND Text_execirse ='" + text + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void deleteUser(String login){
        String query = "DELETE FROM Statistics WHERE Login = '" + login + "';";
        String query2 = "DELETE FROM User WHERE Login = '" + login + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void editUserName(String login, String name){
        String query = "UPDATE User SET name ='" + name + "' WHERE Login = '" + login + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public boolean isExistLogin(String log){
        int count = 0;
        String query = "select * from User WHERE Login = '" + log + "'";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                count++;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return (count>0);
    }

    public String getPassword(String login){
        String result="";
        String query = "select password from User WHERE Login = '" + login + "'";
        try {
            con = DriverManager.getConnection(url, user, this.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getString("password");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return result;
    }

    public String getName(String login){
        String result="";
        String query = "select name from User WHERE Login = '" + login + "'";
        try {
            con = DriverManager.getConnection(url, user, this.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getString("name");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return result;
    }

    public String[] getLevelSets(String level){
        String[] sets = new String[3];
        String query = "select Time_of_character_pressing, Max_length_execirse, Percentage_of_errors from Difficulty_level WHERE Name_level = '" + level + "'";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            sets[0] = rs.getString("Time_of_character_pressing");
            sets[1] = rs.getString("Max_length_execirse");
            sets[2] = rs.getString("Percentage_of_errors");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return sets;
    }

    public String[] getCharAndLength(int levelNum){
        String[] sets = new String[2];
        String query = "select Max_length_execirse, Allowable_characters from Difficulty_level WHERE Number_difficulty_level = '" + levelNum + "'";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            sets[0] = rs.getString("Allowable_characters");
            sets[1] = rs.getString("Max_length_execirse");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return sets;
    }

    public void setLevelSets(String level, Double time, Integer length, Integer mistakes ){
        String query = "UPDATE Difficulty_level SET Time_of_character_pressing = " + time + ", Max_length_execirse = " + length + ", Percentage_of_errors = " + mistakes + " WHERE Name_level = '" + level + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public void changePassword(String login, String newPassword){
        String query = "UPDATE User SET password = '" + newPassword + "' WHERE login = '" + login + "';";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public List<String[]> getExercises(DifficultyLevel difficultyLevel) {
        switch (difficultyLevel) {
            case Elementary : return getExercisesWithId(1);
            case Easy: return getExercisesWithId(2);
            case Medium: return getExercisesWithId(3);
            case Hard: return getExercisesWithId(4);
            case Master: return getExercisesWithId(5);
            default: throw new IllegalArgumentException("Неверный уровень сложности");
        }
    }

    public List<String[]> getExercisesWithId(int level){
        List<String[]> exerciseList = new ArrayList<>();
        String query = "select Text_execirse, ID_execirse from Execirse WHERE Number_difficulty_level = '" + level + "'";
        try {
            con = DriverManager.getConnection(url, user, this.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            String[] exerciseWithId;
            while (rs.next()) {
                exerciseWithId = new String[2];
                exerciseWithId[0] = rs.getString("ID_execirse");
                exerciseWithId[1] = rs.getString("Text_execirse");
                exerciseList.add(exerciseWithId);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return exerciseList;
    }

    public ObservableList<String> getExercises(int level){
        ObservableList<String> exercise = FXCollections.observableArrayList();
        String query = "select Text_execirse from Execirse WHERE Number_difficulty_level = '" + level + "'";
        try {
            con = DriverManager.getConnection(url, user, this.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                exercise.add(rs.getString("Text_execirse"));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return exercise;
    }

    public ObservableList<String> getUsers(){
        ObservableList<String> exercise = FXCollections.observableArrayList();
        String query = "select login from User WHERE login != 'admin'";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                exercise.add(rs.getString("login"));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return exercise;
    }

    public List<UserInfo> getUserInformation(String login) {
        List<UserInfo> userInfoList = new ArrayList<>();
        String query = "select train_date, number_difficulty_level from Statistics, Execirse " +
                "WHERE Execirse.id_execirse = Statistics.id_execirse AND '" + login + "' = Statistics.login";
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(rs.getString("number_difficulty_level"));
                Date trainingDate = rs.getDate("train_date");
                UserInfo userInfo = new UserInfo(trainingDate, difficultyLevel);
                userInfoList.add(userInfo);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return userInfoList;
    }

    public void saveStatistic(Date trainingDate, int mistakes, int time, String login, int id){
        String query = "INSERT INTO Statistics(Train_Date, Err_count, Time, Login, ID_execirse, Score) " +
                "VALUES (STR_TO_DATE('" + dateFormat.format(trainingDate) + "','" + DATE_SQL + "')," + mistakes + "," + time + ",'" + login + "'," + id + ",0)";
        executeUpdate(query);
    }

    public void saveStatistic(Date trainingDate, int score, String login, int id){
        String query = "INSERT INTO Statistics(Train_Date, Score, Login, ID_execirse, Time, Err_count) " +
                "VALUES (STR_TO_DATE('" + dateFormat.format(trainingDate) + "','" + DATE_SQL + "')," + score + ",'" + login + "'," + id + ",0,0)";
        executeUpdate(query);
    }

    private void executeUpdate(String query) {
        try {
            con = DriverManager.getConnection(url, p);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }



    public static void main(String args[]) throws SQLException {
        DataBase d = new DataBase();
        String[] res = d.getCharAndLength(1);
        for (String el: res) {
            System.out.println(el);
        }

    }
}
