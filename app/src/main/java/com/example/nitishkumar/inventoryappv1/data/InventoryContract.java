package com.example.nitishkumar.inventoryappv1.data;

import android.provider.BaseColumns;

public final class InventoryContract {

    public InventoryContract() {
    }

    public static final class BooksEntry implements BaseColumns{
        /************* Name of Table *********************/
        public final static String TABLE_NAME = "books";
        /************** All Column Names ******************/
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "Product_Name";
        public final static String COLUMN_PRICE = "Price";
        public final static String COLUMN_QUANTITY = "Quantity";
        public final static String COLUMN_SUPPLIER_NAME = "Supplier_Name";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "Supplier_Phone_Number";
    }
}
