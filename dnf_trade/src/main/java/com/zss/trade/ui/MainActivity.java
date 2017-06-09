package com.zss.trade.ui;

import java.util.ArrayList;
import java.util.List;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zss.trade.R;
import com.zss.trade.ui.base.BaseFragment;
import com.zss.trade.ui.base.BaseFragmentActivity;
import com.zss.trade.widget.AddGoodsView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

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
        initViews();
        initBottomNavigationBar();
        fragmentList = getFragmentList();
        setDefaultFragment();
    }

    private void initViews() {
        AddGoodsView addGoods = (AddGoodsView) findViewById(R.id.add_goods);
        addGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "add goods", Toast.LENGTH_SHORT).show();
            }
        });
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
                transaction.add(R.id.container, fragment);
            }
            transaction.commitAllowingStateLoss();
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
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.tab_goods)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.tab_deal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.tab_chart)))
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
        transaction.replace(R.id.container, FragmentGoods.newInstance());
        transaction.commitAllowingStateLoss();
    }

    /**
     * 添加Fragment
     */
    private List<BaseFragment> getFragmentList() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(FragmentGoods.newInstance());
        fragments.add(FragmentDeal.newInstance());
        fragments.add(FragmentChart.newInstance());
        return fragments;
    }
}
