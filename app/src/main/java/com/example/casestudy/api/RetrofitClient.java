package com.example.casestudy.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final RetrofitClient ourInstance = new RetrofitClient();
    private Retrofit retrofit;
    private final static String BASE_URL = "http://private-d190ab-campaigns19.apiary-mock.com/";

    public static RetrofitClient getInstance() {
        return ourInstance;
    }

    private RetrofitClient() {

        //For retrofit logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client;
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        //Gson date formatter
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateFormatter())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public CampaignsService campaignsService() {
        return retrofit.create(CampaignsService.class);
    }

}
