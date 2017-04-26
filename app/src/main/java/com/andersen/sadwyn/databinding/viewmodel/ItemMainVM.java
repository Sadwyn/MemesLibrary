package com.andersen.sadwyn.databinding.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.andersen.sadwyn.databinding.model.Meme;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sadwyn on 26.04.2017.
 */

public class ItemMainVM extends BaseObservable {
    private Meme meme;

    public ItemMainVM(Meme meme) {
        this.meme = meme;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Glide.with(imageView.getContext().getApplicationContext()).load(v).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(imageView);
    }

    public String getMemeUrl() {
        return meme.getUrl();
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
        notifyChange();
    }

    public void saveImage(View view){
        ImageView imageView = (ImageView)view;
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Memes";
        File file = new File(path, meme.getName()+".jpg");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        Log.i("TAG", "SAVED");
    }
}
