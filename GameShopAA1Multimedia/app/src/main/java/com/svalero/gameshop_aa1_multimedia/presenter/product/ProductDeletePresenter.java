package com.svalero.gameshop_aa1_multimedia.presenter.product;

import com.svalero.gameshop_aa1_multimedia.adapter.ProductAdapter;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductDeleteContract;
import com.svalero.gameshop_aa1_multimedia.model.product.ProductDeleteModel;


public class ProductDeletePresenter implements ProductDeleteContract.Presenter, ProductDeleteContract.Model.OnDeleteProductListener {

    private ProductDeleteModel model;
    private ProductDeleteContract.View view;

     public ProductDeletePresenter(ProductAdapter view){
         this.view = view;
         model = new ProductDeleteModel();
     }

    @Override
    public void onDeleteProductSuccess(String success) {
        view.showDeleteProduct(success);
    }

    @Override
    public void onDeleteProductError(String error) {
        view.showDeleteError(error);
    }

    @Override
    public void deleteProduct(long id) {
        model.deleteShop(this, id);
    }
}
