package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import baseus.HomeAdapter;
import bwei.huanghui.headdomes2.R;
import bwei.huanghui.headdomes2.YemianActivity;
import constant.TetleFrag2;
import constant.TetleFragment;
import me.maxwin.view.XListView;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/14.20:41
 */

public class HomeFrafment extends Fragment implements XListView.IXListViewListener {
    public static final String KEY = "arg";

    private XListView xLv;
    private String mUrl;
    private String mUri;
    private List<TetleFragment.ResultBean.DataBean> list2 = new ArrayList<>();
    private List<TetleFrag2.ResultBean.DataBean> list = new ArrayList<>();
    private static HomeFrafment myFragment;


   /* public HomeFrafment(String url) {
        this.mUrl = url;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fhome_layout, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
        initView();
    }

    private void initDate() {
      /*  ArrayList<String> stringArrayList = myFragment.getArguments().getStringArrayList(KEY);
        for (String s : stringArrayList) {
            getDataFromServer(s);
        }*/
        getDataFromServer();
    }


    public static Fragment newInstance(ArrayList<String> str) {
        myFragment = new HomeFrafment();
        Bundle bundle = new Bundle();
//        bundle.putString(KEY, str);
        bundle.putStringArrayList(KEY, str);
        myFragment.setArguments(bundle);

        return myFragment;
    }


    private void initView() {
        xLv = (XListView) getView().findViewById(R.id.xlist_view);
        xLv.setPullRefreshEnable(true);
        xLv.setPullLoadEnable(true);
        xLv.setXListViewListener(this);

        HomeAdapter adapter = new HomeAdapter(getContext(), list);
        xLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(getActivity(), YemianActivity.class);
               it.putExtra("url",list.get(i-1).getUrl());
                startActivity(it);
            }
        });
        xLv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void getDataFromServer() {
        String url = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
        RequestParams params = new RequestParams();
        params.setUri(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    TetleFrag2 bean = gson.fromJson(result, TetleFrag2.class);
                    List<TetleFrag2.ResultBean.DataBean> data = bean.getResult().getData();
                    list.addAll(data);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });

    }

   /* public void getDataFromServer(String uri) {
        String url = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri=" + uri;
        RequestParams params = new RequestParams();
        params.setUri(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    TetleFragment fragmentBean = gson.fromJson(result, TetleFragment.class);
                    List<TetleFragment.ResultBean.DataBean> data = fragmentBean.getResult().getData();
                    list.addAll(data);
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }*/

    /**
     * 刷新
     */

    @Override
    public void onRefresh() {
        initDate();
        stopLoad();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        initDate();
        stopLoad();
    }

    /**
     * 停止
     */
    public void stopLoad() {
        xLv.stopRefresh();
        xLv.stopLoadMore();
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间
        String nowTime = df.format(new Date(System.currentTimeMillis()));
        // 释放时提示正在刷新时的当前时间
        xLv.setRefreshTime(nowTime);
    }


}

