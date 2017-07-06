package baseus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import bwei.huanghui.headdomes2.R;
import constant.TetleFrag2;

/**
 * 类注释
 * 创建人：黄慧
 * 创建时间： 2017/6/15.17:31
 */

public class HomeAdapter extends BaseAdapter {
    final int TYPE0 = 0;
    final int TYPE1 = 1;
    Context context;
    private List<TetleFrag2.ResultBean.DataBean> list;

    public HomeAdapter(Context context, List<TetleFrag2.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;
            default:
                return TYPE0;
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*     @Override
         public int getItemViewType(int position) {
             switch (position) {
                 case 0:
                     return TYPE0;
                 case 1:
                     return TYPE1;
                 case 3:
                     return TYPE3;
                 default:
                     return TYPE0;
             }
         }
         @Override
         public int getViewTypeCount() {
             return 3;
         }*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TetleFrag2.ResultBean.DataBean bean = list.get(position);
        ViewHodel hodel = null;
        convertView = null;
        if (convertView == null) {
            hodel = new ViewHodel();

            switch (getItemViewType(position)) {
                case TYPE0:
                    convertView = View.inflate(context, R.layout.homefragmentr1_layout, null);
                    hodel.name_tetel= (TextView) convertView.findViewById(R.id.name_tetel);
                    hodel.author_name= (TextView) convertView.findViewById(R.id.author_name);
                    hodel.view_imget= (ImageView) convertView.findViewById(R.id.view_imget);
                    break;
                case TYPE1:
                    convertView = View.inflate(context, R.layout.homefragmentr2_layout, null);
                    hodel.name_tetel= (TextView) convertView.findViewById(R.id.name_tetel);
                    hodel.author_name= (TextView) convertView.findViewById(R.id.author_name);
                    hodel.vie_imge1= (ImageView) convertView.findViewById(R.id.vie_imget1);
                    hodel.vie_imge2= (ImageView) convertView.findViewById(R.id.vie_imget2);
                    hodel.vie_imge3= (ImageView) convertView.findViewById(R.id.vie_imget3);

                    break;
            }
            convertView.setTag(hodel);
        } else {
            hodel = (ViewHodel) convertView.getTag();
        }
        switch (getItemViewType(position)) {
            case TYPE0:
                hodel.name_tetel.setText(bean.getTitle());
                hodel.author_name.setText(bean.getAuthor_name());
                x.image().bind(hodel.view_imget,bean.getThumbnail_pic_s());
                break;
            case TYPE1:
                hodel.name_tetel.setText(bean.getTitle());
                hodel.author_name.setText(bean.getAuthor_name());
                x.image().bind(hodel.vie_imge1,bean.getThumbnail_pic_s());
                x.image().bind(hodel.vie_imge2,bean.getThumbnail_pic_s());
                x.image().bind(hodel.vie_imge3,bean.getThumbnail_pic_s());
                break;
        }

        return convertView;
    }

    class ViewHodel {
        TextView name_tetel,author_name;
        ImageView view_imget,vie_imge1,vie_imge2,vie_imge3;
    }
}


