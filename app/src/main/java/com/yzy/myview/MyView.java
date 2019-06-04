package com.yzy.myview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path=new Path();
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        path.moveTo(100,100);
        path.lineTo(200,200);
        path.moveTo(300,300);
        path.lineTo(400,400);
        path.addRect(100, 100, 400, 400, Path.Direction.CW);
        path.addCircle(250,250, (float) (50*Math.sqrt(2)),Path.Direction.CW);

        RectF rect = new RectF(400,400,500,600);
        path.addRoundRect(rect, 20, 20, Path.Direction.CW); //创建一个圆角矩形
        canvas.drawPath(path,mPaint);

    }
}
