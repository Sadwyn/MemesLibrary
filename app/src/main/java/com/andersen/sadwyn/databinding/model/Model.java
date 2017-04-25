package com.andersen.sadwyn.databinding.model;

import com.andersen.sadwyn.databinding.model.pojo.MemsData;

/**
 * Created by Sadwyn on 25.04.2017.
 */

public interface Model {
    io.reactivex.Observable<MemsData> getImages();
}
