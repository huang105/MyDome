package baseus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/27.11:10
 */

public class BaseUiListener implements IUiListener {
    private Context context;
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private ImageView imageView;
    private TextView tv;
    private View include;
    private LinearLayout ll1;
    private static final String TAG = "LoginQQdeng";

    public BaseUiListener() {
    }

    public BaseUiListener(Context context, Tencent mTencent, UserInfo mUserInfo, ImageView imageViews, TextView textView, LinearLayout ll1, View include) {
        this.context = context;
        this.mTencent = mTencent;
        this.mUserInfo = mUserInfo;
        this.imageView=imageViews;
        this.tv=textView;
        this.include=include;
        this.ll1=ll1;

    }

    @Override
    public void onComplete(Object response) {

        Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "response:" + response);
        JSONObject obj = (JSONObject) response;
        try {
            String openID = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("expires_in");
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(accessToken,expires);
            QQToken qqToken = mTencent.getQQToken();
            mUserInfo = new UserInfo(context.getApplicationContext(),qqToken);
            mUserInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object response) {
                    Log.e(TAG,"登录成功"+response.toString());
                    JSONObject obj = (JSONObject) response;
                    try {
                        String figureurl_1 = obj.getString("figureurl_qq_1");
                        String nickname = obj.getString("nickname");
                        ll1.setVisibility(View.GONE);
                        include.setVisibility(View.VISIBLE);

                        tv.setText(nickname);
                        Glide.with(context).load(figureurl_1).into(imageView);

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
        Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(context, "授权取消", Toast.LENGTH_SHORT).show();

    }
}
