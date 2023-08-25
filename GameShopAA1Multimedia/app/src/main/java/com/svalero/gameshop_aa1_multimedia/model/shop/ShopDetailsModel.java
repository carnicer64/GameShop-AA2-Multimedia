package com.svalero.gameshop_aa1_multimedia.model.shop;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDetailsContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailsModel implements ShopDetailsContract.Model {

    @Override
    public void loadDetailsShop(OnLoadShopListener loadShopListener, long id) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<List<Shop>> call = api.getShop(id);
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                loadShopListener.onLoadShopSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                t.printStackTrace();
                loadShopListener.onLoadShopError("Error loading shop");

            }
        });
    }
}
