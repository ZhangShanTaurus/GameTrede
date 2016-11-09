package com.zss.game_trade.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zss.game_trade.R;
import com.zss.game_trade.ui.basic.BaseFragment;
import com.zss.game_trade.ui.basic.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 * Created by Administrator on 2016/11/9.
 */
public class MainActivity extends BaseFragmentActivity implements BottomNavigationBar.OnTabSelectedListener {

    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigationBar();
        fragmentList = getFragmentList();
        setDefaultFragment();
    }

    @Override
    public void onTabSelected(int position) {
        if (fragmentList != null && position < fragmentList.size()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            for (Fragment fragment : fragmentList) {
                if (fragment.isAdded()) {
                    transaction.hide(fragment);
                }
            }

            Fragment fragment = fragmentList.get(position);
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.layoutFrame, fragment);
            }
            transaction.commit();
        }
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);//设置模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);//设置背景样式
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "物价"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "买入"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "卖出"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "统计"))
                .setActiveColor(R.color.DeepSkyBlue)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的Fragment
     */
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layoutFrame, FragmentPrice.newInstance());
        transaction.commit();
    }

    /**
     * 添加Fragment
     */
    private List<BaseFragment> getFragmentList() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(FragmentPrice.newInstance());
        fragments.add(FragmentBuy.newInstance());
        fragments.add(FragmentSale.newInstance());
        fragments.add(FragmentStatistics.newInstance());
        return fragments;
    }
}
