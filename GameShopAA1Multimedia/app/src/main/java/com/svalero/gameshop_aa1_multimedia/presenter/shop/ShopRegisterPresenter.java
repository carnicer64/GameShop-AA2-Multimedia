package com.svalero.gameshop_aa1_multimedia.presenter.shop;

import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopRegisterContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopRegisterModel;
import com.svalero.gameshop_aa1_multimedia.view.RegisterShopActivity;

public class ShopRegisterPresenter implements ShopRegisterContract.Presenter, ShopRegisterContract.Model.OnRegisterShopListener {

    private ShopRegisterModel model;
    private ShopRegisterContract.View view;

    public ShopRegisterPresenter(RegisterShopActivity view) {
        this.view = view;
        model = new ShopRegisterModel();

    }

    @Override
    public void onRegisterShopSuccess(Shop shop) {
        view.showRegisterShop(shop);
    }

    @Override
    public void onRegisterShopError(String error) {
        view.showError(error);
    }

    @Override
    public void registerShop(ShopInDTO shop) {
        model.registerShop(this, shop);
    }
}
