package com.bawei.yuchenlei20190731.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.yuchenlei20190731.R;
import com.bawei.yuchenlei20190731.adatper.FragmentAdatper;
import com.bawei.yuchenlei20190731.base.BaseActivity;
import com.bawei.yuchenlei20190731.fragment.FindFragment;
import com.bawei.yuchenlei20190731.fragment.LongFragment;
import com.bawei.yuchenlei20190731.fragment.ShowFragment;

import java.util.ArrayList;

/**
 * MainActivity继承基类
 * 于晨雷
 * 2019-7-31 09:28:07
 */
public class MainActivity extends BaseActivity {
//创建集合
private ArrayList<Fragment> fragments=new ArrayList<>();
private ArrayList<String> strings=new ArrayList<>();
    private ViewPager vp;
    private TabLayout tab;
//重写加载布局的方法
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

//重写找id的的方法
    @Override
    protected void getView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }
//重写加载数据的方法
    @Override
    protected void getData() {
        fragments.add(new ShowFragment());
        fragments.add(new FindFragment());
        fragments.add(new LongFragment());
        strings.add("首页");
        strings.add("找人");
        strings.add("未登录");
        tab.setupWithViewPager(vp);
        FragmentAdatper fragmentAdatper = new FragmentAdatper(getSupportFragmentManager(),fragments,strings);
        vp.setAdapter(fragmentAdatper);
    }
}
