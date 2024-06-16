package com.bharat.mall;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    private Paint wavePaint;
    private int waveColor = 0xFF0000FF; // Default wave color
    private int waveHeight = 50; // Default wave height
    private int waveLength = 200; // Default wave length
    private int waveSpeed = 5; // Default wave speed
    private int offsetX = 0;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        wavePaint = new Paint();
        wavePaint.setColor(waveColor);
        wavePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Clear canvas
        canvas.drawColor(0xFFFFFFFF);

        // Draw wave
        for (int i = 0; i < width; i += waveLength) {
            canvas.drawLine(i, height, i + waveLength / 2, height - waveHeight, wavePaint);
            canvas.drawLine(i + waveLength / 2, height - waveHeight, i + waveLength, height, wavePaint);
        }

        // Move wave horizontally
        offsetX += waveSpeed;
        if (offsetX >= waveLength) {
            offsetX -= waveLength;
        }

        // Invalidate view to trigger redraw
        invalidate();
    }

    // Setter methods for wave properties
    public void setWaveColor(int color) {
        waveColor = color;
        wavePaint.setColor(waveColor);
    }

    public void setWaveHeight(int height) {
        waveHeight = height;
    }

    public void setWaveLength(int length) {
        waveLength = length;
    }

    public void setWaveSpeed(int speed) {
        waveSpeed = speed;
    }
}

