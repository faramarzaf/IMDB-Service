package com.google.imdb.mvp_retrofit;

import com.google.imdb.raw.IMDBmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

// ?i=tt3896198&apikey=bfe17a0c

    @GET("\n")
    Call<IMDBmodel> searchMovie(
            @Query("i") String word,
            @Query("apikey") String key
    );

}
