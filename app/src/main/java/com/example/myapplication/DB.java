package com.example.myapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB extends Thread {

    private List<String> data;
    private boolean done = false;

    public boolean done() {
        return this.done;
    }

    public List<String> getData() {
        return this.data;
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<String>();
        try
        {
            DB db = new DB();
            Connection conn = db.getPostgreSQLConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM test.table1";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                data.add(results.getString("name"));
            }

            for (int i =0; i<data.size(); i++){
                System.out.println(data.get(i));
            }


            statement.close();
            results.close();
            conn.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /* This method return java.sql.Connection object. */
    public Connection getPostgreSQLConnection()
    {
        /* Declare and initialize a sql Connection variable. */
        Connection ret = null;

        try
        {
            /* Register jdbc driver class. */
            Class.forName("org.postgresql.Driver");

            /* Create connection url. */
            String mysqlConnUrl = "jdbc:postgresql://10.0.2.2:5432/Werkzeugausleihe";

            /* user name. */
            String mysqlUserName = "postgres";

            /* password. */
            String mysqlPassword = "postgresql";

            /* Get the Connection object. */
            ret = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            return ret;
        }
    }

    public void run() {
        this.data = new ArrayList<String>();

        try {
            DB db = new DB();
            Connection conn = db.getPostgreSQLConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM test.table1";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                data.add(results.getString("name"));
            }

            for (int i =0; i<data.size(); i++){
                System.out.println(data.get(i));
            }


            statement.close();
            results.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.done = true;

    }



}