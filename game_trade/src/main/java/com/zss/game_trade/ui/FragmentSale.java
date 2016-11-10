package com.zss.game_trade.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zss.game_trade.R;
import com.zss.game_trade.ui.basic.BaseFragment;

/**
 * 卖出
 * Created by Administrator on 2016/11/9.
 */
public class FragmentSale extends BaseFragment {

    public static FragmentSale newInstance() {
        Bundle args = new Bundle();
        FragmentSale fragment = new FragmentSale();
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
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }


}