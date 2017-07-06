package ulist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/22.11:59
 */

public class Preferencesa {
    //定义静态常量
    private static final String shared_name = "user_guide";

    private static SharedPreferences sp;

    //调用的方法
    public static String getString(Context context, String key, String defaultValues) {

        SharedPreferences sp = context.getSharedPreferences(shared_name, context.MODE_PRIVATE);

        return sp.getString(key, defaultValues);

    }

    //调用的方法
    public static void setString(Context context, String key, String Values) {

        SharedPreferences sp = context.getSharedPreferences(shared_name, context.MODE_PRIVATE);

        sp.edit().putString(key, Values).commit();

    }

}
