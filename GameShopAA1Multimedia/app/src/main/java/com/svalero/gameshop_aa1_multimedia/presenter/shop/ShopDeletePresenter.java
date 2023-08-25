package com.svalero.gameshop_aa1_multimedia.presenter.shop;

import com.svalero.gameshop_aa1_multimedia.adapter.ShopAdapter;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDeleteContract;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDetailsContract;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopDeleteModel;
import com.svalero.gameshop_aa1_multimedia.model.shop.ShopDetailsModel;

public class ShopDeletePresenter implements ShopDeleteContract.Presenter, ShopDeleteContract.Model.OnDeleteShopListener {

    private ShopDeleteModel model;
    private ShopDeleteContract.View view;

    public ShopDeletePresenter(ShopAdapter view) {
        this.view = view;
        model = new ShopDeleteModel();
    }

    @Override
    public void onDeleteShopSuccess(String success) {
        view.showDeleteShop(success);
    }

    @Override
    public void onDeleteShopError(String error) {
        view.showError(error);
    }

    @Override
    public void deleteShop(long id) {
        model.deleteShop(this, id);
    }
}
