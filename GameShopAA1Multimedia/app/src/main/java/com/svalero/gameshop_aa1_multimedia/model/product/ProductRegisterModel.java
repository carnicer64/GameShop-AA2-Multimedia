package com.svalero.gameshop_aa1_multimedia.model.product;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductRegisterContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRegisterModel implements ProductRegisterContract.Model {
    @Override
    public void RegisterProduct(OnRegisterProductListener registerProductListener, Product product) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Product> call = api.addProduct(product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                registerProductListener.onRegisterProductSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                registerProductListener.onRegisterProductError("Error registering product");
            }
        });
    }
}
