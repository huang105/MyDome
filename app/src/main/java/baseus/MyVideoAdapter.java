package baseus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bwei.huanghui.headdomes2.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by TIGER LEE on 2017/6/15.
 */

public class MyVideoAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList=new ArrayList<>();

    public MyVideoAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList=mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(mContext, R.layout.vodeo_base, null);
            holder = new MyViewHolder();
            holder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.player_list_video);
            convertView.setTag(holder);

        } else {
              holder= (MyViewHolder) convertView.getTag();
        }
        holder.jcVideoPlayer.setUp(mList.get(position), JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
        Glide.with(mContext).load("http://f2.kkmh.com/image/151203/ub3hlynnl.jpg-w180").into(holder.jcVideoPlayer.thumbImageView);

        return convertView;
    }

     class MyViewHolder {

         private JCVideoPlayerStandard jcVideoPlayer;
    }

}
