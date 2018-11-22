package com.bwie.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.security.interfaces.DSAKey;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/3
 */
public class ShouHuanView extends View {
    private int[] colors = new int[]{Color.RED, Color.GRAY, Color.YELLOW, Color.BLUE, Color.GREEN, Color.DKGRAY,Color.WHITE};
    private Paint mPaint;
    private int centerX;
    private int centerY;

    public ShouHuanView(Context context) {
        this(context, null);
    }

    public ShouHuanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ShouHuanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //获取屏幕中心
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        centerX = displayMetrics.widthPixels/2;
        centerY = displayMetrics.heightPixels/2;
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动中心
        canvas.translate(centerX, centerY);
        float start=2;
        canvas.drawCircle(0,0,200,mPaint);
        canvas.drawCircle(0,0,300,mPaint);
        for (int i = 0; i < 180; i++) {
            canvas.rotate(2);
            canvas.drawLine(200,0,300,0,mPaint);
        }

    }
}
