package com.svalero.gameshop_aa1_multimedia.contract.shop;

import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;

public interface ShopRegisterContract {
    interface Model{
        interface OnRegisterShopListener {
            void onRegisterShopSuccess(Shop shop);
            void onRegisterShopError(String error);
        }

        void registerShop(OnRegisterShopListener registerShopListener, ShopInDTO shop);
    }

    interface View {
        void showRegisterShop(Shop shop);
        void showError(String Error);
    }

    interface Presenter {
        void registerShop(ShopInDTO shop);
    }
}
