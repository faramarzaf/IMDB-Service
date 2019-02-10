package com.google.imdb.mvp_style;

import com.google.imdb.raw.IMDBmodel;

public interface Contract {

    interface View{
        void onDataReceived(IMDBmodel result);
        void onConnectionfaild();
        void showLoading(boolean show);
    }
    interface Presenter{
        void attachView(View view);
        void orderToGetData();
        void onConnectionfaild();
        void onDataReceived(IMDBmodel result);
        void isOnLoading(boolean is);
    }
    interface Model{
        void attachPresenter(Presenter presenter);
        void orderToGetData(String word);

    }
}
