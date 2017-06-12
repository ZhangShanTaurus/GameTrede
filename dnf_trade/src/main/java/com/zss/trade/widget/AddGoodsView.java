package com.zss.trade.widget;

import com.zss.trade.R;
import com.zss.trade.utils.ScreenUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 添加物品View
 * Created by Administrator on 2016/11/9.
 */
public class AddGoodsView extends View {
    private static final String TAG = AddGoodsView.class.getSimpleName();

    /**
     * 内心圆相关
     */
    private static final float DEFAULT_INNER_RADIUS = 50f;
    private int mInnerCircleColor;
    private float mInnerCircleRadius;
    private Paint mInnerCirclePaint;

    /**
     * 外心圆相关
     */
    private static final float DEFAULT_OUTER_RADIUS = 75f;
    private int mOuterCircleColor;
    private float mOuterCircleRadius;
    private Paint mOuterCirclePaint;

    /**
     * 直线相关
     */
    private static final float DEFAULT_INNER_LINE_WIDTH = 6f;
    private Paint mLinePaint;
    private int mInnerLineColor;
    private float mInnerLineWidth;

    /**
     * 屏幕宽高
     */
    private int mScreenWidth;
    private int mScreenHeight;

    public AddGoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();
        initAttribute(attrs);
        initCirclePaint();
        initLinePaint();
    }

    private void initAttribute(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.AddGoodsView);
        mInnerCircleColor =
                typedArray.getColor(R.styleable.AddGoodsView_innerCircleColor, getResources().getColor(R.color.Tan));
        mInnerCircleRadius = typedArray.getDimension(R.styleable.AddGoodsView_innerCircleRadius, DEFAULT_INNER_RADIUS);
        mOuterCircleColor =
                typedArray.getColor(R.styleable.AddGoodsView_outerCircleColor, getResources().getColor(R.color.Olive));
        mOuterCircleRadius = typedArray.getDimension(R.styleable.AddGoodsView_outerCircleRadius, DEFAULT_OUTER_RADIUS);
        mInnerLineColor =
                typedArray.getColor(R.styleable.AddGoodsView_innerLineColor, getResources().getColor(R.color.DimGray));
        mInnerLineWidth = typedArray.getDimension(R.styleable.AddGoodsView_innerLineWidth, DEFAULT_INNER_LINE_WIDTH);
        typedArray.recycle();
    }

    /**
     * 初始化圆圈画笔
     */
    private void initCirclePaint() {
        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(mOuterCircleColor);

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(mInnerCircleColor);
    }

    /**
     * 初始化直线画笔
     */
    private void initLinePaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(mInnerLineColor);
        mLinePaint.setStrokeWidth(mInnerLineWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = (int) (mOuterCircleRadius * 2);
        setMeasuredDimension(getMeasuredSize(widthMeasureSpec, defaultSize),
                getMeasuredSize(heightMeasureSpec, defaultSize));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float coordinateX = getWidth() / 2;
        float coordinateY = getHeight() / 2;
        canvas.drawCircle(coordinateX, coordinateY, mOuterCircleRadius, mOuterCirclePaint);//外心圆
        canvas.drawCircle(coordinateX, coordinateY, mInnerCircleRadius, mInnerCirclePaint);//内心圆
        float distance = Math.abs(mOuterCircleRadius - mInnerCircleRadius);//外心圆与内心圆半径之差
        canvas.drawLine(coordinateX, distance, coordinateX, getHeight() - distance, mLinePaint);//竖线
        canvas.drawLine(distance, coordinateY, getWidth() - distance, coordinateY, mLinePaint);//横线
    }

    private int getMeasuredSize(int measureSpec, int defaultSize) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(defaultSize, specSize);
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                result = defaultSize;
                break;
        }
        return result;
    }

    /**
     * 滑动相关
     */
    private int mLastX;
    private int mLastY;
    private int mMoveX;
    private int mMoveY;

    /**
     * 距离屏幕底部的高度
     */
    private int mBottomHeight;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                mMoveX = mLastX;
                mMoveY = mLastY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - mLastX;
                int dy = (int) event.getRawY() - mLastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;

                if (left < 0) {
                    left = 0;
                    right = left + getWidth();
                }
                if (right > mScreenWidth) {
                    right = mScreenWidth;
                    left = right - getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + getHeight();
                }
                if (bottom > (mScreenHeight - mBottomHeight)) {
                    bottom = (mScreenHeight - mBottomHeight);
                    top = bottom - getHeight();
                }

                layout(left, top, right, bottom);
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //避免滑出触发点击事件
                if ((int) (event.getRawX() - mMoveX) != 0
                        || (int) (event.getRawY() - mMoveY) != 0) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setBottomHeight(int mBottomHeight) {
        this.mBottomHeight = mBottomHeight;
    }
}
