package com.example.demo.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

//https://blog.csdn.net/CodingEnding/article/details/72810365
public class BookContentProvider extends ContentProvider {

    //匹配多行书籍
    private static final int BOOK = 0;
    //匹配单行书籍 某一行的数据
    private static final int BOOK_ITEM = 1;
    private static final int AUTHOR = 2;
    private static final int AUTHOR_ITEM = 3;

    // uri的scheme部分 这个部分是固定的写法
    private static final String SCHEME = "content://";
    private static final String AUTHORITY = "com.example.bookprovider";
    private static final String BOOK_URI = SCHEME + AUTHORITY +"/" + Book.TABLE_NAME;
    private static final String BOOK_ITEM_URI = SCHEME + AUTHORITY +"/" + Book.TABLE_NAME + "/";
    private static final String AUTHOR_URI = SCHEME + AUTHORITY +"/" + Author.TABLE_NAME;
    private static final String AUTHOR_ITEM_URI = SCHEME + AUTHORITY +"/" + Author.TABLE_NAME + "/";

    private BookDBOpenHelper bookOpenHelper;
    private static UriMatcher uriMatcher;

    static {//初始化
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Book.TABLE_NAME, BOOK);
        uriMatcher.addURI(AUTHORITY, Book.TABLE_NAME + "/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, Author.TABLE_NAME, AUTHOR);
        uriMatcher.addURI(AUTHORITY, Author.TABLE_NAME + "/#", AUTHOR_ITEM);
    }

    @Override
    public boolean onCreate() {
        bookOpenHelper = new BookDBOpenHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = bookOpenHelper.getWritableDatabase();
        long dataId = 0;//将数据插入表中后的数据id
        Uri insertUri = null;
        switch (uriMatcher.match(uri)) {
            case BOOK:
            case BOOK_ITEM:
                dataId = database.insert(Book.TABLE_NAME, null, values);
                insertUri = Uri.parse(BOOK_ITEM_URI + dataId);
                break;
            case AUTHOR:
            case AUTHOR_ITEM:
                dataId = database.insert(Author.TABLE_NAME, null, values);
                insertUri = Uri.parse(AUTHOR_ITEM_URI + dataId);
                break;
            default:
                // 如果插入失败也最好抛出异常 通知调用者
                throw new SQLException("Failed to insert row into " + uri);
        }
        // 用于通知所有观察者数据已经改变
        getContext().getContentResolver().notifyChange(insertUri, null);
        return insertUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = bookOpenHelper.getWritableDatabase();
        int deleteNum = 0;//被删除的数据条数
        switch (uriMatcher.match(uri)) {
            case BOOK:
                //访问Book表
                deleteNum = database.delete(Book.TABLE_NAME, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                //访问Book表中的某一条数据
                String bookDeleteId = uri.getPathSegments().get(1);//被删除数据的id
                deleteNum = database.delete(Book.TABLE_NAME, Book.BOOK_ID + "=?", new String[]{bookDeleteId});
                break;
            case AUTHOR:
                //访问Author表
                deleteNum = database.delete(Author.TABLE_NAME, selection, selectionArgs);
                break;
            case AUTHOR_ITEM:
                //访问Author表中的某一条数据
                String authorDeleteId = uri.getPathSegments().get(1);//被删除数据的id
                deleteNum = database.delete(Author.TABLE_NAME, Author.Author_ID + "=?", new String[]{authorDeleteId});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteNum;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = bookOpenHelper.getWritableDatabase();
        int updateNum = 0;//被更新的数据条数
        switch (uriMatcher.match(uri)) {
            case BOOK:
                updateNum = database.update(Book.TABLE_NAME, values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookUpdateId = uri.getPathSegments().get(1);//被更新的数据id
                updateNum = database.update(Book.TABLE_NAME, values, Book.BOOK_ID+"=?", new String[]{bookUpdateId});
                break;
            case AUTHOR:
                updateNum = database.update(Author.TABLE_NAME, values, selection, selectionArgs);
                break;
            case AUTHOR_ITEM:
                String authorUpdateId = uri.getPathSegments().get(1);
                updateNum = database.update(Author.TABLE_NAME, values, Author.Author_ID+"=?", new String[]{authorUpdateId});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updateNum;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = bookOpenHelper.getReadableDatabase();
        Cursor cursor = null;//用于返回的Cursor对象
        switch (uriMatcher.match(uri)) {
            case BOOK:
                cursor = database.query(Book.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookQueryId = uri.getPathSegments().get(1);//用于查询的id
                cursor = database.query(Book.TABLE_NAME, projection, Book.BOOK_ID+"=?", new String[]{bookQueryId},
                        null, null, sortOrder);
                break;
            case AUTHOR:
                cursor = database.query(Author.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case AUTHOR_ITEM:
                String authorQueryId = uri.getPathSegments().get(1);//用于查询的id
                cursor = database.query(Author.TABLE_NAME, projection, Author.Author_ID+"=?", new String[]{authorQueryId},
                        null, null, sortOrder);
                break;
            default:
                break;
        }
        // 这个地方要解释一下 这句语句的作用，很多人自定义provider的时候 在query方法里面都忘记
        // 写这句话，有的人写了也不知道这句话是干嘛的，实际上这句话就是给我们的cursor加了一个观察者
        // 有兴趣的可以看一下sdk里面这个函数的源码，非常简单。那么他的实际作用就是如果返回的cursor
        // 被用在SimpleCursorAdapter 类似的这种adapter的话，一旦uri所对应的provider数据发生了变化
        // 那么这个adapter里的数据是会自己变化刷新的。这句话起的就是这个作用 有兴趣的可以自己写代码
        // 验证一下 如果把这句话删除掉的话 adapter里的数据是不会再uri更新的时候 自动更新的
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    /**
     * 这个地方的返回值 一定要和manifest你配置activity的时候data 字段的值相同 不然会报错
     */
    @Override
    public String getType(Uri uri) {
        String type = "";
        switch (uriMatcher.match(uri)) {
            case BOOK:
                type = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".Book";
                break;
            case BOOK_ITEM:
                type = "vnd.android.cursor.item/vnd." + AUTHORITY + ".Book";
                break;
            case AUTHOR:
                type = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".Author";
                break;
            case AUTHOR_ITEM:
                type = "vnd.android.cursor.item/vnd." + AUTHORITY + "Author";
                break;
            default:
                // 注意这个地方记得不匹配的时候抛出异常信息 这样当比人调用失败的时候会知道哪里不对
                throw new IllegalArgumentException("Unknown uri" + uri);
        }
        return type;
    }
}