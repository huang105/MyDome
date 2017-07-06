package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import bean.MessageBean;
import bean.UserEntity;
import bwei.huanghui.headdomes2.R;
import bwei.huanghui.headdomes2.RegisterActivity;
import constant.LoginCode;
import ulist.CheckDataUtlis;
import ulist.MD5Utils;

import static android.content.ContentValues.TAG;


/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/9.15:32
 */
public class Loginfrag extends Fragment {


    private EditText name;
    private EditText pass;
    private Button login;
    private TextView zhuce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_lonig, null);
        name = (EditText) view.findViewById(R.id.user_name);
        pass = (EditText) view.findViewById(R.id.user_pass);
        login = (Button) view.findViewById(R.id.login);
        zhuce = (TextView) view.findViewById(R.id.zhuce);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString().trim();
                String userpass = pass.getText().toString().trim();
                //   Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
                denglu(username, userpass);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), RegisterActivity.class);
                startActivity(it);

            }
        });


        return view;
    }


    private void ButClick() {
        String username = name.getText().toString().trim();
        String userpass = pass.getText().toString().trim();
        Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
        //denglu(username, userpass);
    }

    public void denglu(String username, String userpass) {
        //用户校验
        if (!checkUser(username)) {
            Toast.makeText(getActivity(), "请输入合法的手机号！", Toast.LENGTH_SHORT).show();
            return;
        }
        //登录和加密密码
        RequestParams entity = new RequestParams("http://172.18.47.36/api/login.php");
        entity.addQueryStringParameter("username", username);
        entity.addQueryStringParameter("password", MD5Utils.getMD5(userpass));

        x.http().get(entity, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG, "onSuccess: " + result);
                        Gson gson = new Gson();
                        MessageBean<List<UserEntity>> userBean = gson.fromJson(result, MessageBean.class);
                        // 4、在callback方法里面做状态码的判断
                        if (userBean.isSuccess()) {
                            // 登录成功
                            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                        } else {
                            // 登录失败之后通过状态码判断是哪种错误
                            int code = userBean.getCode();
                            if (code == LoginCode.LOGIN_FAILURE_PHONE_UNREGISTED) {
                                // 用户未注册
                                Toast.makeText(getActivity(), "用户未注册", Toast.LENGTH_SHORT).show();
                            } else if (code == LoginCode.LOGIN_FAILURE_PASSWORD_ERROR) {
                                // 用户名/密码错误
                                Toast.makeText(getActivity(), "用户名/密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: 解析异常");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }
        );

    }

    /**
     * 做用户名密码校验
     *
     * @return
     */
    private boolean checkUser(String username) {

        return CheckDataUtlis.isPhoneNo(username);

    }
}
