package com.google.imdb.mvp_retrofit_rx;

import android.support.annotation.NonNull;

import com.google.imdb.mvp_retrofit.RetroGenerator;
import com.google.imdb.mvp_retrofit.ServiceInterface;
import com.google.imdb.raw.IMDBmodel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    ServiceInterface webInterface = RetroGenerator.createService(ServiceInterface.class);

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void search(String word) {
        presenter.isOnLoading(true);
        webInterface.searchMovie(word,"70ad462a").enqueue(new Callback<IMDBmodel>() {

            @Override
            public void onResponse(@NonNull Call<IMDBmodel> call, @NonNull Response<IMDBmodel> response) {
                presenter.movieFound(response.body());
                presenter.isOnLoading(false);
            }

            @Override
            public void onFailure(@NonNull Call<IMDBmodel> call, @NonNull Throwable t) {
                presenter.faild();
                presenter.isOnLoading(false);
            }
        });
    }
}
