package com.example.myapplication;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    public static void main(String[] args) {

        //getData();
        try
        {
            DB db = new DB();

            Connection conn = db.getPostgreSQLConnection();
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
            String mysqlConnUrl = "jdbc:postgresql://localhost:5432/Werkzeugausleihe";

            /* user name. */
            String mysqlUserName = "postgres";

            /* password. */
            String mysqlPassword = "postgresql";

            /* Get the Connection object. */
            ret = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);




            /* Get related meta data for this mysql server to verify db connect successfully..
            DatabaseMetaData dbmd = ret.getMetaData();

            String dbName = dbmd.getDatabaseProductName();

            String dbVersion = dbmd.getDatabaseProductVersion();

            String dbUrl = dbmd.getURL();

            String userName = dbmd.getUserName();

            String driverName = dbmd.getDriverName();

            System.out.println("Database Name is " + dbName);

            System.out.println("Database Version is " + dbVersion);

            System.out.println("Database Connection Url is " + dbUrl);

            System.out.println("Database User Name is " + userName);

            System.out.println("Database Driver Name is " + driverName); */

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            return ret;
        }
    }

    public static ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<String>();

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
        return data;
    }

}