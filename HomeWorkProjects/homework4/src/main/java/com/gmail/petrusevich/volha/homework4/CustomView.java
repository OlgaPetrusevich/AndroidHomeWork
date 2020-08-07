package com.gmail.petrusevich.volha.homework4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private int color1 = 0;
    private int color2 = 0;
    private int color3 = 0;
    private int color4 = 0;
    private int radius = 0;
    private int color_center = 0;
    private int radius_center = 0;
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

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        color1 = typedArray.getColor(R.styleable.CustomView_custom_color1, 0);
        color2 = typedArray.getColor(R.styleable.CustomView_custom_color2, 0);
        color3 = typedArray.getColor(R.styleable.CustomView_custom_color3, 0);
        color4 = typedArray.getColor(R.styleable.CustomView_custom_color4, 0);
        color_center = typedArray.getColor(R.styleable.CustomView_custom_color_center, 0);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widthCenter = w / 2;
        heightCenter = h / 2;
        radius = w / 2;
        radius_center = radius / 3;
        leftPoint = widthCenter - radius;
        rightPoint = widthCenter + radius;
        topPoint = heightCenter - radius;
        bottomPoint = heightCenter + radius;

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
        paintRightBottom.setColor(color1);
        paintBottomLeft.setColor(color2);
        paintLeftTop.setColor(color3);
        paintTopRight.setColor(color4);
        paintCenter.setColor(color_center);
        canvas.drawArc(leftPoint, topPoint, rightPoint, bottomPoint, 0, 90, true, paintRightBottom);
        canvas.drawArc(leftPoint, topPoint, rightPoint, bottomPoint, 90, 90, true, paintBottomLeft);
        canvas.drawArc(leftPoint, topPoint, rightPoint, bottomPoint, 180, 90, true, paintLeftTop);
        canvas.drawArc(leftPoint, topPoint, rightPoint, bottomPoint, 270, 90, true, paintTopRight);
        canvas.drawCircle(widthCenter, heightCenter, radius_center, paintCenter);
        super.onDraw(canvas);
    }
}
