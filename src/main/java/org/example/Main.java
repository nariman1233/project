package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
     String connectionUrl = "jdbc:postgresql://localhost:5432/bank1";
     ArrayList<User> users = new ArrayList<>();
     Connection con  = null;
     ResultSet rs = null;
     Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

             con = DriverManager.getConnection(connectionUrl , "postgres"
                    , "kyralai123");

             stmt = con.createStatement();

             rs = stmt.executeQuery("select * from users");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 String surname = rs.getString("surname");
                 boolean gender = rs.getBoolean("gender");

                User user = new User(id , name ,surname ,gender);
                users.add(user);
                 System.out.println(user);
             }
        } catch (Exception e) {
            System.out.println("Exception occurred");
        } finally {
            try {
                con.close();
            }catch (Exception e) {
                System.out.println("Exception occurred");
            }
        }

    }
}