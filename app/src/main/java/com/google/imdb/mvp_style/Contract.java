package com.google.imdb.mvp_style;

import com.google.imdb.raw.IMDBmodel;

public interface Contract {

    interface View {
        void onDataReceived(IMDBmodel result);
        void onConnectionfaild();

    }

    interface Presenter {
        void search(String word);
        void onConnectionfaild();
        void onDataReceived(IMDBmodel result);

    }

    interface Model {
        void attachPresenter(Presenter presenter);
        void search(String word);

    }
}
