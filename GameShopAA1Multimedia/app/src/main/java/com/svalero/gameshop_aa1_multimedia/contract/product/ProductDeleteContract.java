package com.svalero.gameshop_aa1_multimedia.contract.product;

public interface ProductDeleteContract {
    interface Model{
        interface OnDeleteProductListener{
            void onDeleteProductSuccess(String success);
            void onDeleteProductError(String error);
        }

        void deleteShop(OnDeleteProductListener deleteProductListener, long id);
    }

    interface View {
        void showDeleteProduct(String success);
        void showDeleteError(String error);
    }

    interface Presenter{
        void deleteProduct(long id);
    }
}
