package com.google.imdb.mvp_retrofit_rx;

import com.google.imdb.raw.IMDBmodel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RXImdbInterface {

    @GET("/")
    Observable<IMDBmodel> searchMovie(
            @Query("t") String word
            ,
            @Query("apikey") String key
    ) ;


}
