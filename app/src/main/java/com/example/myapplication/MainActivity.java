package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user taps the 'Werkzeuge' button */
    public void showAll(View view) {
        Intent intent = new Intent(this, ShowAllTools.class);
        startActivity(intent);
    }

    /** Called when the user taps the 'Maschinen' button */
    public void showAllMachines(View view) {
        Intent intent = new Intent(this, ShowAllMachines.class);
        startActivity(intent);
    }

    /** Called when the user taps the 'Maschinen' button */
    public void showAllMaterial(View view) {
        Intent intent = new Intent(this, ShowAllMaterial.class);
        startActivity(intent);
    }

}