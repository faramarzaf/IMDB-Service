package com.google.imdb.mvp_style;

import android.app.ProgressDialog;
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

public class mvpActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {

    ImageView poster;
    TextView title, released, director, rate;
    EditText word;
    ImageView search;
    ProgressDialog dialog;

    Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait to load");
        bind();
        search.setOnClickListener(this);
    }

    void bind(){
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        rate = findViewById(R.id.rate);
        word = findViewById(R.id.word);
        released = findViewById(R.id.released);
        director = findViewById(R.id.director);
        search = findViewById(R.id.search);
    }

    @Override
    public void onDataReceived(IMDBmodel result) {

        title.setText("Title : " + result.getTitle());
        director.setText("Director : " + result.getDirector());
        released.setText("Released : " + result.getReleased());
        rate.setText("Rating : " + result.getImdbRating());
        Glide.with(this).load(result.getPoster()).into(poster);
    }

    @Override
    public void onConnectionfaild() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

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
        presenter.orderToGetData();
    }
}
