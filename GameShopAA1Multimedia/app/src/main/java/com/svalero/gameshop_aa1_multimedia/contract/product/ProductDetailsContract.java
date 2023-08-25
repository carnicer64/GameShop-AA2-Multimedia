package com.svalero.gameshop_aa1_multimedia.contract.product;

import com.svalero.gameshop_aa1_multimedia.domain.Product;

import java.util.List;

public interface ProductDetailsContract {
    interface Model {
        interface OnLoadProductListener {
            void onLoadProductSuccess(List<Product> products);

            void onLoadProductError(String error);
        }

        void loadDetailsProduct(OnLoadProductListener loadProductListener, long id);
    }

    interface View {
        void showProduct(List<Product> products);
        void showError(String error);
    }

    interface Presenter {
        void loadDetailsProduct(long id);
    }
}