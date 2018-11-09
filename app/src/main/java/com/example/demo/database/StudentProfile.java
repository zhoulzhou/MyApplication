package com.example.demo.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 常量类
 */
public final class StudentProfile {

    /**
     * 一般来说 我们的authority都是设置成 我们这个常量类的包名+类名
     */
    public static final String AUTHORITY = "com.example.providertest.StudentProfile";

    /**
     * 注意这个构造函数 是私有的 目的就是让他不能被初始化
     */
    private StudentProfile() {

    }

    /**
     * 实现了这个BaseColumns接口 可以让我们少写几行代码
     *
     */
    public static final class Students implements BaseColumns {
        /**
         * 这个类同样也是不能被初始化的
         */
        private Students() {

        }

        // 定义我们的表名
        public static final String TABLE_NAME = "students";

        /**
         * 下面开始uri的定义
         */

        // uri的scheme部分 这个部分是固定的写法
        private static final String SCHEME = "content://";

        // 部分学生
        private static final String PATH_STUDENTS = "/students";

        // 某一个学生
        private static final String PATH_STUDENTS_ID = "/students/";

        /**
         * path这边的第几个值是指的位置 我们设置成第一个位置
         */
        public static final int STUDENT_ID_PATH_POSITION = 1;

        // 这个表的基本的uri格式
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY
                + PATH_STUDENTS);
        // 某一条数据的基本uri格式 这个通常在自定義的provider的insert方法里面被调用
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME
                + AUTHORITY + PATH_STUDENTS_ID);

        /**
         * 定义一下我们的mime类型 注意一下mime类型的写法
         *
         * 一般都是后面vnd.应用程序的包名.表名
         */

        // 多行的mime类型
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.example.providertest.students";
        // 单行的mime类型
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.com.example.providertest.students";

        /**
         * 既然provider提供了查询的方法 我们肯定要设置一个默认的排序方式 这里我们就默认让他根据创建的时间 来降序排序
         */
        public static final String DEFAULT_SORT_ORDER = "created DESC";

        /**
         * 下面就是表的列定义了
         */

        // 学生的名字
        public static final String COLUMN_NAME_NAME = "name";
        // 学生的年龄
        public static final String COLUMN_NAME_AGE = "age";
        // 学生的学号
        public static final String COLUMN_NAME_NUMBER = "number";
        // 这个学生创建的时间
        public static final String COLUMN_NAME_CREATE_DATE = "created";
        // 这个学生入库以后修改的时间
        public static final String COLUMN_NAME_MODIFICATION_DATE = "modified";

    }

}