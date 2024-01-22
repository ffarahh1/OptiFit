package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.webkit.WebSettings;
import android.webkit.WebView;




public class VideoPlayerActivity extends AppCompatActivity {

    private WebView webView;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        webView = findViewById(R.id.webView);
        String videoUrl = getIntent().getStringExtra("videoUrl");
        String videoId = videoUrl.substring(videoUrl.lastIndexOf("=") + 1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //System.out.println(videoId);
        webView.loadUrl(videoId);
        webView.loadUrl("https://player.vimeo.com/video/"+videoId);


//        videoView = findViewById(R.id.videoView);
//
//        String videoUrl = getIntent().getStringExtra("videoUrl");
//        System.out.println(videoUrl);
//
//        videoView.setVideoURI(Uri.parse(videoUrl));
//        videoView.setMediaController(new MediaController(this));
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.start();
//            }
//        });
    }
}