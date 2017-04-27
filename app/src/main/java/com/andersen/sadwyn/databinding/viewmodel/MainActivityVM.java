package com.andersen.sadwyn.databinding.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.andersen.sadwyn.databinding.App;
import com.andersen.sadwyn.databinding.model.Meme;
import com.andersen.sadwyn.databinding.model.MemsData;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sadwyn on 26.04.2017.
 */

public class MainActivityVM extends Observable {
    public static final String LIST = "LIST";
    public ObservableInt progress_bar;

    private List<Meme> memes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainActivityVM() {
        this.memes = new ArrayList<>();
        if (memes.isEmpty()) {
            progress_bar = new ObservableInt(View.VISIBLE);
            fetchMemes();
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            memes.addAll((Collection<? extends Meme>) Parcels.unwrap(bundle.getParcelable(LIST)));
            setChanged();
            notifyObservers();
        }
    }

    public void onDestroy() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    private void fetchMemes() {
        compositeDisposable.add(App.getApi().getMemes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(memsData -> {
            changeMemesDataset(memsData.getData().getMemes());
            progress_bar.set(View.GONE);
        }));
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
