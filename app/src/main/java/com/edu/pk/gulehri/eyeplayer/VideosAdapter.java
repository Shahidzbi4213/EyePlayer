package com.edu.pk.gulehri.eyeplayer;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.pk.gulehri.eyeplayer.databinding.SingleVideoBinding;

import java.io.File;

/**
 * Created by Shahid Iqbal on 6/21/2021
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosHolder> {

    String[] paths;

    public VideosAdapter(String[] videosList) {
        this.paths = videosList;
    }

    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideosHolder(SingleVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
        try {
            File file = new File(paths[position]);
            String absolutePath = file.getName();
            Bitmap bMap = ThumbnailUtils.createVideoThumbnail(paths[position], MediaStore.Video.Thumbnails.MICRO_KIND);
            holder.binding.tvPath.setText(absolutePath);
            holder.binding.imgThumb.setImageBitmap(bMap);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return paths.length;
    }

    public class VideosHolder extends RecyclerView.ViewHolder {
        private final SingleVideoBinding binding;

        public VideosHolder(@NonNull SingleVideoBinding videoBinding) {
            super(videoBinding.getRoot());
            binding = videoBinding;
        }
    }
}
