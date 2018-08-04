package com.xd.controller;

import java.sql.*;

public class TestJdbc {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";

    static final String userName = "xudai";
    static final String password = "a123";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting Database...");
            conn = DriverManager.getConnection(DB_URL, userName, password);

            System.out.println("Statement...");
            stmt = conn.createStatement();
            String sqlStr;
            sqlStr = "SELECT user_id, user_name, credits FROM t_user";
            ResultSet rs = stmt.executeQuery(sqlStr);

            while(rs.next()){
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                int credits = rs.getInt("credits");

                System.out.println("ID: " + user_id);
                System.out.println("Name: " + user_name);
                System.out.println("Credits: " + credits);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null) stmt.close();
            }catch(SQLException se2){

            }
            try{
                if(conn != null) conn.close();
            }catch (SQLException se3){
                se3.printStackTrace();
            }
        }
        System.out.println("GoodBye!");
    }
}
