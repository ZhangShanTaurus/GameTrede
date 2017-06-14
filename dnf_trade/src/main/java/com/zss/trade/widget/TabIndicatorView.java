/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.widget;

import com.zss.trade.R;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TabIndicatorView
 * 作者 zhangss
 * 实现的主要功能:首页FragmentTabHost底部选项卡View
 * 创建日期 2017/6/14
 */
public class TabIndicatorView extends RelativeLayout {

    private ImageView mTabImage;
    private TextView mTabText;

    private int mNormalResId;
    private int mFocusResId;

    public TabIndicatorView(Context context) {
        this(context, null);
    }

    public TabIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tab_indicator_layout, this);
        mTabText = (TextView) view.findViewById(R.id.tab_text);
        mTabImage = (ImageView) view.findViewById(R.id.tab_img);
    }

    public void setTabText(String tabText) {
        mTabText.setText(tabText);
    }

    public void setTabText(@StringRes int tabText) {
        mTabText.setText(tabText);
    }

    public void setTabImageStateIcon(@DrawableRes int normalResId, @DrawableRes int focusResId) {
        this.mNormalResId = normalResId;
        this.mFocusResId = focusResId;
        mTabImage.setImageResource(normalResId);
    }

    public void setTabIndicatorChecked(boolean checked) {
        mTabText.setTextColor(getResources().getColor(checked ? R.color.main_tab_indicator_checked_color
                : R.color.main_tab_indicator_normal_color));//设置选中文字颜色
        mTabText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(//为什么这里要设置为:COMPLEX_UNIT_PX?
                checked ? R.dimen.tab_indicator_text_checked_size : R.dimen.tab_indicator_text_normal_size));//设置选中文字大小
        mTabImage.setImageResource(checked ? mFocusResId : mNormalResId);
    }

}
