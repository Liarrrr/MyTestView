package com.example.lsy.mytestview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.example.lsy.mytestview.R;
import com.example.lsy.mytestview.util.PointEvaluator;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by lsy on 16-9-24.
 */

public class MyTestView3 extends View {
    @BindDimen(R.dimen.ball_default_size)
    float ballSize;

    @BindColor(R.color.colorBallStart)
    int startClolr;

    @BindColor(R.color.colorBallEnd)
    int endColor;

    private Paint paint;
    private float width;
    private float height;
    private float ballX;
    private float ballY;
    private Point ballPoint=new Point();
    private ValueAnimator valueAnimator;
    private ValueAnimator valueAnimator1;

    public MyTestView3(Context context) {
        this(context, null);
    }

    public MyTestView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTestView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ButterKnife.bind(this);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyTestView3, defStyleAttr, 0);
        ballSize = a.getDimension(R.styleable.MyTestView3_ballSizes, ballSize);
        startClolr = a.getColor(R.styleable.MyTestView3_startColor, startClolr);
        endColor = a.getColor(R.styleable.MyTestView3_endColor, endColor);
        ballX = ballSize;
        ballY = ballSize;
        ballPoint.x = (int) ballSize;
        ballPoint.y = (int) ballSize;
        a.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(startClolr);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        height = getHeight();
//        ballX=width/2;
//        ballY=height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(ballPoint.x, ballPoint.y, ballSize, paint);
    }


    public void startAnim() {
        valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), new Point((int) ballX, (int) ballY), new Point((int) (width - ballSize), (int) (height - ballSize)));
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ballPoint=(Point)animation.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.start();

//        valueAnimator1=ValueAnimator.ofArgb(startClolr,endColor);
    }
}
