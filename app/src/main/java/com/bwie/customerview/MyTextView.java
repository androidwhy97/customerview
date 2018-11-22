package com.bwie.customerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/10/31
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMeasuredResult = getMeasuredResult(widthMeasureSpec);
        int heightMeasuredResult = getMeasuredResult(heightMeasureSpec);

        setMeasuredDimension(widthMeasuredResult, heightMeasuredResult);
    }


    private int getMeasuredResult(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = 500;
        if (mode == AT_MOST) {
            if (size < 10) {
                return result;
            }
            return size;
        }
        return size;
    }

}
