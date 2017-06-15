package com.zss.trade.ui;

import java.util.ArrayList;
import java.util.List;

import com.zss.trade.R;
import com.zss.trade.goods.FragmentWeapon;
import com.zss.trade.ui.base.BaseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 物价
 * Created by Administrator on 2016/11/9.
 */
public class FragmentGoods extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static FragmentGoods newInstance() {
        Bundle args = new Bundle();
        FragmentGoods fragment = new FragmentGoods();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //初始化标题以及Fragment内容
        this.fmList.clear();
        this.titleList.clear();
        titleList.add("武器");
        titleList.add("装备");
        titleList.add("首饰");
        titleList.add("辅助");
        titleList.add("材料");
        titleList.add("消耗品");
        titleList.add("副职业");

        FragmentWeapon fm1 = FragmentWeapon.newInstance();
        FragmentWeapon fm2 = FragmentWeapon.newInstance();
        FragmentWeapon fm3 = FragmentWeapon.newInstance();
        FragmentWeapon fm4 = FragmentWeapon.newInstance();
        FragmentWeapon fm5 = FragmentWeapon.newInstance();
        FragmentWeapon fm6 = FragmentWeapon.newInstance();
        FragmentWeapon fm7 = FragmentWeapon.newInstance();

        fmList.add(fm1);
        fmList.add(fm2);
        fmList.add(fm3);
        fmList.add(fm4);
        fmList.add(fm5);
        fmList.add(fm6);
        fmList.add(fm7);

        mViewPager.setAdapter(new TabFragmentAdapter(getChildFragmentManager(), fmList, titleList));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<String> titleList = new ArrayList<>();
    private List<FragmentWeapon> fmList = new ArrayList<>();

    class TabFragmentAdapter extends FragmentPagerAdapter {
        List<String> titleList;
        List<FragmentWeapon> fmList;

        public TabFragmentAdapter(FragmentManager fm, List<FragmentWeapon> fmList, List<String> titleList) {
            super(fm);
            this.fmList = fmList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int position) {
            return fmList.get(position);
        }

        @Override
        public int getCount() {
            if (fmList == null) {
                return 0;
            } else {
                return fmList.size();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
