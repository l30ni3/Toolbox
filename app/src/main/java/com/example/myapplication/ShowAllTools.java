package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowAllTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tools);

        ArrayList<String> list = new ArrayList<String>();
        //list.addAll(DB.getData());
        for (int i = 0; i < DB.getData().size(); i++) {
            list.add(i, DB.getData().get(i));
        }


        String listString = "";

        for (String s : list)
        {
            listString += s + "\t";
        }

        System.out.println(listString);


        //ArrayList<String> dataString = DB.getData();
        TextView data = findViewById(R.id.data);

        data.setText(listString);
    }
}
