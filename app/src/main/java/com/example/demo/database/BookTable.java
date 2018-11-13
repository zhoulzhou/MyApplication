package com.example.demo.database;

import android.provider.BaseColumns;

public class BookTable implements BaseColumns {
    public static final String TABLE_NAME= "book";

    public static final String BOOK_URI = BookContentProvider.SCHEME + BookContentProvider.AUTHORITY +"/" + BookTable.TABLE_NAME;
    public static final String BOOK_ITEM_URI = BookContentProvider.SCHEME + BookContentProvider.AUTHORITY +"/" + BookTable.TABLE_NAME + "/";

    public static final String BOOK_ID = "book_id";
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String PRICE = "price";
    public static final String PRESS = "press";
}
