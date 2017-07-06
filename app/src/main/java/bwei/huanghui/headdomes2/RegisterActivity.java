package bwei.huanghui.headdomes2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import baseus.BaseAPP;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Button qie;
    private boolean isNight = false;
    private EditText phones;
    private EditText passxod1;
    private EditText passxod2;
    private EditText yanzheng;
    private Button tijiao;
    private Timer timer;
    private Button register;
    private ImageView imgLogo;
    private LinearLayout llParent;
    private static final String TAG = "RegistActivity";

    private static final int FLAG = 0x122;

    private static int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == FLAG) {
                int current = (int) msg.obj;
                if (current > 0) {
                    register.setText("(" + current + ")秒钟之后重新获取");
                } else {
                    register.setEnabled(true);
                    register.setText("重新获取");
                    if (timer != null) {
                        timer.cancel();
                    }
                    count = 60;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        initfind();
    }

    private void initfind() {
        qie = (Button) findViewById(R.id.id_qiehuan);
        phones = (EditText) findViewById(R.id.phones);
        passxod1 = (EditText) findViewById(R.id.passxod1);
        passxod2 = (EditText) findViewById(R.id.passxod2);
        yanzheng = (EditText) findViewById(R.id.yan_zheng);
        register = (Button) findViewById(R.id.re_gi_ster);
        tijiao = (Button) findViewById(R.id.ti_jiao);
        imgLogo = (ImageView) findViewById(R.id.img_logo);
        llParent = (LinearLayout) findViewById(R.id.bg_view_parent);
        qie.setOnClickListener(this);
        register.setOnClickListener(this);
        tijiao.setOnClickListener(this);

    }

    /**
     * 自带短信验证
     *
     * @param v
     */
    private void onReg(View v) {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

// 提交用户信息（此方法可以不调用）
                    //  registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //切换日夜间
            case R.id.id_qiehuan:
                isNight = !isNight;
                BaseAPP.getInstance().setNight(isNight);
                swictMode(isNight);
                // 得到Window的视图
                View view = getWindow().getDecorView();
                changeTextColor((ViewGroup) view);

                changeImage();
                break;
            //获取验证码
            case R.id.re_gi_ster:
                register.setEnabled(false);
                String username = phones.getText().toString().trim();
                check();
                EventHandler eh = new EventHandler() {

                    @Override
                    public void afterEvent(int event, int result, Object data) {

                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i(TAG, "afterEvent: 回调完成");
                            //回调完成
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                Log.i(TAG, "afterEvent: 提交验证码成功");
                                // 校验成功，可以把数据提交给服务器，做跳转

                                // 提交验证码成功
                            } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                Log.i(TAG, "afterEvent: 获取验证码成功");
                                //获取验证码成功
                            } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                                Log.i(TAG, "afterEvent: 返回支持发送验证码的国家列表");
                                //返回支持发送验证码的国家列表
                            }
                        } else {
                            ((Throwable) data).printStackTrace();
                        }
                    }
                };

                SMSSDK.registerEventHandler(eh); //注册短信回调
                SMSSDK.getVerificationCode("86", username);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count--;
                        Message msg = Message.obtain();
                        msg.what = FLAG;
                        msg.obj = count;
                        handler.sendMessage(msg);
                    }
                }, 0, 1000);
                break;
            //提交按钮时校验
            case R.id.ti_jiao:
                String phone = phones.getText().toString().trim();
                String code = yanzheng.getText().toString().trim();
                // 短信验证码校验
                SMSSDK.submitVerificationCode("86", phone, code);
                Log.i(TAG, "afterEvent: 注册成功。。" + phone);
                break;

        }
    }

    /**
     * 日夜间模式切换的时候改变自身的图片
     */
    private void changeImage() {
        imgLogo.setImageResource(BaseAPP.getInstance().getResId(R.mipmap.default_user_leftdrawer));
       llParent.setBackgroundColor(BaseAPP.getInstance().isNight() ? Color.GRAY : Color.WHITE);
    }

    // 在视图可见可交互的时候才能改变视图
    @Override
    protected void onResume() {
        super.onResume();
        // 一进入Activity，先拿到全局的isNight
        isNight = BaseAPP.getInstance().isNight();
        if (isNight) {
            swictMode(isNight);
        }
        changeTextColor((ViewGroup) getWindow().getDecorView());
        changeImage();
    }

    private void check() {
    }
}
