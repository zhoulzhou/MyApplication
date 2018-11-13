package com.example.demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.demo.common.LogUtils;

public class BookDBOpenHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "bookInfo.db";
    private static final int DB_VERSION = 1;

    public BookDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + BookTable.TABLE_NAME + " ("
                + BookTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BookTable.BOOK_ID + " INTEGER, "
                + BookTable.NAME + " TEXT, " + BookTable.PRICE + " REAL, "
                + BookTable.AUTHOR + " TEXT, " + BookTable.PRESS + " TEXT" + ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + AuthorTable.TABLE_NAME + " ("
                + AuthorTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AuthorTable.Author_ID + " INTEGER, "
                + AuthorTable.NAME + " TEXT, " + AuthorTable.ADDRESS + " TEXT, "
                + AuthorTable.AGE + " INTEGER, " + AuthorTable.PHONE + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        LogUtils.d("BookDBOpenHelper onUpgrade");
    }
}
