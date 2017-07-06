package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bwei.huanghui.headdomes2.PindaoActivity;
import bwei.huanghui.headdomes2.R;
import constant.TeTles;


/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/9.15:32
 */

public class Homefrag extends Fragment {

    private View view;
    private TabLayout tab;
    private List<Fragment> list_fragment = new ArrayList<>();                                //定义要装fragment的列表
    private List<TeTles.ResultBean.DateBean> list_title2 = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private ViewPager vp;
    private String uri;
    private HomeFrafment hoFragment;
    ArrayList<String> uris = new ArrayList<>();
    private ImageButton pindao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    private void initview() {
        tab = (TabLayout) view.findViewById(R.id.tablayout);
        vp = (ViewPager) view.findViewById(R.id.vp);
        pindao = (ImageButton) view.findViewById(R.id.pindao);
        pindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), PindaoActivity.class);
                startActivity(it);
            }
        });
        //将名称加载tab名字列表
        list_title.add("推荐");
        list_title.add("热点");
        list_title.add("北京");
        list_title.add("视频");
        list_title.add("社会");
        list_title.add("图片");
        list_title.add("问答");
        list_title.add("科技");
        list_title.add("汽车");
        list_title.add("财经");
        list_title.add("军事");
        list_title.add("段子");
        list_title.add("国际");
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < list_title.size(); i++) {
            tab.addTab(tab.newTab().setText(list_title.get(i)));
            hoFragment = new HomeFrafment();
            list_fragment.add(hoFragment);
        }
       /* //从网络获取的数据
        //intetle();
        // tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < list_title.size(); i++) {
            tab.addTab(tab.newTab().setText(list_title.get(i).getTitle()));
            uri = list_title.get(i).getUri();
            uris.add(uri);
            hoFragment = new HomeFrafment();
            list_fragment.add(hoFragment);

        }
        hoFragment.newInstance(uris);*/
        inVpager();
    }

    private void inVpager() {
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return list_fragment.get(position);
            }

            @Override
            public int getCount() {

                return list_fragment.size();
            }

            //此方法用来显示tab上的名字
            @Override
            public CharSequence getPageTitle(int position) {
               // return list_title.get(position % list_title.size()).getTitle();

           return list_title.get(position%list_title.size());
            }
        });
        tab.setupWithViewPager(vp);
    }

    private void intetle() {
        String url = "http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news";
        RequestParams params = new RequestParams();
        params.setUri(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    TeTles teTles = gson.fromJson(result, TeTles.class);
                    List<TeTles.ResultBean.DateBean> date = teTles.getResult().getDate();
                    list_title2.addAll(date);
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

}
