package com.bwie.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/5
 */
public class JieTiView extends ViewGroup {

    private int widthPixels;

    public JieTiView(Context context) {
        this(context, null);
    }

    public JieTiView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public JieTiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取一下屏幕宽度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
        for (int i = 0; i < 20; i++) {
            initTextV();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = 0;
        int measuredHeight = 0;

        int resultWidth = 0;
        int resultHeight = 0;
        int childCount = getChildCount();

        int mode = getMode(widthMeasureSpec|heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                measureChild(childAt, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
                measuredWidth = childAt.getMeasuredWidth();
                measuredHeight = childAt.getMeasuredHeight();

                resultWidth += measuredWidth;
                resultHeight += measuredHeight;
            }
            setMeasuredDimension(resultWidth, resultHeight);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getMode(int measureSpec) {
        return MeasureSpec.getMode(measureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int totalWidth = 0;
        int j = 0;
        for (int i = 0; i < childCount; i++) {
            TextView childAt = (TextView) getChildAt(i);
            childAt.measure(0, 0);
//            measureChild(childAt,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
            int width = childAt.getMeasuredWidth();
            totalWidth += width;
            int height = childAt.getMeasuredHeight();
            if (widthPixels >= totalWidth) {
                if (widthPixels == totalWidth) {
                    j = i;
//                    childAt.layout(width * j, height * i, width * (j + 1), height * (i + 1));
                }
                childAt.layout(width * i, height * i, width * (i + 1), height * (i + 1));

            } else {
                childAt.layout(width * (j - 1), height * i, width * j, height * (i + 1));
                j -= 1;
                if (j == 0) {
                    totalWidth = 0;
                }
            }

        }
    }

    private TextView initTextV() {
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.RED);
        textView.setTextSize(20);
        textView.setText("123456");
        addView(textView);
        return textView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
    }
}
