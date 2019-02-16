package com.google.imdb.mvp_retrofit;

import com.google.imdb.raw.IMDBmodel;

public class Presenter implements Contract.Presenter {

    Contract.View view;
    Model model = new Model();

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);

    }

    @Override
    public void search(String word) {
        model.search(word);
    }

    @Override
    public void faild() {
        view.faild();
    }

    @Override
    public void movieFound(IMDBmodel result) {
        view.movieFound(result);
    }

    @Override
    public void isOnLoading(boolean is) {
        view.showLoading(is);
    }
}
