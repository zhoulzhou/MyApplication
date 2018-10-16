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
    private int windowWidth, windowHeight;
    private String centerText;
    private float centerTextSize;
    private int centerTextColor;
    private int circleColor;
    private float circleRadius;
    private Paint textPaint;
    private Paint circlePaint;
    private Paint progressPaint;
    private Path path;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    //水波纹的密度(为了美观，尽可能在20-50之间，理论上没有限制)
    private int waveDensity = 20;
    private int progressColor;
    private int currentProgress;
    private int maxProgress = 100;
    //控件本身的宽和高
    private float width, height;

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getWidowAttrs(context);
        initAttrs(context, attrs);
        initPaint();
    }

    private void getWidowAttrs(Context context){
        windowWidth = context.getResources().getDisplayMetrics().widthPixels;
        windowHeight = context.getResources().getDisplayMetrics().heightPixels;
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
        super.onDraw(canvas);

        //计算缓冲区画布的宽高，即直径
        width = circleRadius*2;
        height = width;

        bitmap = Bitmap.createBitmap((int) circleRadius*2, (int) circleRadius*2,Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        //绘制圆
        bitmapCanvas.drawCircle(width/2,height/2, circleRadius,circlePaint);

       //绘制贝塞尔曲线
        drawQuad();

        //绘制文字，这里需要注意文本基线问题
        float textWidth = textPaint.measureText(centerText);
        float textX = width / 2 - textWidth / 2;
        //获取文字上坡度(为负数)和下坡度的高度
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = -(fontMetrics.ascent + fontMetrics.descent) / 2;
        //下移一部分，确保文字局中
        float textY = height / 2 + dy;
        bitmapCanvas.drawText(centerText, textX, textY, textPaint);

        //计算整个画布canvas的宽高，并将缓冲画布绘制在中心处
        float w = getWidth();
        float h = getHeight();
        float cx = w/2 - circleRadius;
        float cy = h/2 - circleRadius;
        canvas.drawBitmap(bitmap,cx,cy,null);
    }

    private void drawQuad(){
        path.reset();
        //计算画笔所在的Y坐标值，直径 - 进度移动距离
        float py = (1- (float) currentProgress/maxProgress) * 2 * circleRadius;
        //向Y轴方向移动画笔（这里为向上）
        path.moveTo(0,py);
        //默认水波纹半径 水平半径
        float xRadius = 2f * circleRadius / waveDensity;
        //水波纹当前半径 垂直半径
        float yRadius = (1 - (float) currentProgress/maxProgress) * xRadius;

        System.out.println("");
        for(int i=0; i<waveDensity; i++){
            //这里是在一条直线上绘制的是上下循环的贝塞尔曲线
            //下曲线，这里可以去掉,但不去掉会更美观
            //绘制贝塞尔曲线，每次绘制相对上一条的位置开始
            path.rQuadTo(xRadius,yRadius,2*xRadius,0);
            //上曲线
            path.rQuadTo(xRadius,-yRadius,2*xRadius,0);
        }
        path.lineTo(width,py);
        path.lineTo(width,height);
        path.lineTo(0,height);
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
            width =windowWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height += heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height += (int) Math.min(heightSize, circleRadius * 2);
        } else {
            height = windowHeight;
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
        this.currentProgress = progress;
        this.centerText = progress + "%";
        invalidate();
    }

}
