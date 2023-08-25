package com.svalero.gameshop_aa1_multimedia.contract.shop;

import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import java.util.List;

public interface ShopDetailsContract {
    interface Model {
        interface OnLoadShopListener {
            void onLoadShopSuccess(List<Shop> shops);
            void onLoadShopError(String error);
        }

        void loadDetailsShop(OnLoadShopListener loadShopListener, long id);
    }

    interface View {
        void showShop(List<Shop> shops);
        void showError(String error);
    }

    interface Presenter {
        void loadDetailsShop(long id);
    }
}
