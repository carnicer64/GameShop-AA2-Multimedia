package com.svalero.gameshop_aa1_multimedia.model.product;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductListContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListModel implements ProductListContract.Model {
    @Override
    public void loadAllProducts(OnLoadProductsListener loadProductListener) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<List<Product>> call = api.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                loadProductListener.onLoadProductsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                loadProductListener.onLoadProductsError("Error loading products");
            }
        });
    }
}
