package com.example.lsy.mytestview.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationSet;

import com.example.lsy.mytestview.R;

/**
 * Created by lsy on 16-9-23.
 */

public class MyTestView2 extends View {

    private int ballSize = getResources().getDimensionPixelSize(R.dimen.ball_default_size);
    private int ballColor = Color.BLACK;
    private int viewBackground = Color.RED;
    private Paint myPaint;
    private int ballX;
    private int ballY;
    private int viewWeight;
    private int viewHeight;
    private ValueAnimator valueAnimatorX;
    private ValueAnimator valueAnimatorY;
    private float offsetX;
    private float offsetY;
    private float pointX;
    private float pointY;
    Rect rect = new Rect();

    public MyTestView2(Context context) {
        this(context, null);
    }

    public MyTestView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTestView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyTestView2, defStyleAttr, 0);

        ballSize = a.getDimensionPixelSize(R.styleable.MyTestView2_ballSize, ballSize);
        ballColor = a.getColor(R.styleable.MyTestView2_ballColor, ballColor);
        viewBackground = a.getColor(R.styleable.MyTestView2_viewBackgroud, viewBackground);
//        Log.e("ballx",ballX+"");
//        Log.e("bally",ballY+"");
        myPaint = new Paint();
        myPaint.setColor(ballColor);
        myPaint.setAntiAlias(true);
        myPaint.setStyle(Paint.Style.FILL);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getHeight();
        viewWeight = getWidth();
        ballX = viewWeight / 2;
        ballY = viewHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("ballx", ballX + "");
        Log.e("bally", ballY + "");
        canvas.drawCircle(ballX, ballY, ballSize, myPaint);
        // startBallAnimation();
    }

    public void startBallAnimation() {
        valueAnimatorX = ValueAnimator.ofFloat(0f, 1f);
        valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float percent = (float) animation.getAnimatedValue();

                ballX = (int) (viewWeight / 2 - (viewWeight / 2 - ballSize) * percent);
                invalidate();
            }
        });

        valueAnimatorY = ValueAnimator.ofFloat(0f, 1f);
        valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float percent = (float) animation.getAnimatedValue();
                ballY = (int) (viewHeight / 2 - (viewHeight / 2 - ballSize) * percent);
                invalidate();
            }
        });

        // 重复次数 -1表示无限循环
        valueAnimatorX.setRepeatCount(-1);
        valueAnimatorY.setRepeatCount(-1);

        // 重复模式, RESTART: 重新开始 REVERSE:恢复初始状态再开始
        valueAnimatorX.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimatorY.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet set = new AnimatorSet();
        set.play(valueAnimatorX).with(valueAnimatorY);
        set.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                getHitRect(rect);
                pointX = event.getRawX();
                pointY = event.getRawY();
                super.onTouchEvent(event);
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                if(rect==null){
                    break;
                }
                offsetX=event.getRawX()-pointX;
                offsetY=event.getRawY()-pointY;
                layout((int)(rect.left+offsetX),(int)(rect.top+offsetY),
                        (int)(rect.right+offsetX),(int)(rect.bottom+offsetY));
                return false;
            }

        }
        return super.onTouchEvent(event);
    }
}
