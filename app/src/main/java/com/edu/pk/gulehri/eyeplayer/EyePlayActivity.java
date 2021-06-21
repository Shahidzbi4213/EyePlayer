package com.edu.pk.gulehri.eyeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.edu.pk.gulehri.eyeplayer.databinding.ActivityEyePlayBinding;

public class EyePlayActivity extends AppCompatActivity {

    private ActivityEyePlayBinding binding;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEyePlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getValues();
        setEyePlayer();
    }

    private void getValues() {
        Intent intent = getIntent();
        path = intent.getStringExtra("filename");
    }

    private void setEyePlayer() {
        binding.lookme.init(this, "ACCURATE", "FRONT");
        binding.lookme.setVideoPath(path);
        //Creating MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.lookme);
        binding.lookme.setMediaController(mediaController);
        binding.lookme.start();
        binding.lookme.setLookMe();
    }
}