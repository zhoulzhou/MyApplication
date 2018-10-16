package com.example.demo.customview.roudClock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoundClock extends View implements Runnable {
    private Paint mPaint;

    public RoundClock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0,0,240,mPaint);
        canvas.drawCircle(0,0,300,mPaint);

        canvas.save();

        //这个没什么必要 参考坐标点
        canvas.translate(0,0);

        Path path = new Path();
        RectF rectf = new RectF(-100,-100,100,100);
        path.addArc(rectf,200,360);

        Paint textPaint = new Paint(mPaint);
        textPaint.setTextSize(28);
        textPaint.setStrokeWidth(3);
        canvas.drawTextOnPath("Rolex brand clock", path, 14, 0, textPaint);

        canvas.restore();
    }

    @Override
    public void run() {

    }
}
