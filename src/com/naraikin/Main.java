package com.naraikin;

import java.sql.*;

public class Main {
    private static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PSQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        //Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/students";
        String login = "artur";
        String password = "12345";
        //insertDb(url, login, password);
        //selectDb(url, login, password);
        //updateStudent(url, login, password);
        //deleteStudent(url, login, password);
        //selectByName(url, login, password);


    }

    public static void insertDb(String url, String login, String password) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String sqlQ = "INSERT INTO student (id, name, age) " +
                    "Values (?,?,?)";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sqlQ);
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, "Artem");
            preparedStatement.setInt(3, 30);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void selectDb(String url, String login, String password) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement query = conn.createStatement();
            ResultSet resultSet = query.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void updateStudent(String url, String login, String password) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String pSql = "UPDATE student SET name=?, age=?" + "WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(pSql);
            preparedStatement.setString(1, "Arnold");
            preparedStatement.setInt(2, 29);
            preparedStatement.setInt(3, 5);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteStudent(String url, String login, String password) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String pSql = "DELETE FROM student WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(pSql);
            preparedStatement.setInt(1, 5);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static ResultSet selectId(String table, int id){
        try {
            String pSql = "SELECT * FROM" + table + " WHERE id=?";
            PreparedStatement preparedStatement =conn.prepareStatement(pSql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return preparedStatement.getResultSet();
        } catch (SQLException ex){
            ex.printStackTrace();
        } return null;
    }
    private static ResultSet selectByName(String table, String name){
        try {
            String pSql = "SELECT * FROM" + table + " WHERE id=?";
            PreparedStatement preparedStatement =conn.prepareStatement(pSql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            return preparedStatement.getResultSet();
        } catch (SQLException ex){
            ex.printStackTrace();
        } return null;
    }
    private static  void resultByStudent(ResultSet resultSet){
        try{
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("age"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void selectStdById(int id) {
        ResultSet resultSet = selectId("student", 5);
    }
    public static void slctStdByName(String name){
        ResultSet resultSet = selectByName("student", "Artem");
    }



}
