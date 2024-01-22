package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private CardView cardView1, cardView2, cardView3, cardView4, cardView5,cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






            cardView1 = findViewById(R.id.cv1);
            cardView2 = findViewById(R.id.cv2);
            cardView3 = findViewById(R.id.cv3);
            cardView4 = findViewById(R.id.cv4);
            cardView5 = findViewById(R.id.cv5);
            cardView6 = findViewById(R.id.cv6);


            cardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Male_Femail_Activity.class));
                }
            });

         cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, resp_list.class));
            }
        });

            cardView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ai_chatbot_test1.class));
                }
            });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdealWeightActivity.class));
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cards_videos_list.class));
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FindCoach.class));
            }
        });

    }
}