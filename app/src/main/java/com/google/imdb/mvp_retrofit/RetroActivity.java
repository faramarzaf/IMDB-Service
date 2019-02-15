package com.google.imdb.mvp_retrofit;

import android.annotation.SuppressLint;
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

public class RetroActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {

    Presenter presenter = new Presenter();

    EditText word;
    ImageView search,poster;
    TextView title, released, director, rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro);
        bind();
        presenter.attachView(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        presenter.search(word.getText().toString());
    }

    void bind() {
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        rate = findViewById(R.id.rate);
        word = findViewById(R.id.word);
        released = findViewById(R.id.released);
        director = findViewById(R.id.director);
        search = findViewById(R.id.search);
    }

    @Override
    public void faild() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void movieFound(IMDBmodel result) {
        title.setText("Title : " + result.getTitle());
        director.setText("Director : " + result.getDirector());
        released.setText("Released : " + result.getReleased());
        rate.setText("Rating : " + result.getImdbRating());
        Glide.with(this).load(result.getPoster()).into(poster);

    }


}
