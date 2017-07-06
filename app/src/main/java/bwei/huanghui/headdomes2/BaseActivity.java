package bwei.huanghui.headdomes2;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import baseus.BaseAPP;

public class BaseActivity extends AppCompatActivity {
    private WindowManager manager;
    WindowManager.LayoutParams params;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        getSupportActionBar().hide();
        setContentView(R.layout.activity_base);
        //初始化蒙板
        initview();
    }
    /**
     * 改变文字的颜色
     * @param viewGroup
     */
    public void changeTextColor(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            // 判断子控件是否是textview
            if (viewGroup.getChildAt(i) instanceof TextView) {
                TextView txt = (TextView) viewGroup.getChildAt(i);
                // 得到模式的颜色值
                int resId = BaseAPP.getInstance().getResId(Color.BLACK);
                txt.setTextColor(resId);
            } else if(viewGroup.getChildAt(i) instanceof ViewGroup){
//                viewGroup.getChildAt(i).setBackgroundColor(Color.BLACK);
                // 递归调用
                changeTextColor((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }




    /**
     * 切换日夜间模式
     * @param isNight
     */

public  void swictMode(boolean isNight ){
    //夜间模式的时候添加上蒙版
    if (isNight) {
        manager.addView(view,params);
    } else {
        //非夜间模式移除蒙版
        manager.removeViewImmediate(view);
    }
}
    private void initview() {
        view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorhei));
        //得到系统的WindowManager
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
// 布局参数
        // 1. 布局文件类型，默认的是TYPE_APPLICATION
        // 2. FLAG，设置无焦点，不可触摸
        // 3. 图像的格式，透明

        params=new  WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE ,
                PixelFormat.TRANSPARENT);
    }
}
