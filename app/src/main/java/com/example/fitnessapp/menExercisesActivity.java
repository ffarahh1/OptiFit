package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class menExercisesActivity extends AppCompatActivity {

    private List<String> exerciseCategory;
    private List<String> ImgeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_exercises);
        LinearLayout linearLayout = findViewById(R.id.linear_layout_men_list);
        LayoutInflater inflater = LayoutInflater.from(this);
        exerciseCategory = new ArrayList<>();
        ImgeName = new ArrayList<>();
        String gender = getIntent().getStringExtra("Gender");
        String src_file_name;
        int slice;
        if (gender.equals("male")){
            src_file_name = "men_exercises_list.csv";
            slice = 4;
        }else{
            //System.out.println("elseelseelseelse");
            src_file_name = "women_exercises_list.csv";
            slice = 6;
        }
        //for (int i =0;i<20;i++){System.out.println(src_file_name);}
//        System.out.println(src_file_name);
        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open(src_file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    exerciseCategory.add(parts[0]);
                    ImgeName.add(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (int i =0;i<20;i++){System.out.println("catch");}
        }
        String underText = "";
        String FileName;
        int Ln = underText.length();
        for (int i = 0; i < 13; i++) {
            View cardView = inflater.inflate(R.layout.card_layout, linearLayout, false);

            ImageView img = cardView.findViewById(R.id.Card_Image_id);
            int imageId = getResources().getIdentifier(ImgeName.get(i), "drawable", getPackageName());
            //System.out.println(ImgeName.get(i));
            img.setImageResource(imageId);

            TextView textView = cardView.findViewById(R.id.card_text);
            underText = exerciseCategory.get(i);//.split(".")[0];
            FileName = exerciseCategory.get(i);
            Ln = underText.length();
            underText = underText.substring(slice,Ln-4);
            textView.setText(underText);

            //System.out.println();
            final String finalFileName = FileName;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //System.out.println(finalFileName);
                    Intent intent = new Intent(menExercisesActivity.this, Videos_Lister.class);
                    intent.putExtra("FileNme", finalFileName);
                    startActivity(intent);
                }
            });

            linearLayout.addView(cardView);
        }
    }
}