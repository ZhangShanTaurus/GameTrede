package com.zss.trade;

import com.zss.trade.db.DBHelper;
import com.zss.trade.gen.DaoMaster;
import com.zss.trade.gen.DaoSession;

import android.app.Application;

/**
 * DnfTradeApp
 * Created by Administrator on 2016/11/8.
 */
public class DnfTradeApp extends Application {

    private DaoSession mDaoSession;

    private static DnfTradeApp sInstances;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstances = this;
        initGreenDao();
    }

    public static DnfTradeApp getInstances() {
        return sInstances;
    }

    /**
     * 初始化greenDao
     */
    private void initGreenDao() {
        DBHelper mHelper = new DBHelper(this, null);
        DaoMaster mDaoMaster = new DaoMaster(mHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
