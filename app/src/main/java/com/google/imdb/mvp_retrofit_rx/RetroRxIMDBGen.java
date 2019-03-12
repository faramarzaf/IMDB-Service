package com.google.imdb.mvp_retrofit_rx;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroRxIMDBGen {

    public static <S> S createService(Class<S> serviceClass) {


        String BASE_URL = "http://www.omdbapi.com/";

        HttpLoggingInterceptor loggerInterceptorBody = new HttpLoggingInterceptor();
        loggerInterceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);

        HttpLoggingInterceptor loggerInterceptorHeader = new HttpLoggingInterceptor();
        loggerInterceptorHeader.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient client = (new OkHttpClient.Builder()).addInterceptor(loggerInterceptorBody).addInterceptor(loggerInterceptorHeader).readTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS).build();
        Retrofit retrofit = (new Retrofit.Builder()).baseUrl(BASE_URL).client(client).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()
                ).build();

        return retrofit.create(serviceClass);
    }
}