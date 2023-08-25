package com.svalero.gameshop_aa1_multimedia.contract.shop;

import com.svalero.gameshop_aa1_multimedia.domain.Shop;
import com.svalero.gameshop_aa1_multimedia.domain.dto.ShopInDTO;

public interface ShopEditContract {
    interface Model{
        interface OnEditShopsListener {
            void onEditShopsSuccess(Shop shop);
            void onEditShopsError(String error);
        }

        void loadEditShop(OnEditShopsListener editShopsListener, ShopInDTO shop);
    }

    interface View {
        void editShop(Shop shop);
        void showError(String error);
    }

    interface Presenter {
        void editShop(ShopInDTO shop);
    }
}
