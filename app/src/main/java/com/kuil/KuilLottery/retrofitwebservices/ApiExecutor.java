package com.kuil.KuilLottery.retrofitwebservices;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiExecutor {

    private static String baseUrl;
    private static Retrofit retrofit;

    public static ApiService getApiService(Context mContext) {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        baseUrl = RequestUrl.BASE_URL;
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit.create(ApiService.class);
    }
}
