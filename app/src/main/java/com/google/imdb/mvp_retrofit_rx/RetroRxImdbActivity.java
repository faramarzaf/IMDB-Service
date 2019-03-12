package com.google.imdb.mvp_retrofit_rx;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.imdb.R;
import com.google.imdb.raw.IMDBmodel;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class RetroRxImdbActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {
    Presenter presenter = new Presenter();
    EditText word;
    ImageView poster;
    TextView title, released, director, rate;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_rx_imdb);
        bind();
        presenter.attachView(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait to load");

        RxTextView.textChanges(word).debounce(900, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChange);
    }

    private void onChange(CharSequence charSequence) {
        rest(charSequence.toString());
    }

    void rest(String word) {
        RXImdbInterface face = RetroRxIMDBGen.createService(RXImdbInterface.class);
        face.searchMovie(word, "70ad462a")
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError, this::onComplete);


    }


    private void onComplete() {

    }

    private void onError(Throwable throwable) {
        faild();
    }

    private void onSuccess(IMDBmodel imdb) {
        movieFound(imdb);
    }

    void bind() {
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        rate = findViewById(R.id.rate);
        word = findViewById(R.id.word);
        released = findViewById(R.id.released);
        director = findViewById(R.id.director);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void movieFound(IMDBmodel result) {
        if (word.length() == 0) {
            title.setText("");
            director.setText("");
            released.setText("");
            rate.setText("");
            poster.setVisibility(View.GONE);
        }
        else if (word.length() != 0){
            title.setText("Title : " + result.getTitle());
            director.setText("Director : " + result.getDirector());
            released.setText("Released : " + result.getReleased());
            rate.setText("Rating : " + result.getImdbRating());
            Glide.with(this).load(result.getPoster()).into(poster);
            poster.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void faild() {
        Toast.makeText(this, "Not found!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showLoading(boolean show) {
        if (show)
            dialog.show();
        else
            dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        presenter.search(word.getText().toString());
    }


}
