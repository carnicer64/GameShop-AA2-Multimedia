package com.svalero.gameshop_aa1_multimedia.model.product;

import com.svalero.gameshop_aa1_multimedia.api.GameShopApi;
import com.svalero.gameshop_aa1_multimedia.api.GameShopApiInterface;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductEditContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductEditModel implements ProductEditContract.Model {
    @Override
    public void loadEditProduct(OnEditProductListener editProductListener, Product product) {
        GameShopApiInterface api = GameShopApi.buildInstance();
        Call<Product> call = api.updateProduct(product.getId(), product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                editProductListener.onEditProductSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                t.printStackTrace();
                editProductListener.onEditProductError("Error editing product");
            }
        });
    }
}
