package common;

import java.sql.*;
import java.util.Properties;

public class DataBase {
    private static final String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11194797";
    private static final String user = "sql11194797";
    private static final String password = "L9HUrPWabh";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Properties p;

    public DataBase(){

    }

    public void registration(String name, String login, String password, String password2) {
        p = new Properties();
        p.setProperty("user",user);
        p.setProperty("password", this.password);
        p.setProperty("useUnicode","true");
        p.setProperty("characterEncoding","cp1251");
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


    public static void main(String args[]) throws SQLException {
        DataBase d = new DataBase();
        System.out.println(d.getPassword("john"));
    }
}
