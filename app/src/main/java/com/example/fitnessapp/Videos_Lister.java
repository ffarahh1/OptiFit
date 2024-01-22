package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Videos_Lister extends AppCompatActivity {

    private ListView listView;
    private List<String> exerciseNames;
    private List<String> exerciseUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_lister);
        String FileName = getIntent().getStringExtra("FileNme");

        listView = findViewById(R.id.listView_videos);
        exerciseNames = new ArrayList<>();
        exerciseUrls = new ArrayList<>();
        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open(FileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    exerciseNames.add(parts[0]);
                    exerciseUrls.add(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Videos_Lister.this, VideoPlayerActivity.class);
                intent.putExtra("videoUrl", exerciseUrls.get(position));
                startActivity(intent);
            }
        });
    }
}