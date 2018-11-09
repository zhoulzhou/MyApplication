package com.example.demo.database;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class StudentProfileProvider extends ContentProvider {

    // tag 打日志用
    private static final String TAG = "StudentProfileProvider";

    // 数据库的名字
    private static final String DATABASE_NAME = "students_info.db";

    // 数据库版本号
    private static final int DATABASE_VERSION = 1;

    /**
     * A UriMatcher instance
     */
    private static final UriMatcher sUriMatcher;

    // 匹配成功的返回值 这里代表多行匹配成功
    private static final int STUDENTS = 1;

    // 匹配成功的返回值 这里代表多单行匹配成功
    private static final int STUDENTS_ID = 2;

    /**
     * 注意看一下这个哈希表 这个哈希表实际上是主要为了SQLiteQueryBuilder这个类的 setProjectionMap这个方法使用的
     *
     * 他的值的初始化我放在静态代码块里面，这个地方实际上主要是为了多表查询而存在的
     *
     * 比如你要多表查询的时候 你有2个表 一个表A 一个表B 你join的时候 肯定需要重命名某个表的某个列
     *
     * 比如你要把表A的 name1 这个列名重命名成 a.name1 那你就可以add一个key value对，key为name1
     *
     * value 为a.name1 即可。当然咯 如果你不想重命名或者只是单表查询那就只需要吧key 和value
     *
     * 的值都写成 一样的即可
     *
     */
    private static HashMap<String, String> sStudentsProjectionMap;

    // 定义数据库helper.
    private DatabaseHelper mOpenHelper;

    // 静态代码块执行
    static {

        // 先构造urimatcher
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sUriMatcher.addURI(StudentProfile.AUTHORITY, "students", STUDENTS);

        // #代表任意数字 *一般代表任意文本
        sUriMatcher.addURI(StudentProfile.AUTHORITY, "students/#", STUDENTS_ID);

        // 因为我们这里是单表查询 所以这个地方key和value的值都写成固定的就可以了
        sStudentsProjectionMap = new HashMap<String, String>();

        sStudentsProjectionMap.put(StudentProfile.Students._ID,
                StudentProfile.Students._ID);

        sStudentsProjectionMap.put(StudentProfile.Students.COLUMN_NAME_AGE,
                StudentProfile.Students.COLUMN_NAME_AGE);

        sStudentsProjectionMap.put(
                StudentProfile.Students.COLUMN_NAME_CREATE_DATE,
                StudentProfile.Students.COLUMN_NAME_CREATE_DATE);

        sStudentsProjectionMap.put(
                StudentProfile.Students.COLUMN_NAME_MODIFICATION_DATE,
                StudentProfile.Students.COLUMN_NAME_MODIFICATION_DATE);

        sStudentsProjectionMap.put(StudentProfile.Students.COLUMN_NAME_NAME,
                StudentProfile.Students.COLUMN_NAME_NAME);

        sStudentsProjectionMap.put(StudentProfile.Students.COLUMN_NAME_NUMBER,
                StudentProfile.Students.COLUMN_NAME_NUMBER);
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * 对于自定义contentprovider来说CRUD的这几个方法的写法 要尽量保证 代码优美 和 容错性高
     *
     */

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(StudentProfile.Students.TABLE_NAME);

        // 先匹配uri
        switch (sUriMatcher.match(uri)) {
            // 多行查询
            case STUDENTS:
                qb.setProjectionMap(sStudentsProjectionMap);
                break;
            // 单行查询
            case STUDENTS_ID:
                qb.setProjectionMap(sStudentsProjectionMap);
                qb.appendWhere(StudentProfile.Students._ID
                        + "="
                        + uri.getPathSegments().get(
                        StudentProfile.Students.STUDENT_ID_PATH_POSITION));
                break;
            default:
                throw new IllegalArgumentException("Unknown uri" + uri);
        }

        // 如果没有传orderby的值过来 那我们就使用默认的
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = StudentProfile.Students.DEFAULT_SORT_ORDER;
        } else {
            // 如果传过来了 就使用传来的值
            orderBy = sortOrder;
        }

        // 开始操作数据库
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, orderBy);

        // 这个地方要解释一下 这句语句的作用，很多人自定义provider的时候 在query方法里面都忘记
        // 写这句话，有的人写了也不知道这句话是干嘛的，实际上这句话就是给我们的cursor加了一个观察者
        // 有兴趣的可以看一下sdk里面这个函数的源码，非常简单。那么他的实际作用就是如果返回的cursor
        // 被用在SimpleCursorAdapter 类似的这种adapter的话，一旦uri所对应的provider数据发生了变化
        // 那么这个adapter里的数据是会自己变化刷新的。这句话起的就是这个作用 有兴趣的可以自己写代码
        // 验证一下 如果把这句话删除掉的话 adapter里的数据是不会再uri更新的时候 自动更新的
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    /**
     * 这个地方的返回值 一定要和manifest你配置activity的时候data 字段的值相同 不然会报错
     */
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case STUDENTS:
                return StudentProfile.Students.CONTENT_TYPE;
            case STUDENTS_ID:
                return StudentProfile.Students.CONTENT_ITEM_TYPE;
            default:
                // 注意这个地方记得不匹配的时候抛出异常信息 这样当比人调用失败的时候会知道哪里不对
                throw new IllegalArgumentException("Unknown uri" + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {

        if (sUriMatcher.match(uri) != STUDENTS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;

        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        // 下面几行代码实际上就是告诉我们对于某些表而言 默认的字段的值 可以在insert里面自己写好
        // 不要让调用者去手动再做重复劳动，我们应该允许调用者写入最少的字段的值 来完成db的insert
        // 操作
        Long now = Long.valueOf(System.currentTimeMillis());

        if (values.containsKey(StudentProfile.Students.COLUMN_NAME_CREATE_DATE) == false) {
            values.put(StudentProfile.Students.COLUMN_NAME_CREATE_DATE, now);
        }
        if (values
                .containsKey(StudentProfile.Students.COLUMN_NAME_MODIFICATION_DATE) == false) {
            values.put(StudentProfile.Students.COLUMN_NAME_MODIFICATION_DATE,
                    now);
        }

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        long rowId = db.insert(StudentProfile.Students.TABLE_NAME,
                StudentProfile.Students.COLUMN_NAME_NAME, values);

        if (rowId > 0) {
            Uri stuUri = ContentUris.withAppendedId(
                    StudentProfile.Students.CONTENT_ID_URI_BASE, rowId);
            // 用于通知所有观察者数据已经改变
            getContext().getContentResolver().notifyChange(stuUri, null);
            return stuUri;
        }

        // 如果插入失败也最好抛出异常 通知调用者
        throw new SQLException("Failed to insert row into " + uri);

    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String finalWhere;

        int count;

        switch (sUriMatcher.match(uri)) {

            case STUDENTS:
                count = db.delete(StudentProfile.Students.TABLE_NAME, where,
                        whereArgs);
                break;

            case STUDENTS_ID:
                finalWhere = StudentProfile.Students._ID
                        + " = "
                        + uri.getPathSegments().get(
                        StudentProfile.Students.STUDENT_ID_PATH_POSITION);

                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }

                count = db.delete(StudentProfile.Students.TABLE_NAME, finalWhere,
                        whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where,
                      String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        String finalWhere;

        switch (sUriMatcher.match(uri)) {

            case STUDENTS:

                count = db.update(StudentProfile.Students.TABLE_NAME, values,
                        where, whereArgs);
                break;

            case STUDENTS_ID:

                finalWhere = StudentProfile.Students._ID
                        + " = "
                        + uri.getPathSegments().get(
                        StudentProfile.Students.STUDENT_ID_PATH_POSITION);

                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }

                count = db.update(StudentProfile.Students.TABLE_NAME, values,
                        finalWhere, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    // 自定义helper
    static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + StudentProfile.Students.TABLE_NAME
                    + " (" + StudentProfile.Students._ID
                    + " INTEGER PRIMARY KEY,"
                    + StudentProfile.Students.COLUMN_NAME_NAME + " TEXT,"
                    + StudentProfile.Students.COLUMN_NAME_NUMBER + " TEXT,"
                    + StudentProfile.Students.COLUMN_NAME_AGE + " INTEGER,"
                    + StudentProfile.Students.COLUMN_NAME_CREATE_DATE
                    + " INTEGER,"
                    + StudentProfile.Students.COLUMN_NAME_MODIFICATION_DATE
                    + " INTEGER" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            // 数据库升级的时候 这边的代码 不写了,看各自的业务逻辑了,一般建议大家在这个地方多打一些日志
        }

    }
}