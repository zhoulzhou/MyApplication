package com.example.demo.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.myapplication.R;

public class DBTestActivity extends AppCompatActivity{
    private BookDBOpenHelper bookDBOpenHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookDBOpenHelper = new BookDBOpenHelper(this);
        insertDBTest();
    }

    private void testDB(){

    }

    private void insertDBTest(){
        SQLiteDatabase db = bookDBOpenHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(BookTable.BOOK_ID,110);
        values.put(BookTable.AUTHOR,"authorxxx");
        values.put(BookTable.NAME,"booknamexxx");
        values.put(BookTable.PRESS,"pressxxx");
        values.put(BookTable.PRICE,11.1);
        db.insert(BookTable.TABLE_NAME,null,values);
        db.setTransactionSuccessful();
    }
}
