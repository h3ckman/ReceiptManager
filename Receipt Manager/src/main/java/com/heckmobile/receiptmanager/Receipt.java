package com.heckmobile.receiptmanager;

import java.io.Serializable;

/**
 * Created by h3ckman on 12/13/13.
 */
public class Receipt implements Serializable{
    public static final String TABLE_NAME = "Receipts";
    public static final String _ID = "id";
    public static final String COLUMN_NAME_STORE = "store";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_CATEGORY = "category";
    public static final String COLUMN_NAME_PRICE = "price";
    public static final String COLUMN_NAME_NULLABLE = "nullable";

    public int id;
    public String store;
    public String category;
    public String description;
    public String price;


    public Receipt() {

    }

    public Receipt(int id, String store, String price) {
        this.id = id;
        this.store = store;
        this.price = price;
    }

    public Receipt(int id, String store, String price, String category) {
        this.id = id;
        this.store = store;
        this.category = category;
        this.price = price;
    }

    public Receipt(int id, String store, String price, String category, String description) {
        this.id = id;
        this.store = store;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
