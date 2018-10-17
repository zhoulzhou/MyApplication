package com.example.demo.customview.roudClock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.icu.util.Calendar;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoundClock extends View implements Runnable {
    private Paint mPaint;
    private float mCenterX;

    private float mHourLength;
    private float mMinuteLength;
    private float mSecondLength;

    private Handler handler = new Handler();

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

        handler.postDelayed(this, 2000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

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

        Calendar calendar = Calendar.getInstance();
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentHour = calendar.get(Calendar.HOUR);
        int currentSecond = calendar.get(Calendar.SECOND);
        // 计算分针和时间的角度
        double secondRadian = Math.toRadians((360 - ((currentSecond * 6) - 90)) % 360);
        double minuteRadian = Math.toRadians((360 - ((currentMinute * 6) - 90)) % 360);
        double hourRadian = Math.toRadians((360 - ((currentHour * 30) - 90))% 360 - (30 * currentMinute / 60));

        // 设置实针为6个象素粗
        mPaint.setStrokeWidth(6);
        // 在表盘上画时针
        mCenterX = 0;
        mHourLength = 150;
        canvas.drawLine(mCenterX, mCenterX,
                (int) (mCenterX + mHourLength * Math.cos(hourRadian)),
                (int) (mCenterX - mHourLength * Math.sin(hourRadian)), mPaint);

        // 设置分针为4个象素粗
        mPaint.setStrokeWidth(4);
        mMinuteLength = 210;
        // 在表盘上画分针
        canvas.drawLine(mCenterX, mCenterX,
                (int) (mCenterX + mMinuteLength* Math.cos(minuteRadian)),
                (int) (mCenterX - mMinuteLength* Math.sin(minuteRadian)),
                mPaint);
        // 设置分针为2个象素粗
        mPaint.setStrokeWidth(2);
        // 在表盘上画秒针
        mSecondLength = 145;
        int centerY = 30;
        canvas.drawLine((int) (mCenterX - centerY* Math.cos(secondRadian)),(int) (mCenterX + centerY* Math.sin(secondRadian)),
                (int) (mCenterX + mSecondLength* Math.cos(secondRadian)),
                (int) (mCenterX - mSecondLength* Math.sin(secondRadian)),
                mPaint);
//        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 5, mPaint);
    }

    @Override
    public void run() {
        // 重新绘制View
        this.invalidate();
        // 重新设置定时器，在60秒后调用run方法
        handler.postDelayed(this, 1000);
    }
}
