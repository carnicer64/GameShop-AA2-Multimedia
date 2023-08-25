package com.svalero.gameshop_aa1_multimedia.contract.product;


import com.svalero.gameshop_aa1_multimedia.domain.Product;

public interface ProductEditContract {
    interface Model{
        interface OnEditProductListener{
            void onEditProductSuccess(Product product);
            void onEditProductError(String error);
        }

        void loadEditProduct(OnEditProductListener editProductListener, Product product);
    }

    interface View {
        void editProduct(Product product);
        void showError(String error);
    }

    interface Presenter{
        void editProduct(Product product);
    }
}
