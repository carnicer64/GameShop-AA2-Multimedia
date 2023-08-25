package com.svalero.gameshop_aa1_multimedia.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favourite {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long idProduct;
    private long  idClient;
    private String productName;
    private int barcode;

    public Favourite(long idProduct, long idClient, String productName, int barcode) {
        this.idProduct = idProduct;
        this.idClient = idClient;
        this.productName = productName;
        this.barcode = barcode;
    }

    public Favourite() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }
}
