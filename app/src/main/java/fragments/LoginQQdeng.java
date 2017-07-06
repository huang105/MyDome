package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.Tencent;

import baseus.BaseUiListener;
import bwei.huanghui.headdomes2.R;
import ulist.UiUtilsa;


/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/22.11:13
 */

public class LoginQQdeng extends Fragment implements View.OnClickListener {
    private static final String TAG = "LoginQQdeng+++++++";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    private View include;
    private LinearLayout ll_2;
    private TextView nikeName;
    private ImageView touxiang;

    private TextView yejian;
    private View qq;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qq_frag, null);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,getActivity().getApplicationContext());
        qq = view.findViewById(R.id.qq);
        qq.setOnClickListener(this);
        yejian = (TextView) view.findViewById(R.id.yejian);
        include = view.findViewById(R.id.include);
        ll_2 = (LinearLayout) view.findViewById(R.id.ll_2);
        nikeName = (TextView) view.findViewById(R.id.nikeName);
        touxiang = (ImageView) view.findViewById(R.id.touxiang);

        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UiUtilsa.switchAppTheme(getActivity());
                reload();
            }
        });
        return view;
    }
    /**
     * 构造淡入淡出效果
     */
    public void reload() {

        Intent intent = getActivity().getIntent();

        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
      getActivity().finish();


        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);

    }




  /*  public void buttonLogin(View v){
        Toast.makeText(getActivity(),"点击了QQ",Toast.LENGTH_SHORT).show();
        ll_2.setVisibility(View.GONE);
        *//**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 *//*
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(getActivity(),"all", mIUiListener);
    }*/
    @Override
    public void onClick(View view) {
        Log.e(TAG,"*-*-*-*-*-*--*-*-*-*-*-*-点击率*-*-*-*-*-*-**--**-*-");
       // include.setVisibility(View.VISIBLE);
        //ll_2.setVisibility(View.GONE);
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener(getContext(),mTencent,mUserInfo,touxiang,nikeName,ll_2,include);
        //all表示获取所有权限
        mTencent.login(getActivity(),"all", mIUiListener);

    }


  /*  *//**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     *//*
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getActivity().getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject obj = (JSONObject) response;
                        try {
                            String figureurl_1 = obj.getString("figureurl_qq_1");

                            String nickname = obj.getString("nickname");

                            include.setVisibility(View.VISIBLE);
                            nikeName.setText(nickname);
                            Glide.with(getActivity()).load(figureurl_1).into(touxiang);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();

        }

    }*/

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
*/
}
