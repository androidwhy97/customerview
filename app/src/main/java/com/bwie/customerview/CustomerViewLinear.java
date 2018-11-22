package com.bwie.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 文件描述：
 * 作者：王恒钰
 * 创建时间：2018/11/1
 */
public class CustomerViewLinear extends LinearLayout {
    private LinearLayout rootView;
    private ImageView leftIvPic;
    private TextView centerTextTitle;
    private TextView rightTextMay;

    public CustomerViewLinear(Context context) {
        this(context, null);
    }

    public CustomerViewLinear(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomerViewLinear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        initAttrs(context, attrs);
        initListener(context);
    }

    private void initListener(final Context context) {
        leftIvPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               if (customerClick !=null){
                   customerClick.leftClick(v);
               }
            }
        });
        centerTextTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerClick !=null){
                    customerClick.centerClick(v);
                }
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerViewLinear);
        Drawable drawable = typedArray.getDrawable(R.styleable.CustomerViewLinear_left_iv_pic);
        if (drawable != null) {
            leftIvPic.setImageDrawable(drawable);
        }
        CharSequence text = typedArray.getText(R.styleable.CustomerViewLinear_center_text_title);
        if (!TextUtils.isEmpty(text)) {
            centerTextTitle.setText(text);
        }
        int color = typedArray.getColor(R.styleable.CustomerViewLinear_center_text_color, -1);
        if (color != -1) {
            centerTextTitle.setTextColor(color);
        }

        float dimension = typedArray.getDimension(R.styleable.CustomerViewLinear_center_text_size, 0f);
        centerTextTitle.setTextSize(dimension);
    }

    private void initView(Context context) {
        rootView = (LinearLayout) View.inflate(context, R.layout.layout_linearlayout, this);
        leftIvPic = rootView.findViewById(R.id.left_iv_pic);
        centerTextTitle = rootView.findViewById(R.id.center_tv_title);
        rightTextMay = rootView.findViewById(R.id.right_text_may);
    }

    interface CustomerClick {
        void leftClick(View view);
        void centerClick(View view);
    }

    private CustomerClick customerClick;
    public void setLeftClick(CustomerClick customerClick){
        this.customerClick = customerClick;
    }
}
