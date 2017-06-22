/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.goods;

import java.io.File;

import com.zss.trade.ui.base.BaseFragment;
import com.zss.trade.utils.FileUtils;
import com.zss.trade.utils.LogUtils;
import com.zss.trade.utils.ToastUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        Button button = new Button(getContext());
        button.setText("导出");
        button.setLayoutParams(new ViewGroup.LayoutParams(100, 50));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = new File("/data/data/" + v.getContext().getPackageName() + "/databases");
                File targetDir = new File("/sdcard/dnf_trade/db_export");
                if (targetDir.exists()) {
                    FileUtils.deleteFileOrDir(targetDir);
                }
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }
                if (dir.exists() && dir.isDirectory()) {
                    File[] files = dir.listFiles();
                    for (File df : files) {
                        LogUtils.d(TAG, "path=" + df.getAbsolutePath());
                        try {
                            FileUtils.copyFile(df, new File(targetDir, df.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                ToastUtils.toastShort(v.getContext(), "已导出到" + targetDir.getAbsolutePath() + "目录");
            }
        });
        return button;
    }
}
