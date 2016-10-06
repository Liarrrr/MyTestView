package com.example.lsy.mytestview.util;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by lsy on 16-9-24.
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint=(Point)startValue;
        Point endPoint=(Point)endValue;

        float x=startPoint.x+fraction*(endPoint.x-startPoint.x);
        float y=startPoint.y+fraction*(endPoint.y-startPoint.y);
        return new Point((int)x,(int)y);
    }
}
