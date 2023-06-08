package com.example.lab4;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private RadioGroup videoRadioGroup;
    private VideoView videoView;
    private Button playButton;
    private Button stopButton;
    private Button pauseButton;
    private Button backButton;

    private String[] videoFiles = {
            "leave_me_alone",
            "catch_me"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoRadioGroup = findViewById(R.id.video_radio_group);
        videoView = findViewById(R.id.video_view);
        playButton = findViewById(R.id.play_button);
        stopButton = findViewById(R.id.stop_button);
        pauseButton = findViewById(R.id.pause_button);
        backButton = findViewById(R.id.back_button);

        initializeRadioButtons();

        videoRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                stopPlayback();
                int selectedVideoIndex = videoRadioGroup.indexOfChild(findViewById(checkedId));
                String videoFileName = videoFiles[selectedVideoIndex];
                int videoResourceId = getResources().getIdentifier(videoFileName, "raw", getPackageName());
                String videoPath = "android.resource://" + getPackageName() + "/" + videoResourceId;
                videoView.setVideoURI(Uri.parse(videoPath));
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
            }
        });

        stopButton.setOnClickListener(v -> stopPlayback());

        pauseButton.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void initializeRadioButtons() {
        for (String videoFile : videoFiles) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(videoFile);
            videoRadioGroup.addView(radioButton);
        }
    }

    private void stopPlayback() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}
