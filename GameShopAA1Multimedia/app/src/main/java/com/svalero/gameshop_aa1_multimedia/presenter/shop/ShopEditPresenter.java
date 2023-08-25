package com.svalero.gameshop_aa1_multimedia.presenter.shop;

import com.svalero.gameshop_aa1_multimedia.adapter.ShopAdapter;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopEditContract;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopListContract;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopEditModel;
import com.svalero.gameshop_aa1_multimedia.view.RegisterShopActivity;

public class ShopEditPresenter implements ShopEditContract.Presenter, ShopEditContract.Model.OnEditShopsListener {

    private ShopEditModel model;
    private ShopEditContract.View view;

    public ShopEditPresenter(RegisterShopActivity view) {
        this.view = view;
        model = new ShopEditModel();
    }
    @Override
    public void onEditShopsSuccess(Shop shop) {
        view.editShop(shop);
    }

    @Override
    public void onEditShopsError(String error) {
        view.showError(error);
    }

    @Override
    public void editShop(ShopInDTO shop) {
        model.loadEditShop(this, shop);
    }
}
