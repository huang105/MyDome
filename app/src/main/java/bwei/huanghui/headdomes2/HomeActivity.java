package bwei.huanghui.headdomes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import baseus.BaseUiListener;
import fragments.Homefrag;
import fragments.LoginQQdeng;
import fragments.Loginfrag;
import fragments.Toufrag;
import fragments.Viofrag;
import ulist.UiUtilsa;

@ContentView(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    @ViewInject(R.id.radio_group)
    private RadioGroup radio;

    @ViewInject(R.id.tad1)
    RadioButton rad1;

    @ViewInject(R.id.tad2)
    RadioButton rad2;

    @ViewInject(R.id.tad3)
    RadioButton rad3;

    @ViewInject(R.id.tad4)
    RadioButton rad4;
    private Viofrag viofrag;
    private Homefrag honefrag;
    private Toufrag toufrag;
    private Loginfrag loginfrag;
    private  LoginQQdeng loginQQdeng;
    private Fragment fragment;
    private int theme = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //切换主题必须放在onCreate()之前

        if (savedInstanceState == null) {
            theme = UiUtilsa.getAppTheme(HomeActivity.this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        //去除标题
        getSupportActionBar().hide();
        x.view().inject(this);
        addFragment();
    }

    private void addFragment() {
        addfragment(new Homefrag());
    }

    @Event({R.id.tad1, R.id.tad2, R.id.tad3, R.id.tad4})
    private void butoncli(View view) {
        switch (view.getId()) {
            case R.id.tad1:
                if (honefrag == null ){
                   honefrag=new Homefrag();
                }
                addfragment(honefrag);
                break;
            case R.id.tad2:
                if (viofrag == null ){
                    viofrag=new Viofrag();
                }
                addfragment(viofrag);
                break;
            case R.id.tad3:
                if (toufrag == null ){
                    toufrag=new Toufrag();
                }
                addfragment(toufrag);
                break;
            case R.id.tad4:
              /*  if (loginfrag == null ){
                    loginfrag=new Loginfrag();
                }*/
                if (loginQQdeng == null ){
                    loginQQdeng=new LoginQQdeng();
                }
                addfragment(loginQQdeng);
                break;


        }
    }

    public void addfragment(Fragment f) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
             /*transaction.replace(R.id.con_fragmnet, f);
             transaction.commit();*/
        //添加到回退栈  string 类型的参数代表这一组操作起的别名
        transaction.addToBackStack("beiming");

        if (fragment != null) {
            //隐藏
            transaction.hide(fragment);
        }
        if (!f.isAdded()) {
            //isAdded对象是否被添加过
            transaction.add(R.id.con_fragmnet, f);
        }
        //显示当前
        transaction.show(f);
        transaction.commit();
        fragment = f;
    }
       @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            BaseUiListener  baseUiListener= new BaseUiListener();
            Tencent.onActivityResultData(requestCode,resultCode,data,baseUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
