package com.bawei.yuchenlei20190731.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.yuchenlei20190731.R;
import com.bawei.yuchenlei20190731.activity.Main2Activity;
import com.bawei.yuchenlei20190731.adatper.ListViewDataper;
import com.bawei.yuchenlei20190731.base.BaseFragment;
import com.bawei.yuchenlei20190731.bean.DataInof;
import com.bawei.yuchenlei20190731.sqlite.MyDao;
import com.bawei.yuchenlei20190731.sqlite.MySqlite;
import com.bawei.yuchenlei20190731.util.NetWorkUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class ShowFragment extends BaseFragment {

    private PullToRefreshListView pull;
    int page =1;
    private String path="http://www.xieast.com/api/news/news.php?page=";
    private ArrayList<DataInof.Data> datas =new ArrayList<>();

    @Override
    protected int onLayout() {
        return R.layout.showfragment;
    }
private Handler handler =new Handler();
    @Override
    protected void onView(View view) {
        pull = view.findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page=1;
                datas.clear();
                onDate();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull.onRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                onDate();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull.onRefreshComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    protected void onData() {
        boolean isNetwork = NetWorkUtils.netWorkUtils().NetWork(getActivity());
        if (isNetwork) {
            onDate();

            Toast.makeText(getActivity(), "有网", Toast.LENGTH_SHORT).show();

        }else {
            if (page == 1) {
                SQLiteDatabase readableDatabase = new MySqlite(getActivity()).getReadableDatabase();
                Cursor date = readableDatabase.query("date", null, null, null, null, null, null, null);
                String json = date.getString(date.getColumnIndex("json"));
                Log.i("TTT", "onData: "+json);
                Gson gson = new Gson();
                DataInof dataInof = gson.fromJson(json, DataInof.class);
                 ArrayList<DataInof.Data> data = dataInof.getData();
                ListViewDataper listViewDataper = new ListViewDataper(getActivity(),data);
                pull.setAdapter(listViewDataper);
            }


            Toast.makeText(getActivity(), "没网", Toast.LENGTH_SHORT).show();
        }
    }

    private void onDate() {

        NetWorkUtils.netWorkUtils().getData(path + page, new NetWorkUtils.mCallBack() {
            @Override
            public void getSer(Object obj) {
                String json = obj.toString();
                Gson gson = new Gson();
                DataInof dataInof = gson.fromJson(json, DataInof.class);
                final ArrayList<DataInof.Data> data = dataInof.getData();
                datas.addAll(data);
                MyDao myDao = new MyDao(getActivity());
                myDao.add(json);
                Toast.makeText(getActivity(), "数据缓存完成！！！", Toast.LENGTH_SHORT).show();
                pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), Main2Activity.class);
                        String url = data.get(position).getUrl();
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
                ListViewDataper listViewDataper = new ListViewDataper(getActivity(),datas);
                pull.setAdapter(listViewDataper);
            }

            @Override
            public void getEro(String mes) {

            }
        });
    }
}
