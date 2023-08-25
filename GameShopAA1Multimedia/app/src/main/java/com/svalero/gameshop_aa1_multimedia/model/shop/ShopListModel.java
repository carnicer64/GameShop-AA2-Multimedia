package com.svalero.gameshop_aa1_multimedia.model.shop;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopListContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListModel implements ShopListContract.Model {

    @Override
    public void loadAllShops(OnLoadShopsListener loadShopsListener) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<List<Shop>> call = api.getShops();
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                loadShopsListener.onLoadShopsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                t.printStackTrace();
                loadShopsListener.onLoadShopsError("Error loading products");
            }
        });
    }
}
