package com.svalero.gameshop_aa1_multimedia.presenter.product;

import com.svalero.gameshop_aa1_multimedia.contract.product.ProductEditContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.model.product.ProductEditModel;
import com.svalero.gameshop_aa1_multimedia.view.RegisterProductActivity;

public class ProductEditPresenter implements ProductEditContract.Presenter, ProductEditContract.Model.OnEditProductListener {

    private ProductEditModel model;
    private ProductEditContract.View view;

    public ProductEditPresenter(RegisterProductActivity view){
        this.view = view;
        model = new ProductEditModel();
    }
    @Override
    public void onEditProductSuccess(Product product) {
        view.editProduct(product);
    }

    @Override
    public void onEditProductError(String error) {
        view.showError(error);
    }

    @Override
    public void editProduct(Product product) {
        model.loadEditProduct(this, product);
    }
}
