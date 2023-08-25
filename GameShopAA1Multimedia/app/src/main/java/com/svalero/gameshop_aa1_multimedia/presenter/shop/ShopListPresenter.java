package com.svalero.gameshop_aa1_multimedia.presenter.shop;

import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopListContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopListModel;
import com.svalero.gameshop_aa1_multimedia.view.ManageShopsActivity;

import java.util.List;

public class ShopListPresenter implements ShopListContract.Presenter, ShopListContract.Model.OnLoadShopsListener {

    private ShopListModel model;
    private ShopListContract.View view;

    public ShopListPresenter(ManageShopsActivity view) {
        this.view = view;
        model = new ShopListModel();
    }

    @Override
    public void onLoadShopsSuccess(List<Shop> shopList) {
        view.showShops(shopList);
    }

    @Override
    public void onLoadShopsError(String error) {
        view.showError(error);
    }

    @Override
    public void loadAllShops() {
        model.loadAllShops(this);
    }
}
