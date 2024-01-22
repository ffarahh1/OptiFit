package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Male_Femail_Activity extends AppCompatActivity {
    CardView cvM , cvF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_femail);
        cvM = findViewById(R.id.to_men_videos);
        cvF = findViewById(R.id.to_women_videos);

        cvM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Male_Femail_Activity.this, menExercisesActivity.class);
                intent.putExtra("Gender", "male");
                startActivity(intent);
            }
        });
        cvF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Male_Femail_Activity.this, menExercisesActivity.class);
                intent.putExtra("Gender", "female");

                startActivity(intent);
            }
        });
    }
}