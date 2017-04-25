package com.andersen.sadwyn.databinding.network;

import com.andersen.sadwyn.databinding.data.MemsData;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Sadwyn on 24.04.2017.
 */

public interface Api {
    @GET("get_memes")
    Observable<MemsData> getMemes();
}
