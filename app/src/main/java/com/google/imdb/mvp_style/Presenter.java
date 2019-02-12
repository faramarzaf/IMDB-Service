package com.google.imdb.mvp_style;

import com.google.imdb.raw.IMDBmodel;

public class Presenter implements Contract.Presenter {
    Contract.View view;
    Contract.Model model = new Model();

    public Presenter(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);
    }

    @Override
    public void search(String word) {
        model.search(word);
    }

    @Override
    public void onConnectionfaild() {
        view.onConnectionfaild();

    }

    @Override
    public void onDataReceived(IMDBmodel result) {
        view.onDataReceived(result);
    }


}
