/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.goods;

import com.zss.trade.R;
import com.zss.trade.ui.base.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 武器Fragment
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/14
 */
public class FragmentWeapon extends BaseFragment {

    public static FragmentWeapon newInstance() {
        Bundle args = new Bundle();

        FragmentWeapon fragment = new FragmentWeapon();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText(FragmentWeapon.class.getSimpleName());
        textView.setTextColor(getResources().getColor(R.color.Aqua));
        return textView;
    }
}
