package com.bawei.yuchenlei20190731.adatper;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.yuchenlei20190731.R;
import com.bawei.yuchenlei20190731.bean.DataInof;
import com.bawei.yuchenlei20190731.util.NetWorkUtils;

import java.util.ArrayList;

import javax.xml.validation.ValidatorHandler;

public class ListViewDataper extends BaseAdapter {
    Context context;
    ArrayList<DataInof.Data> data;

    public ListViewDataper(Context context, ArrayList<DataInof.Data> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         final ViewHandler viewHandler;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item, null);
            viewHandler = new ViewHandler();
            viewHandler.iv_thumbnail_pic_s = convertView.findViewById(R.id.iv_thumbnail_pic_s);
            viewHandler.tv_title = convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHandler);
        }else {
            viewHandler = (ViewHandler) convertView.getTag();
        }
        viewHandler.tv_title.setText(data.get(position).getTitle());
        NetWorkUtils.netWorkUtils().getBimData(data.get(position).getThumbnail_pic_s(), new NetWorkUtils.mCallBack() {
            @Override
            public void getSer(Object obj) {
                viewHandler.iv_thumbnail_pic_s.setImageBitmap((Bitmap) obj);
            }

            @Override
            public void getEro(String mes) {

            }
        });
        return convertView;
    }

    private class ViewHandler {
        ImageView iv_thumbnail_pic_s;
        TextView tv_title;
    }
}
