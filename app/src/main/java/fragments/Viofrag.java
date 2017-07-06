package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bwei.huanghui.headdomes2.R;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/9.15:32
 */

public class Viofrag extends Fragment {
    private List<Fragment> list_fragment = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private View view;
    private TabLayout tab;
    private ViewPager vp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_vio, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    private void initview() {
        tab = (TabLayout) view.findViewById(R.id.tab_video);
        vp = (ViewPager) view.findViewById(R.id.vp_video);

        //将名称加载tab名字列表
        list_title.add("推荐");
        list_title.add("音乐");
        list_title.add("搞笑");
        list_title.add("社会");
        list_title.add("小品");
        list_title.add("生活");
        list_title.add("娱乐");
        list_title.add("呆萌");
        list_title.add("原创");
        list_title.add("游戏");
        list_title.add("开眼");
        list_title.add("再看一遍");

        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < list_title.size(); i++) {
            tab.addTab(tab.newTab().setText(list_title.get(i)));
          VideoFragment videoFragment=new VideoFragment();
            list_fragment.add(videoFragment);
        }
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


}
