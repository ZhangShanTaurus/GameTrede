package com.zss.trade.widget;

import com.zss.trade.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 添加物品View
 * Created by Administrator on 2016/11/9.
 */
public class AddGoodsView extends View {
    private static final String TAG = AddGoodsView.class.getSimpleName();

    /**
     * 内心圆半径
     */
    private static final int INNER_RADIUS = 50;

    /**
     * 外心圆半径
     */
    private static final int OUTER_RADIUS = 75;

    /**
     * 两个圆半径的差
     */
    private static final int DISTANCE = OUTER_RADIUS - INNER_RADIUS;

    /**
     * 默认大小
     */
    private static final int DEFAULT_SIZE = 150;

    /**
     * 外圆画笔
     */
    private Paint mOuterCirclePaint;

    /**
     * 内圆画笔
     */
    private Paint mInnerCirclePaint;

    /**
     * 直线画笔
     */
    private Paint mLinePaint;

    public AddGoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCirclePaint();
        initLinePaint();
    }

    /**
     * 初始化圆圈画笔
     */
    private void initCirclePaint() {
        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(getResources().getColor(R.color.Olive));

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(getResources().getColor(R.color.Tan));
    }

    /**
     * 初始化直线画笔
     */
    private void initLinePaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.DimGray));
        mLinePaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureSize(widthMeasureSpec), getMeasureSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float widthCenter = getWidth() / 2;
        float heightCenter = getHeight() / 2;
        canvas.drawCircle(widthCenter, heightCenter, OUTER_RADIUS, mOuterCirclePaint);
        canvas.drawCircle(widthCenter, heightCenter, INNER_RADIUS, mInnerCirclePaint);
        canvas.drawLine(widthCenter, DISTANCE, widthCenter, getHeight() - DISTANCE, mLinePaint);
        canvas.drawLine(DISTANCE, heightCenter, getWidth() - DISTANCE, heightCenter, mLinePaint);
    }

    private int getMeasureSize(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.AT_MOST:
                size = DEFAULT_SIZE;
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        return size;
    }
}
