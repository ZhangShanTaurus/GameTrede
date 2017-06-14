package com.zss.trade.ui;

import com.zss.trade.R;
import com.zss.trade.ui.base.BaseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 统计
 * Created by Administrator on 2016/11/9.
 */
public class FragmentChart extends BaseFragment {

    public static FragmentChart newInstance() {
        Bundle args = new Bundle();
        FragmentChart fragment = new FragmentChart();
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
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
