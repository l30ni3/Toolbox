package com.example.myapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBThreadVersuch extends Thread {

    private List<String> data;
    private boolean done = false;

    public boolean done() {return this.done;}

    public List<String> getData() {
        return this.data;
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
