package com.svalero.gameshop_aa1_multimedia.presenter.product;

import com.svalero.gameshop_aa1_multimedia.contract.product.ProductRegisterContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.model.product.ProductRegisterModel;
import com.svalero.gameshop_aa1_multimedia.view.RegisterProductActivity;


public class ProductRegisterPresenter implements ProductRegisterContract.Presenter, ProductRegisterContract.Model.OnRegisterProductListener {

    private ProductRegisterModel model;
    private ProductRegisterContract.View view;

    public ProductRegisterPresenter(RegisterProductActivity view){
        this.view = view;
        model = new ProductRegisterModel();
    }

    @Override
    public void onRegisterProductSuccess(Product product) {
        view.showRegisterProduct(product);
    }

    @Override
    public void onRegisterProductError(String error) {
        view.showError(error);
    }

    @Override
    public void registerProduct(Product product) {
        model.RegisterProduct(this, product);
    }
}
