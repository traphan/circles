package com.example.ren95.circles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by ren95 on 09.10.2016.
 */
public class CanvasView extends View implements iCanvasView {
    private Paint mPaint;
    private GameManager mGameManager;
    private static int height;
    private static int width;
    private Canvas mCanvas;
    private Toast mToast;


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthandHeigt(context);
        initPaint();
        mGameManager=new GameManager(this,width,height);

    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);// сглаживание
        mPaint.setStyle(Paint.Style.FILL);//стиль

    }

    private void initWidthandHeigt(Context context) {
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        width=point.x;
        height=point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas=canvas;
        mGameManager.onDraw();

    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        mPaint.setColor(circle.getColor());
        mCanvas.drawCircle(circle.getX(),circle.getY(),circle.getRadius(),mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int) event.getX();
        int y=(int) event.getY();
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            mGameManager.onTouchEvent(x,y);
        }
        invalidate();
        return true;
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMassage(String text) {
        if(mToast!=null){
            mToast.cancel();
        }
        mToast=Toast.makeText(getContext(),text,Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }
}
