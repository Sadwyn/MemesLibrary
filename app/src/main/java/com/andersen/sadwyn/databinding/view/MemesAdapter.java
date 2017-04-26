package com.andersen.sadwyn.databinding.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andersen.sadwyn.databinding.R;
import com.andersen.sadwyn.databinding.databinding.ItemMemBinding;
import com.andersen.sadwyn.databinding.model.Meme;
import com.andersen.sadwyn.databinding.viewmodel.ItemMainVM;

import java.util.Collections;
import java.util.List;



public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MemViewHolder> {
    private List<Meme> memList;

    public MemesAdapter() {
        this.memList = Collections.emptyList();
    }

    public void setMemList(List<Meme> memList) {
        this.memList = memList;
        notifyDataSetChanged();
    }

    @Override
    public MemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMemBinding memItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mem,
                        parent, false);
        return new MemViewHolder(memItemBinding);
    }

    @Override
    public void onBindViewHolder(MemViewHolder holder, int position) {
        holder.bindMem(memList.get(position));
        Log.i("BIND", String.valueOf(position) );
    }

    @Override
    public int getItemCount() {
        return memList.size();
    }



    public static class MemViewHolder extends RecyclerView.ViewHolder {
         ItemMemBinding binding;

        public MemViewHolder(ItemMemBinding memItemBinding) {
            super(memItemBinding.itemMem);
            binding = memItemBinding;
        }

        void bindMem(Meme meme) {
            if (binding.getItemMainVM() == null) {
                binding.setItemMainVM(
                        new ItemMainVM(meme));
            } else {
                binding.getItemMainVM().setMeme(meme);
            }
        }
    }
}
