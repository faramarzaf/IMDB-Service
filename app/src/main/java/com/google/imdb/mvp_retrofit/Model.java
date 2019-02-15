package com.google.imdb.mvp_retrofit;

import android.support.annotation.NonNull;

import com.google.imdb.raw.IMDBmodel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    Contract.Presenter presenter;
    ServiceInterface webInterface = RetroGenerator.createService(ServiceInterface.class);

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void search(String word) {
        webInterface.searchMovie(word,"bfe17a0c").enqueue(new Callback<IMDBmodel>() {

            @Override
            public void onResponse(@NonNull Call<IMDBmodel> call, @NonNull Response<IMDBmodel> response) {
                presenter.movieFound(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<IMDBmodel> call, @NonNull Throwable t) {
                presenter.faild();
            }
        });
    }
}