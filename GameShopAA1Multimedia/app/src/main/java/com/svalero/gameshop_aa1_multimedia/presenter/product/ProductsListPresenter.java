package com.svalero.gameshop_aa1_multimedia.presenter.product;

import com.svalero.gameshop_aa1_multimedia.contract.product.ProductListContract;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.model.product.ProductListModel;
import com.svalero.gameshop_aa1_multimedia.view.ManageProductsActivity;

import java.util.List;

public class ProductsListPresenter implements ProductListContract.Presenter, ProductListContract.Model.OnLoadProductsListener {

    private ProductListModel model;
    private ProductListContract.View view;

    public ProductsListPresenter(ManageProductsActivity view){
        this.view = view;
        model = new ProductListModel();
    }
    @Override
    public void onLoadProductsSuccess(List<Product> productList) {
        view.showProducts(productList);
    }

    @Override
    public void onLoadProductsError(String error) {
        view.showError(error);
    }

    @Override
    public void loadAllProducts() {
        model.loadAllProducts(this);
    }
}
