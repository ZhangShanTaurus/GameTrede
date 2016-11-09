package com.zss.game_trade.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zss.game_trade.R;
import com.zss.game_trade.ui.basic.BaseFragment;

/**
 * 买入
 * Created by Administrator on 2016/11/9.
 */
public class FragmentBuy extends BaseFragment {

    public static FragmentBuy newInstance() {
        Bundle args = new Bundle();
        FragmentBuy fragment = new FragmentBuy();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        initView(view);
        Toast.makeText(getContext(), "FragmentBuy onCreateView()", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Toast.makeText(getContext(), "FragmentBuy onHiddenChanged()--" + hidden, Toast.LENGTH_SHORT).show();
    }

    private void initView(View view) {
    }
}
