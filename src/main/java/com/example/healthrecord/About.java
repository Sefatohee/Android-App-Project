package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about =(TextView) findViewById(R.id.idabout);
        about.setText("Developed By : Maraz and Ohee\nSpecial Guest : Rumida\nSome Acronym Used In This App :\n" +
                "SR = Sugar Level\nCC = Consumed Calories\nSR=Systolic Rate\nDS = Diastolic Rate\nN = Normal");
    }
}
