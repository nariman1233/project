package data;

import data.Interface.IDB;

import java.sql.*;
public class PostgreDB implements IDB {

    public Connection getConnection() throws SQLException, ClassNotFoundException{
    String connectionUrl = "jdbc:postgresql://localhost:5432/bank1";
                try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "kyralai123");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

