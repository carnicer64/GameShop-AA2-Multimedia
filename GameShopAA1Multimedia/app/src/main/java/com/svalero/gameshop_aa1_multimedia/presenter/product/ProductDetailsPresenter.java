package com.svalero.gameshop_aa1_multimedia.presenter.product;

import com.svalero.gameshop_aa1_multimedia.contract.product.ProductDetailsContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.model.product.ProductDetailsModel;
import com.svalero.gameshop_aa1_multimedia.view.ProductDetail;

import java.util.List;

public class ProductDetailsPresenter implements ProductDetailsContract.Presenter, ProductDetailsContract.Model.OnLoadProductListener {

    private ProductDetailsModel model;
    private ProductDetailsContract.View view;

    public ProductDetailsPresenter(ProductDetail view) {
        this.view = view;
        model = new ProductDetailsModel();
    }

    @Override
    public void onLoadProductSuccess(List<Product> products) {
        view.showProduct(products);
    }

    @Override
    public void onLoadProductError(String error) {
        view.showError(error);

    }

    @Override
    public void loadDetailsProduct(long id) {
        model.loadDetailsProduct(this,id);
    }
}
