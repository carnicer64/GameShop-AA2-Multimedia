package com.svalero.gameshop_aa1_multimedia.contract.shop;

public interface ShopDeleteContract {
    interface Model{
        interface OnDeleteShopListener{
            void onDeleteShopSuccess(String success);
            void onDeleteShopError(String error);
        }

        void deleteShop(OnDeleteShopListener deleteShopListener, long id);
    }

    interface View {
        void showDeleteShop(String success);
        void showError(String error);
    }

    interface Presenter {
        void deleteShop(long id);
    }
}
