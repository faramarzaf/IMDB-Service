package com.google.imdb.mvp_style;

import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.imdb.MainActivity;
import com.google.imdb.raw.IMDBmodel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Model implements Contract.Model {

    Contract.Presenter presenter;

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void search(String word) {
        AsyncHttpClient client = new AsyncHttpClient();
        //http://www.omdbapi.com/?i=tt3896198&apikey=bfe17a0c api key for your self
        String url = "http://www.omdbapi.com/?t=" + word + "&apikey=70ad462a";

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                presenter.onConnectionfaild();
                Log.e("statusCode", "" + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseData(responseString);
            }
        });
    }

    private void parseData(String responseString) {
        Gson gson = new Gson();
        IMDBmodel result = gson.fromJson(responseString, IMDBmodel.class);
        presenter.onDataReceived(result);
    }


}
