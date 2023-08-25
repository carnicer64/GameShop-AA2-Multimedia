package com.svalero.gameshop_aa1_multimedia.api;


import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameShopApiInterface {

    //PRODUCT
    @GET("products")
    Call<List<Product>> getProducts();

    //TODO cambiar para que devuelva una List como en shops
    @GET("products")
    Call<List<Product>> getProduct(@Query("id") long id);

    @POST("products")
    Call<Product> addProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") long id);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") long id, @Body Product product);

    //SHOP

    @GET("shops")
    Call<List<Shop>> getShops();

    @GET("shops")
    Call<List<Shop>> getShop(@Query("id") long id);

    @POST("shops")
    Call<Shop> addShop(@Body ShopInDTO shop);

    @DELETE("shops/{id}")
    Call<Void> deleteShop(@Path("id") long id);

    @PUT("shops/{id}")
    Call<Shop> updateShop(@Path("id") long id, @Body ShopInDTO shop);

}
