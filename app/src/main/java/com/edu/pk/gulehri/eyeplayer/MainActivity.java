package com.edu.pk.gulehri.eyeplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.edu.pk.gulehri.eyeplayer.databinding.ActivityMainBinding;

import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (!(ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED) &&
                !(ActivityCompat.checkSelfPermission(this, CAMERA) == PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE
                    , CAMERA}, 1);

            ActivityCompat.recreate(this);
        } else {
            String[] allVideoPath = getAllVideoPath(this);
            binding.videoRecycle.setLayoutManager(new LinearLayoutManager(this));
            binding.videoRecycle.setAdapter(new VideosAdapter(allVideoPath));
        }


    }

    private String[] getAllVideoPath(Context context) {
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.VideoColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        ArrayList<String> pathArrList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                pathArrList.add(cursor.getString(0));
            }
            cursor.close();
        }

        return pathArrList.toArray(new String[0]);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}