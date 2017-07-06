package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bwei.huanghui.headdomes2.R;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/9.15:32
 */

public class Toufrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_toutiao,container,false);
    }
}
