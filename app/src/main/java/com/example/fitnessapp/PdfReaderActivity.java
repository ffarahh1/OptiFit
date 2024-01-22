package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;

//import androidx.appcompat.app.AppCompatActivity;


import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;
//import android.os.Bundle;

public class PdfReaderActivity extends AppCompatActivity {

    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader);

        pdfView = findViewById(R.id.mypdfview);

        String fileName = getIntent().getStringExtra("fileName");

        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open(fileName + ".pdf");
            pdfView.fromStream(in).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}