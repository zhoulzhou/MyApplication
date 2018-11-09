package com.example.demo.database;

import android.content.ContentProvider;
import android.content.UriMatcher;

//https://blog.csdn.net/CodingEnding/article/details/72810365
public class BookContentProvider extends ContentProvider {
    private static final int BOOK=0;
    private static final int BOOK_ITEM=1;
    private static final int AUTHOR=2;
    private static final int AUTHOR_ITEM=3;

    private static final String AUTHORITY="com.example.providerlibrary.provider";

    private BookOpenHelper bookOpenHelper;
    private static UriMatcher uriMatcher;

    static{//初始化
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"Book",BOOK);
        uriMatcher.addURI(AUTHORITY,"Book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"Author",AUTHOR);
        uriMatcher.addURI(AUTHORITY,"Author/#",AUTHOR_ITEM);
    }

    @Override
    public boolean onCreate() {
        bookOpenHelper=new BookOpenHelper(getContext(),"ProviderDemo.db",null,1);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database=bookOpenHelper.getWritableDatabase();
        long dataId=0;//将数据插入表中后的数据id
        Uri insertUri=null;
        switch(uriMatcher.match(uri)){
            case BOOK:
            case BOOK_ITEM:
                dataId=database.insert("Book",null,values);
                insertUri=Uri.parse("content://com.example.providerlibrary.provider/Book/"+dataId);
                break;
            case AUTHOR:
            case AUTHOR_ITEM:
                dataId=database.insert("Author",null,values);
                insertUri=Uri.parse("content://com.example.providerlibrary.provider/Author/"+dataId);
                break;
            default:
                break;
        }
        return insertUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database=bookOpenHelper.getWritableDatabase();
        int deleteNum=0;//被删除的数据条数
        switch(uriMatcher.match(uri)){
            case BOOK:
                //访问Book表
                deleteNum=database.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                //访问Book表中的某一条数据
                String bookDeleteId=uri.getPathSegments().get(1);//被删除数据的id
                deleteNum=database.delete("Book","id=?",new String[]{bookDeleteId});
                break;
            case AUTHOR:
                //访问Author表
                deleteNum=database.delete("Author",selection,selectionArgs);
                break;
            case AUTHOR_ITEM:
                //访问Author表中的某一条数据
                String authorDeleteId=uri.getPathSegments().get(1);//被删除数据的id
                deleteNum=database.delete("Author","id=?",new String[]{authorDeleteId});
                break;
            default:
                break;
        }
        return deleteNum;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database=bookOpenHelper.getWritableDatabase();
        int updateNum=0;//被更新的数据条数
        switch (uriMatcher.match(uri)){
            case BOOK:
                updateNum=database.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookUpdateId=uri.getPathSegments().get(1);//被更新的数据id
                updateNum=database.update("Book",values,"id=?",new String[]{bookUpdateId});
                break;
            case  AUTHOR:
                updateNum=database.update("Author",values,selection,selectionArgs);
                break;
            case AUTHOR_ITEM:
                String authorUpdateId=uri.getPathSegments().get(1);
                updateNum=database.update("Author",values,"id=?",new String[]{authorUpdateId});
                break;
            default:
                break;
        }
        return updateNum;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database=bookOpenHelper.getReadableDatabase();
        Cursor cursor=null;//用于返回的Cursor对象
        switch (uriMatcher.match(uri)){
            case BOOK:
                cursor=database.query("Book",projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookQueryId=uri.getPathSegments().get(1);//用于查询的id
                cursor=database.query("Book",projection,"id=?",new String[]{bookQueryId},
                        null,null,sortOrder);
                break;
            case AUTHOR:
                cursor=database.query("Author",projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case AUTHOR_ITEM:
                String authorQueryId=uri.getPathSegments().get(1);//用于查询的id
                cursor=database.query("Author",projection,"id=?",new String[]{authorQueryId},
                        null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        String type="";
        switch (uriMatcher.match(uri)){
            case BOOK:
                type="vnd.android.cursor.dir/vnd."+AUTHORITY+".Book";
                break;
            case BOOK_ITEM:
                type="vnd.android.cursor.item/vnd."+AUTHORITY+".Book";
                break;
            case AUTHOR:
                type="vnd.android.cursor.dir/vnd."+AUTHORITY+".Author";
                break;
            case AUTHOR_ITEM:
                type="vnd.android.cursor.item/vnd."+AUTHORITY+"Author";
                break;
            default:
                break;
        }
        return type;
    }
}