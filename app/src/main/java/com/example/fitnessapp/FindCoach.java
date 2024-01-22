package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
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

public class FindCoach extends AppCompatActivity {
    private List<String> namesList;
    private List<String> phonesList;
    private List<String> emailsList;
    private List<String> ImgeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_coach);

        LinearLayout linearLayout = findViewById(R.id.linear_layout_find_coach);
        LayoutInflater inflater = LayoutInflater.from(this);

        namesList= new ArrayList<>();
        phonesList= new ArrayList<>();
        emailsList= new ArrayList<>();
        ImgeName= new ArrayList<>();
        String src_file_name = "coaches_infos.csv";

        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open(src_file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    namesList.add(parts[0]);
                    phonesList.add(parts[1]);
                    emailsList.add(parts[2]);
                    ImgeName.add(parts[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (int i =0;i<20;i++){System.out.println("catch");}
        }

        for (int i=0; i<namesList.size();i++){
            View cardView = inflater.inflate(R.layout.coach_info_card, linearLayout, false);

            ImageView img = cardView.findViewById(R.id.Card_Image_coach_id);
            int imageId = getResources().getIdentifier(ImgeName.get(i), "drawable", getPackageName());
            //System.out.println(ImgeName.get(i));
            img.setImageResource(imageId);

            TextView textViewName = cardView.findViewById(R.id.Coach_name);
            TextView textViewPhone = cardView.findViewById(R.id.Coach_phone);
            TextView textViewEmail = cardView.findViewById(R.id.Coach_Email);
            textViewName.setText(namesList.get(i));
            textViewPhone.setText(phonesList.get(i));
            textViewEmail.setText(emailsList.get(i));
            final int x = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Created new Card");
                    System.out.println(namesList.get(x)+phonesList.get(x)+emailsList.get(x)+ImgeName.get(x));
                    System.out.println(phonesList.get(x));
                    System.out.println(phonesList.get(x));
                    System.out.println(phonesList.get(x));
                    System.out.println(phonesList.get(x));

                    String number = "tel:" + phonesList.get(x);
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                    if (ActivityCompat.checkSelfPermission(FindCoach.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(FindCoach.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return;
                    }
                    startActivity(callIntent);

//                    Intent intent = new Intent(menExercisesActivity.this, Videos_Lister.class);
//                    intent.putExtra("FileNme", finalFileName);
//                    startActivity(intent);
                }
            });

            linearLayout.addView(cardView);

        }



    }
}