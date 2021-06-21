package com.edu.pk.gulehri.eyeplayer;

import android.content.Context;
import android.content.Intent;
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

    private final   String[] paths;
    private Context mContext;

    public VideosAdapter(String[] videosList) {
        this.paths = videosList;
    }


    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new VideosHolder(SingleVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
        File file = new File(paths[position]);
        String absolutePath = file.getName();
        Bitmap bMap = ThumbnailUtils.createVideoThumbnail(paths[position], MediaStore.Video.Thumbnails.MICRO_KIND);
        holder.binding.tvPath.setText(absolutePath);
        holder.binding.imgThumb.setImageBitmap(bMap);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EyePlayActivity.class);
            intent.putExtra("filename", file.getAbsolutePath());
            mContext.startActivity(intent);
        });

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
