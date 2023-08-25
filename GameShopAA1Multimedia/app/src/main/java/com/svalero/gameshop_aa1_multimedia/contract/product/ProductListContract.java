package com.svalero.gameshop_aa1_multimedia.contract.product;

import com.svalero.gameshop_aa1_multimedia.domain.Product;

import java.util.List;

public interface ProductListContract {
    interface Model{
        interface OnLoadProductsListener {
            void onLoadProductsSuccess(List<Product> productList);
            void onLoadProductsError(String error);
        }

        void loadAllProducts(OnLoadProductsListener loadProductListener);
    }

    interface View {
        void showProducts(List<Product> productList);
        void showError(String error);
    }

    interface Presenter {
        void loadAllProducts();
    }
}
