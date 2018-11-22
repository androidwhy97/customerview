package com.bwie.customerview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/4
 */
public class FloatLayout extends LinearLayout {
    String[] desc = {"我", "我是", "我是1606", "我是1606A", "我是1606A的", "我是1606A的学", "我是1606A的学生"};
    private int widthPixels;

    public FloatLayout(Context context) {
        this(context, null);
    }

    public FloatLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FloatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置垂直
        setOrientation(VERTICAL);
        //获取屏幕宽度
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
    }

    public void innerSetAdapter(String[] desc) {
        LinearLayout linearLayout = initHorLinear();
        int childCount = linearLayout.getChildCount();
        int totalWidth = 0 ;
        for (int i = 0; i < childCount; i++) {
            TextView childAt = (TextView) linearLayout.getChildAt(i);
            childAt.measure(getMeasuredWidth(),getMeasuredHeight());
            int width = childAt.getMeasuredWidth();

            totalWidth+=width;
        }
        TextView textView = initTextView();


    }

    private LinearLayout initHorLinear() {
        LinearLayout linearLayout_H = new LinearLayout(getContext());
        linearLayout_H.setOrientation(HORIZONTAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(linearLayout_H, params);
        return linearLayout_H;
    }

    private TextView initTextView() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#ff0000"));
        return textView;
    }

}
