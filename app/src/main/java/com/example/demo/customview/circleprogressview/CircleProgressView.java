package com.example.demo.customview.circleprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
    private Paint progressPaint;
    private Path path;
    private int progressColor;
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
        progressColor = typedArray.getColor(R.styleable.circleprogressview_progressColor,0x3F51B5);
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

        progressPaint = new Paint();
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        //取两层绘制交集。显示上层
        progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        super.onDraw(canvas);

        canvas.save();

        canvas.drawCircle(width / 2, height / 2, circleRadius, circlePaint);

        //将text画在圆心位置
        float textWidth = textPaint.measureText(centerText);
        float textX = width / 2 - textWidth / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = -(fontMetrics.ascent + fontMetrics.descent) / 2;
        float textY = height / 2 + dy;
        canvas.drawText(centerText, textX, textY, textPaint);

        Bitmap bitmap = Bitmap.createBitmap((int) circleRadius*2, (int) circleRadius*2,Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawCircle(width/2,height/2, circleRadius,circlePaint);

        float waveY = (float) (1-progress*0.01) *circleRadius*2;

        path.moveTo(width-circleRadius*2,0);
        path.lineTo(0,height);
        path.lineTo(width/2,height);

//        path.moveTo(width,waveY);
//        path.lineTo(width,height);
//        path.lineTo(0,height);
//        path.lineTo(0,waveY);

//        float space = (circleRadius * 2)/ 6;
//        float space = 30;
//        float d = (float) (1-progress*0.01) *space;
//
//        System.out.println("circleRadius= " + circleRadius);
//        System.out.println("progress= " + progress);
        System.out.println("waveY= " + waveY);
        System.out.println("width= " + width);
        System.out.println("height= " + height);
//        System.out.println("space= " + space);
//        System.out.println("d= " + d);
//
//        for(int i=0; i<circleRadius/(space*4); i++){
//            path.rQuadTo(space, -d,space*2, 0);
//            path.rQuadTo(space,d,space*2,0);
//        }

        path.close();
        bitmapCanvas.drawPath(path,progressPaint);
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

    /**
     * 这里重绘控件使用的是postInvalidate();而我们以前也有用Invalidate()函数的。
     * 这两个函数的作用都是用来重绘控件的，但区别是Invalidate()一定要在UI线程执行，
     * 如果不是在UI线程就会报错。而postInvalidate()则没有那么多讲究，它可以在任何线程中执行，
     * 而不必一定要是主线程。其实在postInvalidate()就是利用handler给主线程发送刷新界面的消息来实现的，
     * 所以它是可以在任何线程中执行，而不会出错。而正是因为它是通过发消息来实现的，
     * 所以它的界面刷新可能没有直接调Invalidate()刷的那么快。

     所以在我们确定当前线程是主线程的情况下，还是以invalide()函数为主。
     当我们不确定当前要刷新页面的位置所处的线程是不是主线程的时候，还是用postInvalidate为好;
     *
     */
    public void setProgress(int progress){
        this.progress = progress;
        this.centerText = progress + "%";
        invalidate();
    }

}
