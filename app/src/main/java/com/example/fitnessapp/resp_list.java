package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.res.AssetManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class resp_list extends AppCompatActivity {

    private ListView listView;
    private List<String> pdfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resp_list);

        listView = findViewById(R.id.listView);
        pdfFiles = new ArrayList<>();

        AssetManager assetManager = getAssets();
        try {
            String[] files = assetManager.list("");

            if (files != null) {
                for (String file : files) {
                    if (file.endsWith(".pdf")) {
                        pdfFiles.add(file.substring(0, file.length() - 4)); // Remove ".pdf" from the file name
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pdfFiles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(resp_list.this, PdfReaderActivity.class);
                intent.putExtra("fileName", pdfFiles.get(position));
                startActivity(intent);
            }
        });


    }
}