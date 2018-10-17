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

        Paint linePaint = new Paint(mPaint);
        linePaint.setStrokeWidth(2);
        linePaint.setColor(Color.GRAY);
        float y = 240;
        int count = 60;
        Paint numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setStrokeWidth(3);
        numberPaint.setTextSize(30);
        numberPaint.setColor(Color.RED);

        for(int i=0; i<count; i++){
            if(i%5 ==0){
                //绘制数字刻度
                canvas.drawText(i==0 ? "12" : String.valueOf(i/5),((i/5)>9 || i==0)? -18f:-9f,-y-9f,numberPaint);
            }else{
                //绘制非数字的刻度
                canvas.drawLine(0f,y,0f,y+30f,linePaint);
            }
            //旋转画布 每次旋转绘制数字，或直线
            canvas.rotate(360/count,0f,0f);
        }

        //绘制秒针
        numberPaint.setStrokeWidth(4);
        canvas.drawCircle(0,0,15,numberPaint);

        numberPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,12,numberPaint);

        canvas.drawLine(0,45,0,-234,numberPaint);
    }

    @Override
    public void run() {

    }
}
