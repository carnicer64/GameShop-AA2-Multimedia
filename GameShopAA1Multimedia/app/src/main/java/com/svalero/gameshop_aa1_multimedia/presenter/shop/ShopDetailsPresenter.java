package com.svalero.gameshop_aa1_multimedia.presenter.shop;

import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDetailsContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopDetailsModel;
import com.svalero.gameshop_aa1_multimedia.view.ShopDetails;

import java.util.List;

public class ShopDetailsPresenter implements ShopDetailsContract.Presenter, ShopDetailsContract.Model.OnLoadShopListener {

    private ShopDetailsModel model;
    private ShopDetailsContract.View view;

    public ShopDetailsPresenter(ShopDetails view) {
        this.view = view;
        model = new ShopDetailsModel();
    }
    @Override
    public void onLoadShopSuccess(List<Shop> shops) {
        view.showShop(shops);
    }

    @Override
    public void onLoadShopError(String error) {
        view.showError(error);
    }

    @Override
    public void loadDetailsShop(long id) {
        model.loadDetailsShop(this,id);
    }
}
