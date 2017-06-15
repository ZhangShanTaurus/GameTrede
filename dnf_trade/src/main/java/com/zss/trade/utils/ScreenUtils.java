/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 获取屏幕宽高工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/12
 */
public class ScreenUtils {

    private ScreenUtils() {
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     *
     * @return 屏幕宽
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高
     *
     * @param context 上下文
     *
     * @return 屏幕高
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图
     *
     * @param act           Activity
     * @param withStatusBar 是否包含状态栏
     *
     * @return Bitmap
     */
    public static Bitmap snapShot(Activity act, boolean withStatusBar) {
        View view = act.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Bitmap bp;
        int screenWidth = getScreenWidth(act);
        int screenHeight = getScreenHeight(act);
        if (withStatusBar) {
            Rect frame = new Rect();
            act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, screenWidth, screenHeight - statusBarHeight);
        } else {
            bp = Bitmap.createBitmap(bmp, 0, 0, screenWidth, screenHeight);
        }
        view.destroyDrawingCache();
        return bp;
    }
}
