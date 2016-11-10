package com.zss.game_trade.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.zss.game_trade.R;

/**
 * 拖动的View
 * Created by Administrator on 2016/11/9.
 */
public class DragView extends View {
    private static final int RADIUS = 100;
    private Paint paint;//画笔

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.DeepSkyBlue));//填充红色
        paint.setAlpha(50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
    }
}
