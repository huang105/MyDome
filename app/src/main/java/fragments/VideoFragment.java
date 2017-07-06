package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import baseus.MyVideoAdapter;
import bwei.huanghui.headdomes2.R;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/22.17:27
 */

public class VideoFragment extends Fragment {
    private ListView xLv;
    private List<String> list = new ArrayList<>();
    private MyVideoAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fvideo_layout, container, false);
        return  view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
    }
    private void initView() {

        xLv = (ListView) getView().findViewById(R.id.list_video);

    }

    private void initData() {

        for (int i = 0; i < 10; i++) {
            list.add("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=221" +i + "1&editionType=default&source=ucloud");
        }
        adapter = new MyVideoAdapter(getContext(),list);
        xLv.setAdapter(adapter);
    }
}
