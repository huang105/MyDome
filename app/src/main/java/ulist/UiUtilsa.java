package ulist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import bwei.huanghui.headdomes2.R;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/22.11:59
 */

public class UiUtilsa {
    //获取主题

    public static int getAppTheme(Context ctx) {

        String value = Preferencesa.getString(ctx, "activity_theme", "1");

        switch (Integer.valueOf(value)) {
            case 1:

                return R.style.AppTheme;//白色主题

            case 2:

                return R.style.AppTheme_Black;

            default:
                return R.style.AppTheme;//默认白色

        }

    }
//切换主题

//当然也可以使用资源ID来进行标记

    public static void switchAppTheme(Context ctx) {

        String value = Preferencesa.getString(ctx, "activity_theme", "1");

        switch (Integer.valueOf(value)) {

            case 1:
                Preferencesa.setString(ctx, "activity_theme", "2");

                break;

            case 2:

                Preferencesa.setString(ctx, "activity_theme", "1");

                break;
            default:

                Preferencesa.setString(ctx, "activity_theme", "1");

                break;

        }

    }
}
