package com.bwie.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.RotateAnimation;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/2
 */
public class LuckyCustomer extends View implements View.OnClickListener {
    private Paint mPaint;
    private int centerX;
    private int centerY;
    private int[] colors = new int[]{Color.RED, Color.GRAY, Color.YELLOW, Color.BLUE, Color.GREEN, Color.DKGRAY};
    private String[] desc = new String[]{"性感", "丰满", "知性", "聪明", "贤惠", "优秀"};
    private boolean isRote;

    public LuckyCustomer(Context context) {
        this(context, null);
    }

    public LuckyCustomer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LuckyCustomer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
        this.setOnClickListener(this);
    }

    private void init(Context context) {
        //获取屏幕中心
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        centerX = displayMetrics.widthPixels / 2;
        centerY = displayMetrics.heightPixels / 2;
        //初始化画笔
        mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //平移圆心
        canvas.translate(centerX, centerY);
        //画6个弧
        RectF rectF = new RectF(-300, -300, 300, 300);
        float start = 60;
        for (int i = 0; i < colors.length; i++) {
            mPaint.setColor(colors[i]);
            canvas.drawArc(rectF, start * i, 60, true, mPaint);
        }
        //画小圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0, 0, 100, mPaint);

        //小圆上写字
        mPaint.setColor(Color.WHITE);
        Rect rectText = new Rect();
        mPaint.getTextBounds("start", 0, 5, rectText);
        int width = rectText.width();
        int height = rectText.height();
        canvas.drawText("start", -width / 2, height / 2, mPaint);

        //添加描述语言
        //Direction.CW 是顺时针  Direction.CCW  是逆时针
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        for (int i = 0; i < colors.length; i++) {
            Path path = new Path();
            path.addArc(rectF1, start * i + 20, 60);
            canvas.drawTextOnPath(desc[i], path, 0, 0, mPaint);
        }
    }

    private void startAnim() {
        isRote = true;
        final double random = Math.random();
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360*3, centerX, centerY);
        rotateAnimation.setDuration(800);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new OvershootInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RotateAnimation rotateAnimation = new RotateAnimation(0, (float) (720*random), centerX, centerY);
                rotateAnimation.setDuration(1);
                rotateAnimation.setFillAfter(true);
                startAnimation(rotateAnimation);
                isRote = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(rotateAnimation);
    }

    @Override
    public void onClick(View v) {
        if (!isRote) {
            startAnim();
        }
    }
}
