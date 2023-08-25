package com.svalero.gameshop_aa1_multimedia.contract.shop;

import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import java.util.List;

public interface ShopListContract {
    interface Model{
        interface OnLoadShopsListener {
            void onLoadShopsSuccess(List<Shop> shopList);
            void onLoadShopsError(String error);
        }

        void loadAllShops(OnLoadShopsListener loadShopsListener);
    }

    interface View {
        void showShops(List<Shop> shopList);
        void showError(String error);
    }

    interface Presenter {
        void loadAllShops();
    }
}
