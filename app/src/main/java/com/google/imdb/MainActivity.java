package com.google.imdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.imdb.raw.IMDBmodel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView poster;
    TextView title, released, director, rate;
    EditText word;
    ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onClick(View v) {
        search(word.getText().toString());
    }

    private void search(String word) {

        AsyncHttpClient client = new AsyncHttpClient();
        //http://www.omdbapi.com/?i=tt3896198&apikey=bfe17a0c api key for your self
        String url = "http://www.omdbapi.com/?t=" + word + "&apikey=70ad462a";

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error in connection", Toast.LENGTH_LONG).show();
                Log.e("statusCode", "" + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseAndShow(responseString);
            }
        });

    }

    private void parseAndShow(String responseString) {

        Gson gson = new Gson();
        IMDBmodel imdBmodel = gson.fromJson(responseString, IMDBmodel.class);
        title.setText("Title : " + imdBmodel.getTitle());
        director.setText("Director : " + imdBmodel.getDirector());
        released.setText("Released : " + imdBmodel.getReleased());
        rate.setText("Rating : " + imdBmodel.getImdbRating());
        Glide.with(this).load(imdBmodel.getPoster()).into(poster);
    }
}
