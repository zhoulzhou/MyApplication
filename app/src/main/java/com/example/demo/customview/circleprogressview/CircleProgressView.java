package com.example.demo.customview.circleprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.demo.myapplication.R;

public class CircleProgressView extends View {
    private static final String TAG = "CircleProgressView";
    private String centerText;
    private float centerTextSize;
    private int centerTextColor;
    private int circleColor;
    private float circleRadius;
    private Paint textPaint;
    private Paint circlePaint;
    private int progress;

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circleprogressview);
        centerText = typedArray.getString(R.styleable.circleprogressview_centerText);
        centerTextSize = typedArray.getDimension(R.styleable.circleprogressview_centerTextSize, 24f);
        centerTextColor = typedArray.getColor(R.styleable.circleprogressview_centerTextColor, 0xffffff);
        circleColor = typedArray.getColor(R.styleable.circleprogressview_circleColor, 0xff4081);
        circleRadius = typedArray.getDimension(R.styleable.circleprogressview_circleRadius, 260f);
        typedArray.recycle();

//        System.out.println("centerText= " + centerText);
//        System.out.println("centerTextColor= " + centerTextColor);
//        System.out.println("centerTextSize= " + centerTextSize);
    }

    private void initPaint() {
        textPaint = new Paint();
        textPaint.setColor(centerTextColor);
        textPaint.setTextSize(centerTextSize);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);

        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        super.onDraw(canvas);

        canvas.save();

        canvas.drawCircle(width / 2, height / 2, circleRadius, circlePaint);

        float textWidth = textPaint.measureText(centerText);
        float textX = width / 2 - textWidth / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = -(fontMetrics.ascent + fontMetrics.descent) / 2;
        float textY = height / 2 + dy;
        canvas.drawText(centerText, textX, textY, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量规格大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(getMeasuredWidth(), getMeasuredHeight());

        int width = lp.leftMargin + lp.rightMargin;
        int height = lp.bottomMargin + lp.topMargin;
        if (widthMode == MeasureSpec.EXACTLY) {
            width += widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width += (int) Math.min(widthSize, circleRadius * 2);
        } else {
            width += widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height += heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height += (int) Math.min(heightSize, circleRadius * 2);
        } else {
            height += heightSize;
        }

        setMeasuredDimension(width, height);
    }

    public void setText(String text){
        this.centerText = text;
        invalidate();
    }

    public void setProgress(int progress){
        this.progress = progress;
        this.centerText = progress + "%";
        invalidate();
    }

}
