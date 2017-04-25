package com.andersen.sadwyn.databinding.views.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andersen.sadwyn.databinding.data.Meme;
import com.andersen.sadwyn.databinding.databinding.MemItemBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sadwyn on 24.04.2017.
 */

public class MemesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Meme> memList;

    public MemesAdapter(List<Meme> memList) {
        this.memList = memList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MemItemBinding binding = MemItemBinding.inflate(inflater, parent, false);
        return new MemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Meme meme = memList.get(position);
        ((MemViewHolder)holder).binding.setMeme(meme);
        ((MemViewHolder)holder).binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return memList.size();
    }



    public class MemViewHolder extends RecyclerView.ViewHolder {
        final MemItemBinding binding;

        public MemViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }
    }



    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Glide.with(imageView.getContext().getApplicationContext()).load(v).fitCenter().into(imageView);
    }
}
