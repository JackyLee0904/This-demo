package com.android.musicapp.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.musicapp.R;

public class WaterWaveProgressView extends View {

    private int maxProgress = 100;
    private int currentProgress = 0;
    private int waveColor = Color.BLUE;
    private int backgroundColor = Color.LTGRAY;
    private float waveAmplitude = 20f; // 波幅
    private float waveFrequency = 0.02f; // 波频
    private float waveOffset = 0f; // 波偏移，用于动画

    private Paint backgroundPaint;
    private Paint wavePaint;
    private Path wavePath;
    public WaterWaveProgressView(Context context) {
        super(context);
    }

    public WaterWaveProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterWaveProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context,AttributeSet attrs){
// 初始化画笔
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.FILL);

        wavePaint = new Paint();
        wavePaint.setAntiAlias(true);
        wavePaint.setStyle(Paint.Style.FILL);

        wavePath = new Path();

        // 获取 XML 属性
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaterWaveProgressView);
            maxProgress = a.getInt(R.styleable.WaterWaveProgressView_maxProgress, 100);
            currentProgress = a.getInt(R.styleable.WaterWaveProgressView_currentProgress, 0);
            waveColor = a.getColor(R.styleable.WaterWaveProgressView_waveColor, Color.BLUE);
            backgroundColor = a.getColor(R.styleable.WaterWaveProgressView_backgroundColor, Color.LTGRAY);
            waveAmplitude = a.getDimension(R.styleable.WaterWaveProgressView_waveAmplitude, 20f);
            waveFrequency = a.getFloat(R.styleable.WaterWaveProgressView_waveFrequency, 0.02f);
            a.recycle();
        }

        // 启动波纹动画
        startWaveAnimation();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 200;
        int desiredHeight = 200;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = (widthMode == MeasureSpec.EXACTLY) ? widthSize : Math.min(desiredWidth, widthSize);
        int height = (heightMode == MeasureSpec.EXACTLY) ? heightSize : Math.min(desiredHeight, heightSize);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.min(centerX, centerY) - 10;

        // 绘制背景圆
        backgroundPaint.setColor(backgroundColor);
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);

        // 绘制波纹
        wavePaint.setColor(waveColor);
        wavePath.reset();
        float waterLevel = centerY - (currentProgress * 2 * radius / maxProgress); // 进度决定水位
        wavePath.moveTo(0, waterLevel);

        for (int x = 0; x <= width; x++) {
            float y = waterLevel + (float) (waveAmplitude * Math.sin(waveFrequency * x + waveOffset));
            wavePath.lineTo(x, y);
        }
        wavePath.lineTo(width, height);
        wavePath.lineTo(0, height);
        wavePath.close();

        canvas.drawPath(wavePath, wavePaint);

        // 绘制文字
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        String text = currentProgress + "%";
        canvas.drawText(text, centerX, centerY + 15, textPaint);
    }
    private void startWaveAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 2 * (float) Math.PI);
        animator.setDuration(2000); // 2秒一循环
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(animation -> {
            waveOffset = (float) animation.getAnimatedValue();
            invalidate(); // 触发重绘
        });
        animator.start();
    }

    // 外部方法，设置进度
    public void setProgress(int progress) {
        currentProgress = Math.max(0, Math.min(progress, maxProgress));
        invalidate();
    }

    // 获取当前进度
    public int getProgress() {
        return currentProgress;
    }
}
