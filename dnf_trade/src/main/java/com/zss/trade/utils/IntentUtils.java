package com.zss.trade.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity跳转工具类
 * Created by Administrator on 2016/11/10.
 */
public class IntentUtils {

    public static void intent(Context context, Class cls) {
        intent(context, cls, false);
    }

    public static void intent(Context context, Class cls, boolean isFinish) {
        intent(context, null, cls, isFinish);

    }

    public static void intent(Context context, Bundle bundle, Class cls, boolean isFinish) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }
}
