/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.goods_type;

import java.util.List;

import com.zss.trade.DnfTradeApp;
import com.zss.trade.R;
import com.zss.trade.gen.GoodsTypeDao;
import com.zss.trade.model.GoodsType;
import com.zss.trade.ui.base.BaseFragmentActivity;
import com.zss.trade.utils.ToastUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 添加物品类型Activity
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/15
 */
public class AddGoodsTypeActivity extends BaseFragmentActivity {

    private EditText mGoodTypeEdit;
    private GoodsTypeDao mGoodsTypeDao;
    private List<GoodsType> mGoodsTypeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mGoodsTypeDao = DnfTradeApp.getInstances().getDaoSession().getGoodsTypeDao();
        mGoodsTypeList = mGoodsTypeDao.queryBuilder().build().list();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_goods_type;
    }


    @Override
    protected String getTitleString() {
        return "物品类型";
    }

    private void initView() {
        mGoodTypeEdit = (EditText) findViewById(R.id.et_add_goods_type);
        Button mAddGoodsTypeBtn = (Button) findViewById(R.id.btn_add_goods_type);
        mAddGoodsTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGoodsType();
            }
        });
    }

    private void addGoodsType() {
        String typeDesc = mGoodTypeEdit.getText().toString().trim();
        if (TextUtils.isEmpty(typeDesc)) {
            ToastUtils.toastShort(this, R.string.hint_goods_type_not_null);
        } else {
            GoodsType goodsType = new GoodsType();
            goodsType.setTypeDesc(typeDesc);
            if (mGoodsTypeList.contains(goodsType)) {
                ToastUtils.toastShort(this, R.string.hint_goods_type_is_exist);
            } else {
                long id = mGoodsTypeDao.insert(goodsType);
                mGoodTypeEdit.setText("");
                ToastUtils.toastShort(this, id >= 0 ? R.string.hint_insert_success : R.string.hint_insert_failure);
            }
        }
    }
}
