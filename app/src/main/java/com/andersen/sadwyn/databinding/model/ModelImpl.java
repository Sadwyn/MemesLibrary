package com.andersen.sadwyn.databinding.model;

import com.andersen.sadwyn.databinding.App;
import com.andersen.sadwyn.databinding.model.pojo.MemsData;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sadwyn on 25.04.2017.
 */

public class ModelImpl implements Model {
    private static ModelImpl model;

    public static ModelImpl getInstance() {
        if(model == null)
            model = new ModelImpl();
        return model;
    }


    @Override
    public Observable<MemsData> getImages() {
       return App.getApi().getMemes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
