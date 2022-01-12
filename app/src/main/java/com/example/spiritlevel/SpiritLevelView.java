package com.example.spiritlevel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiritLevelView extends View {
    private float width, height;
    private float centerX, centerY;
    private final Paint paint;
    private String Tag = SpiritLevelView.class.getSimpleName();

    public SpiritLevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(Tag, String.format("size changed from %d %d to %d %d",oldw,oldh,w,h));
        width = w;
        height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(width/2,0,width/2,height,paint);
        canvas.drawLine(0,height/2,width,height/2,paint);
        canvas.drawCircle(centerX, centerY, 100, paint);
    }

    public void updatePosition(float screenX, float screenY) {
        float width = getWidth();
        float height = getHeight();

        float middleX = width / 2;
        float middleY = height / 2;
        centerX = middleX + screenX * 100;
        centerY = middleY + screenY * 100;

        if (Math.abs(centerX- middleX)<10) {
            paint.setColor(Color.GREEN);
        }
        else {paint.setColor(Color.BLACK);

        }
    }
}

