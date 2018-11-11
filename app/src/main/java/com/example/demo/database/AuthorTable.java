package com.example.demo.database;

import android.provider.BaseColumns;

public class AuthorTable implements BaseColumns {
    public static final String TABLE_NAME = "author";

    public static final String AUTHOR_URI = BookContentProvider.SCHEME + BookContentProvider.AUTHORITY +"/" + AuthorTable.TABLE_NAME;
    public static final String AUTHOR_ITEM_URI = BookContentProvider.SCHEME + BookContentProvider.AUTHORITY +"/" + AuthorTable.TABLE_NAME + "/";

    public static final String Author_ID = "author_id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String AGE = "age";
}
