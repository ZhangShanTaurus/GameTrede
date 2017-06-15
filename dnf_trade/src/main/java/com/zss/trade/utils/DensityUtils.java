/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 单位转换工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/15
 */
public class DensityUtils {

    private DensityUtils() {
    }

    public static DisplayMetrics getDisplayMetrics(Context ctx) {
        return ctx.getApplicationContext().getResources().getDisplayMetrics();
    }

    public static float getScreenDpi(Context ctx) {
        return (float) getDisplayMetrics(ctx).densityDpi;
    }

    public static float getScreenDensity(Context ctx) {
        return getDisplayMetrics(ctx).density;
    }

    public static float getScreenScaledDensity(Context ctx) {
        return getDisplayMetrics(ctx).scaledDensity;
    }

    /**
     * dp转换px
     *
     * @param ctx 上下文
     * @param dip dp值
     *
     * @return px值
     */
    public static int dp2px(Context ctx, float dip) {
        float density = getScreenDensity(ctx);
        return (int) (dip * density + 0.5F);
    }

    /**
     * px转换dp
     *
     * @param ctx 上下文
     * @param px  px值
     *
     * @return dp值
     */
    public static int px2dp(Context ctx, float px) {
        float density = getScreenDensity(ctx);
        return (int) (px / density + 0.5F);
    }

    /**
     * px转换sp
     *
     * @param ctx 上下文
     * @param px  px值
     *
     * @return sp值
     */
    public static int px2sp(Context ctx, float px) {
        float scale = getScreenScaledDensity(ctx);
        return (int) (px / scale + 0.5F);
    }

    /**
     * sp转换px
     *
     * @param ctx 上下文
     * @param sp  sp值
     *
     * @return px值
     */
    public static int sp2px(Context ctx, float sp) {
        float fontScale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5F);
    }
}
