package com.nollpointer.graphapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends View {
    public static final String TAG = "GraphView";

    private Paint linePaint;

    private int width;
    private int height;

    private float C = 1f;
    private float A = 1f;

    public GraphView(Context context) {
        super(context);
        initPaint();
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2f);
    }

    public void setC(float c) {
        C = c;
        invalidate();
    }

    public void setA(float a) {
        A = a;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private List<Pair<Float, Float>> getGraphDots(float xMin, float xMax) {
        ArrayList<Pair<Float, Float>> list = new ArrayList<>();

        float x = xMin;

        while (x < xMax) {
            Pair<Float, Float> yDots = getDot(x);
            list.add(new Pair<Float, Float>(x, yDots.first));
            list.add(new Pair<Float, Float>(x, yDots.second));
            x += 0.005f;
        }

        return list;
    }

    private Pair<Float, Float> getDot(float x) {

        Float yPos = (float) Math.sqrt(Math.sqrt(A * A * A * A + 4 * C * C * x * x) - x * x - C * C);
        Float yNeg = -yPos;

        return new Pair<>(yPos, yNeg);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float xMin = -width / 2;
        float xMax = width / 2;

        float xDelta = width / 2;
        float yDelta = height / 2;

        List<Pair<Float, Float>> dots = getGraphDots(-4.5f, 4.5f);

        linePaint.setStyle(Paint.Style.STROKE);

        for (Pair<Float, Float> dot : dots) {
            canvas.drawPoint(xDelta + (dot.first * width/8f), yDelta + (dot.second * height/6), linePaint);
        }

        linePaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(xDelta + C* width/8f,yDelta,1.9f,linePaint);

        canvas.drawCircle(xDelta - C* width/8f,yDelta,1.9f,linePaint);



        //super.onDraw(canvas);
    }
}
