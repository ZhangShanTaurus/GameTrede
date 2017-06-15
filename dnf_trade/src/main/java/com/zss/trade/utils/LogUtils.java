/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.trade.utils;

import android.util.Log;

/**
 * 打印日志工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/6/15
 */
public class LogUtils {
    /**
     * 是否是调试模式
     */
    private static boolean sIsDebugMode = true;

    /**
     * 默认tag
     */
    private static final String DEFAULT_TAG = "dnf_trade";

    private LogUtils() {
    }

    /**
     * Verbose
     *
     * @param msg
     */
    public static void v(String msg) {
        v(DEFAULT_TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (sIsDebugMode) {
            Log.v(tag, msg);
        }
    }

    /**
     * Debug
     *
     * @param msg
     */
    public static void d(String msg) {
        d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (sIsDebugMode) {
            Log.d(tag, msg);
        }
    }

    /**
     * Info
     *
     * @param msg
     */
    public static void i(String msg) {
        i(DEFAULT_TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (sIsDebugMode) {
            Log.i(tag, msg);
        }
    }

    /**
     * Warm
     *
     * @param msg
     */
    public static void w(String msg) {
        w(DEFAULT_TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (sIsDebugMode) {
            Log.w(tag, msg);
        }
    }

    /**
     * Error
     *
     * @param msg
     */
    public static void e(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (sIsDebugMode) {
            Log.e(tag, msg);
        }
    }
}
