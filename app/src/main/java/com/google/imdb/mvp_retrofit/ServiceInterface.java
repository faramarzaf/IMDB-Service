package com.google.imdb.mvp_retrofit;

import com.google.imdb.raw.IMDBmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    //http://www.omdbapi.com/?t=thor&apikey=70ad462a

    @GET("\n")
    Call<IMDBmodel> searchMovie(
            @Query("t") String word,
            @Query("apikey") String key
    );

}
