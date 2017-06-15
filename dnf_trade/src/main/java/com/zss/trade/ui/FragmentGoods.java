package com.zss.trade.ui;

import java.util.List;

import com.zss.trade.DnfTradeApp;
import com.zss.trade.R;
import com.zss.trade.gen.GoodsTypeDao;
import com.zss.trade.goods.FragmentWeapon;
import com.zss.trade.goods_type.AddGoodsTypeActivity;
import com.zss.trade.model.GoodsType;
import com.zss.trade.ui.base.BaseFragment;
import com.zss.trade.utils.IntentUtils;

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

    private TabFragmentAdapter mAdapter;
    private GoodsTypeDao mGoodsTypeDao;

    public static FragmentGoods newInstance() {
        Bundle args = new Bundle();
        FragmentGoods fragment = new FragmentGoods();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoodsTypeDao = DnfTradeApp.getInstances().getDaoSession().getGoodsTypeDao();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<GoodsType> tabList = mGoodsTypeDao.queryBuilder().build().list();
        mAdapter.setGoodsTypeList(tabList);
    }

    private void initView(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        View mEditChannel = view.findViewById(R.id.edit_channel);
        mEditChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.intent(getActivity(), AddGoodsTypeActivity.class);
            }
        });
        mAdapter = new TabFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    static class TabFragmentAdapter extends FragmentPagerAdapter {
        private List<GoodsType> goodsTypeList;

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentWeapon.newInstance();
        }

        @Override
        public int getCount() {
            return goodsTypeList == null ? 0 : goodsTypeList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return goodsTypeList.get(position).getTypeDesc();
        }

        public void setGoodsTypeList(List<GoodsType> goodsTypeList) {
            this.goodsTypeList = goodsTypeList;
            notifyDataSetChanged();
        }
    }
}
