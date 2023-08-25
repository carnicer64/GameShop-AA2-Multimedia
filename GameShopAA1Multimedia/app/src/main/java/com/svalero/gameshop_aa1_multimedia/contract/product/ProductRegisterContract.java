package com.svalero.gameshop_aa1_multimedia.contract.product;

import com.svalero.gameshop_aa1_multimedia.domain.Product;

public interface ProductRegisterContract {
    interface Model{
        interface OnRegisterProductListener{
            void onRegisterProductSuccess(Product product);
            void onRegisterProductError(String error);
        }

        void RegisterProduct(OnRegisterProductListener registerProductListener, Product product);
    }

    interface View {
        void showRegisterProduct(Product product);
        void showError(String error);
    }

    interface Presenter{
        void registerProduct(Product product);
    }
}
