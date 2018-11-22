package com.bwie.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/22
 */
public class WaveView extends View {
    /**
     * y=Asin(ωx+φ)+k
     * A—振幅越大，波形在y轴上最大与最小值的差值越大
     * ω—角速度， 控制正弦周期(单位角度内震动的次数)
     * φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
     * k—偏距，反映在坐标系上则为图像的上移或下移。
     */
    private Paint paint;
    private Path path;
    float A = 8f;
    float w;
    float k = 0f;
    float f;
    float y;
    private Paint paint1;
    private Path path1;
    private float y1;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        //创建一个画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);
        path = new Path();

        //创建一个画笔
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.WHITE);
        paint1.setAntiAlias(true);
        paint1.setAlpha(80);
        path1 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        path1.reset();

        path.moveTo(getLeft(), getBottom());
        path1.moveTo(getLeft(), getBottom());

        w = (float) (2 * Math.PI / getWidth());
        f -= 0.1f;

        for (int x = 0; x <= getWidth(); x += 20) {
            y = (float) (A * Math.sin(w * x + f));
            y1 = (float) (A * Math.cos(w * x + f) + 8);

            path.lineTo(x, y);
            path1.lineTo(x, y1);

            animations.getY(y);
        }

        path.lineTo(getRight(),getBottom());
        path1.lineTo(getRight(),getBottom());

        canvas.drawPath(path,paint);
        canvas.drawPath(path1,paint1);

        postInvalidateDelayed(20);
    }

    public interface Animations {
        void getY(float y);
    }

    private Animations animations;

    public void setAnimations(Animations animations) {
        this.animations = animations;
    }
}
