package com.svalero.gameshop_aa1_multimedia.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameShopApi {
    public static GameShopApiInterface buildInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GameShopApiInterface.class);
    }
}
