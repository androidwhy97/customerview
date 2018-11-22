package com.bwie.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/10/31
 */
public class CustomerView extends View {

    private Paint mPaint;

    public CustomerView(Context context) {
        this(context,null);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        //画笔颜色
        mPaint.setColor(Color.RED);
        //抗锯齿
        mPaint.setAntiAlias(true);
        //画笔粗细
        mPaint.setStrokeWidth(20);
        //画笔的风格
        mPaint.setStyle(Paint.Style.STROKE);
        //透明度
        mPaint.setAlpha(25);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(100,100,mPaint);
    }
}
