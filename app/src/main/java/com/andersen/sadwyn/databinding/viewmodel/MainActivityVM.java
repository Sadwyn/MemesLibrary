package com.andersen.sadwyn.databinding.viewmodel;

import android.os.Bundle;

import com.andersen.sadwyn.databinding.App;
import com.andersen.sadwyn.databinding.model.Meme;
import com.andersen.sadwyn.databinding.model.MemsData;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sadwyn on 26.04.2017.
 */

public class MainActivityVM extends Observable {

    private List<Meme> memes;

    public MainActivityVM() {
        this.memes = new ArrayList<>();
        if(memes.isEmpty())
        fetchMemes();
    }

    public void onRestoreInstanceState(Bundle bundle){
        if (bundle != null) {
            memes.addAll((Collection<? extends Meme>) Parcels.unwrap(bundle.getParcelable("LIST")));
            setChanged();
            notifyObservers();
        }
    }

    private void fetchMemes() {
        App.getApi().getMemes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MemsData>() {
                    @Override
                    public void accept(MemsData memsData) throws Exception {
                        changeMemesDataset(memsData.getData().getMemes());
                    }
                }).subscribe();
    }

    private void changeMemesDataset(List<Meme> memes) {
        this.memes = memes;
        setChanged();
        notifyObservers();
    }

    public List<Meme> getMemes() {
        return memes;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("LIST", Parcels.wrap(memes));
    }
}
