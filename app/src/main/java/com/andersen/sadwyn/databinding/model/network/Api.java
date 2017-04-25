package com.andersen.sadwyn.databinding.model.network;

import com.andersen.sadwyn.databinding.model.pojo.MemsData;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Sadwyn on 24.04.2017.
 */

public interface Api {
    @GET("get_memes")
    Observable<MemsData> getMemes();
}
