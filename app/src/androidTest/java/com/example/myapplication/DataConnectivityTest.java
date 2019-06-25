package com.example.myapplication;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class DataConnectivityTest {

    @Test
    public void connectToDatabase() {
        final String DB_URL = "jdbc:postgresql://10.0.2.2:5432/Toolbox";
        final String USER = "postgres";
        final String PASS = "postgresql";

        /* Get the Connection object. */
        try {

            /* Register jdbc driver class. */
            Class.forName("org.postgresql.Driver");

            /* Get the Connection object. */
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM \"Inventar\".\"Werkzeug\"\n" +
                    "WHERE \"Werkzeug\".\"Werkzeug_ID\" = '0006';");
            assertEquals("Alexa", rs.getString("Maschinen_ID"));
        } catch (Exception e) {
            System.out.print(e);
        }

    }
}
