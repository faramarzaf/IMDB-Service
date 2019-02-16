package com.google.imdb.mvp_retrofit;

import com.google.imdb.raw.IMDBmodel;

public interface Contract {

    interface View{
        void faild();
        void movieFound(IMDBmodel result);
        void showLoading(boolean show);
    }
    interface Presenter{
        void attachView(View view);
        void search(String word);
        void faild();
        void movieFound(IMDBmodel result);
        void isOnLoading(boolean is);
    }

    interface Model{
        void attachPresenter(Presenter presenter);
        void search(String word);
    }
}
