package com.svalero.gameshop_aa1_multimedia.model.shop;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopRegisterContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRegisterModel implements ShopRegisterContract.Model {

    @Override
    public void registerShop(OnRegisterShopListener registerShopListener, ShopInDTO shop) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Shop> call = api.addShop(shop);
        call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                registerShopListener.onRegisterShopSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                t.printStackTrace();
                registerShopListener.onRegisterShopError("Error registering shop");
            }
        });
    }
}