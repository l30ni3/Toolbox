package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tools);


        // declaration and initialize String Array  
        String listString = "";

        // Start database connection in new thread
        DB d = new DB();
        d.start();

        // Polling - to do: Future!
        while(!d.done()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> data = d.getData();
        String str[] = new String[data.size()];
        for (int j = 0; j < data.size(); j++) {

            // Assign each value to String array
            str[j] = data.get(j);
        }

        listString = Arrays.toString(str);

        TextView dataView = findViewById(R.id.data);
        dataView.setText(listString);
    }
}
