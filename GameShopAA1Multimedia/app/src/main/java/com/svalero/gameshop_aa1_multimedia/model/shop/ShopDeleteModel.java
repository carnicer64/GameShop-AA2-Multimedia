package com.svalero.gameshop_aa1_multimedia.model.shop;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDeleteContract;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDetailsContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDeleteModel implements ShopDeleteContract.Model {

    @Override
    public void deleteShop(OnDeleteShopListener deleteShopListener, long id) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Void> call = api.deleteShop(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                deleteShopListener.onDeleteShopSuccess("Correct delete");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                deleteShopListener.onDeleteShopError("Error deleting shop");
            }
        });
    }
}
