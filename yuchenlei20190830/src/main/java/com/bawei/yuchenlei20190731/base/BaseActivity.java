package com.bawei.yuchenlei20190731.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawei.yuchenlei20190731.R;

/**
 * 于晨雷
 * 2019-7-31 09:32:07
 * Fragment基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        加载布局
        setContentView(getLayout());
//        找控件的抽象方法
        getView();
        //    数据的抽象方法
        getData();
    }

    protected abstract int getLayout();



    protected abstract void getView();

    protected abstract void getData();
}
