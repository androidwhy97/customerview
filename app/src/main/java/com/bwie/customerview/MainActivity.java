package com.bwie.customerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;
    private WaveView mWaveView;
    private FrameLayout.LayoutParams flp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        mImage = findViewById(R.id.image);
        mWaveView = findViewById(R.id.waveView);

        flp = new FrameLayout.LayoutParams(-2, -2);
        flp.gravity = Gravity.CENTER;

        mWaveView.setAnimations(new WaveView.Animations() {
            @Override
            public void getY(float y) {
                flp.setMargins(0, -20, 0, (int) (y + 2));
                mImage.setLayoutParams(flp);
            }
        });
    }
}
