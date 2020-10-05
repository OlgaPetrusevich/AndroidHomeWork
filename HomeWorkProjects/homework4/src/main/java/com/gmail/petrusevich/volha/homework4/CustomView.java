package com.gmail.petrusevich.volha.homework4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class CustomView extends View {

    interface TouchActionListener {
        void onTouchDown(int x, int y);
    }

    private int[] colors = new int[4];
    private int radiusLarge = 0;
    private int colorCenter = 0;
    private int radiusSmall = 0;
    private Paint paintRightBottom = new Paint();
    private Paint paintBottomLeft = new Paint();
    private Paint paintLeftTop = new Paint();
    private Paint paintTopRight = new Paint();
    private Paint paintCenter = new Paint();
    private int widthCenter = 0;
    private int heightCenter = 0;
    private int leftPoint = 0;
    private int rightPoint = 0;
    private int topPoint = 0;
    private int bottomPoint = 0;
    private Random random = new Random();
    private int largeCircleSquare;
    private int clickCircleSquare;

    private TouchActionListener touchActionListener;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        for (int i = 0; i < colors.length; i++) {
            int defaultColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            colors[i] = typedArray.getColor(R.styleable.CustomView_custom_color, defaultColor);
        }
        colorCenter = typedArray.getColor(R.styleable.CustomView_custom_color_center, 0);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widthCenter = w / 2;
        heightCenter = h / 2;
        radiusLarge = widthCenter;
        radiusSmall = radiusLarge / 3;
        leftPoint = widthCenter - radiusLarge;
        rightPoint = widthCenter + radiusLarge;
        topPoint = heightCenter - radiusLarge;
        bottomPoint = heightCenter + radiusLarge;
        largeCircleSquare = (int) Math.PI * getSquaredNumber(radiusLarge);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paintRightBottom.setColor(colors[0]);
        paintBottomLeft.setColor(colors[1]);
        paintLeftTop.setColor(colors[2]);
        paintTopRight.setColor(colors[3]);
        paintCenter.setColor(colorCenter);
        drawSegment(canvas, 0, paintRightBottom);
        drawSegment(canvas, 90, paintBottomLeft);
        drawSegment(canvas, 180, paintLeftTop);
        drawSegment(canvas, 270, paintTopRight);
        canvas.drawCircle(widthCenter, heightCenter, radiusSmall, paintCenter);
        super.onDraw(canvas);

    }

    private void drawSegment(Canvas canvas, float startAngle, Paint paint) {
        canvas.drawArc(leftPoint, topPoint, rightPoint, bottomPoint, startAngle, 90, true, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchActionListener != null) {
                touchActionListener.onTouchDown((int) event.getX(), (int) event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    public void setTouchActionListener(TouchActionListener touchActionListener) {
        this.touchActionListener = touchActionListener;
    }

    public void getNewColor(int x, int y) {
        int smallCircleSquare = (int) Math.PI * getSquaredNumber(radiusSmall);
        boolean areaClick = isAreaClick(x, y);
        if (areaClick) {
            return;
        } else if (clickCircleSquare <= smallCircleSquare) {
            getRandomColors();
        } else if (x > widthCenter && y > heightCenter) {
            colors[0] = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        } else if (x < widthCenter && y > heightCenter) {
            colors[1] = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        } else if (x < widthCenter && y < heightCenter) {
            colors[2] = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        } else if (x > widthCenter && y < heightCenter) {
            colors[3] = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
        invalidate();
    }

    private void getRandomColors() {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
    }

    private int getSquaredNumber(int number) {
        return number * number;
    }

    public boolean isAreaClick(int x, int y) {
        int radiusClick = (int) Math.sqrt(getSquaredNumber(x - widthCenter) + getSquaredNumber(y - heightCenter));
        clickCircleSquare = (int) Math.PI * getSquaredNumber(radiusClick);
        return clickCircleSquare > largeCircleSquare;
    }

}
