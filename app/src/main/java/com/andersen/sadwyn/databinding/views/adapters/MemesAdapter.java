package com.andersen.sadwyn.databinding.views.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andersen.sadwyn.databinding.data.Meme;
import com.andersen.sadwyn.databinding.databinding.MemItemBinding;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MemViewHolder> {
    private List<Meme> memList;

    public MemesAdapter(List<Meme> memList) {
        this.memList = memList;
    }

    @Override
    public MemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MemItemBinding binding = MemItemBinding.inflate(inflater, parent, false);
        return new MemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MemViewHolder holder, int position) {
        Meme meme = memList.get(position);
        Log.i("BIND", String.valueOf(position) );
        holder.binding.setMeme(meme);
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
        Glide.with(imageView.getContext().getApplicationContext()).load(v).centerCrop().into(imageView);
    }
}
