package com.bawei.yuchenlei20190731.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.bawei.yuchenlei20190731.R;
import com.bawei.yuchenlei20190731.base.BaseActivity;

/**
 * 于晨雷
 * 2019-7-31 11:06:21
 * 详情页面
 */
public class Main2Activity extends BaseActivity {


    private WebView wab;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }
    @Override

    protected void getView() {
        wab = (WebView) findViewById(R.id.wab);
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.i("TTT", "getData: "+url);
        wab.loadUrl(url);

    }
}
