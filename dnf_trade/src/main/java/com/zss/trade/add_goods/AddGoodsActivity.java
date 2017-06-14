/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.add_goods;

import java.util.List;
import java.util.Random;

import com.zss.trade.DnfTradeApp;
import com.zss.trade.R;
import com.zss.trade.entity.User;
import com.zss.trade.gen.UserDao;
import com.zss.trade.ui.base.BaseFragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 添加物品Activity
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/12
 */
public class AddGoodsActivity extends BaseFragmentActivity implements View.OnClickListener {

    private UserDao userDao;
    private Button mAddBtn;
    private Button mQueryBtn;
    private TextView mInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        userDao = DnfTradeApp.getInstances().getDaoSession().getUserDao();
        initView();
    }

    private void initView() {
        mAddBtn = (Button) findViewById(R.id.btn_add_goods);
        mAddBtn.setOnClickListener(this);
        mQueryBtn = (Button) findViewById(R.id.btn_query_goods);
        mQueryBtn.setOnClickListener(this);
        mInfoText = (TextView) findViewById(R.id.tv_info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_goods:
                addUser();
                break;
            case R.id.btn_query_goods:
                query();
                break;
        }
    }

    private void addUser() {
        User user = new User(null, "张山" + new Random().nextInt(9999), 23);
        userDao.insert(user);
    }

    private void deleteUser() {
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Id.le(10)).build().list();
        for (User user : userList) {
            userDao.delete(user);
        }
    }

    private void query() {
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Id.le(10)).build().list();
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : userList) {
            stringBuilder.append(user.toString()).append("\n");
        }
        mInfoText.setText(stringBuilder.toString());
    }
}
