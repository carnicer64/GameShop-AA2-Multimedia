package com.svalero.gameshop_aa1_multimedia.model.shop;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopEditContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopEditModel implements ShopEditContract.Model {

    @Override
    public void loadEditShop(OnEditShopsListener editShopsListener, ShopInDTO shop) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Shop> call = api.updateShop(shop.getId(), shop);
        call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                editShopsListener.onEditShopsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                t.printStackTrace();
                editShopsListener.onEditShopsError("Error editing shop");
            }
        });
    }
}
