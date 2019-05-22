package com.example.myapplication;

import java.sql.*;

public class DB {

    public static void main(String[] args) {

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



            // CREATE TABLE!!!!!!!!!!!!!

            System.out.println("Creating table in given database...");

            Statement stmt = ret.createStatement();

            String sql = "CREATE TABLE TEST " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            // CREATE TABLE ENDE!!!!!!



            /* Get related meta data for this mysql server to verify db connect successfully.. */
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

            System.out.println("Database Driver Name is " + driverName);

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }finally
        {
            return ret;
        }
    }
}