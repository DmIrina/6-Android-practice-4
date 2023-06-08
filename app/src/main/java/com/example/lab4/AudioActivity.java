package com.example.lab4;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {
    private RadioGroup audioRadioGroup;
    private Button playButton;
    private Button stopButton;
    private Button pauseButton;
    private Button backButton;

    private MediaPlayer mediaPlayer;

    private String[] audioFiles = {
            "catch_if_you_can",
            "circus",
            "dinile_is_river",
            "directed_by_robert_weide",
            "loud_circus",
            "pocoloco"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        audioRadioGroup = findViewById(R.id.audio_radio_group);
        playButton = findViewById(R.id.play_button);
        stopButton = findViewById(R.id.stop_button);
        pauseButton = findViewById(R.id.pause_button);
        backButton = findViewById(R.id.back_button);

        initializeRadioButtons();

        audioRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            stopPlayback();
            int selectedAudioIndex = audioRadioGroup.indexOfChild(findViewById(checkedId));
            String audioFileName = audioFiles[selectedAudioIndex];
            int audioResourceId = getResources().getIdentifier(audioFileName, "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(AudioActivity.this, audioResourceId);
        });

        playButton.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        stopButton.setOnClickListener(v -> stopPlayback());

        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void initializeRadioButtons() {
        for (String audioFile : audioFiles) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(audioFile);
            audioRadioGroup.addView(radioButton);
        }
    }

    private void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
