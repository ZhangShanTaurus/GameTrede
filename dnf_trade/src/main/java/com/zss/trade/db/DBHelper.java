/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.db;

import org.greenrobot.greendao.database.Database;

import com.zss.trade.gen.DaoMaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * DBHelper
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/14
 */
public class DBHelper extends DaoMaster.DevOpenHelper {
    private static final String DB_NAME = "dnf_trade.db";

    public DBHelper(Context context) {
        super(context, DB_NAME);
    }

    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //// TODO: 2017/6/14 数据库升级或迁移在这里写
    }
}
