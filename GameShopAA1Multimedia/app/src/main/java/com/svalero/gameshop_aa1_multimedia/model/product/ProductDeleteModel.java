package com.svalero.gameshop_aa1_multimedia.model.product;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductDeleteContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDeleteModel implements ProductDeleteContract.Model {
    @Override
    public void deleteShop(OnDeleteProductListener deleteProductListener, long id) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Void> call = api.deleteProduct(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                deleteProductListener.onDeleteProductSuccess("Correct delete");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                deleteProductListener.onDeleteProductError("Error deleting product");
            }
        });
    }
}
