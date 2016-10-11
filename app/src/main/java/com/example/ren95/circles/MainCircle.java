package com.example.ren95.circles;

import android.graphics.Color;

/**
 * Created by ren95 on 09.10.2016.
 */
public class MainCircle extends SimpleCircle {
    public static final int INIT_RAD = 50;
    public static final int MAIN_SPEED = 30;
    public static final int OUR_COLOR = Color.GREEN;

    public MainCircle(int x, int y) {
        super(x, y, INIT_RAD);
        setColor(OUR_COLOR);
    }


    public void moveMainCircleWhenTouchAt(int x1, int y1) {
        int dx=(x1-x)* MAIN_SPEED /GameManager.getWidth();
        int dy=(y1-y)*MAIN_SPEED/GameManager.getHeight();
        x+=dx;
        y+=dy;
    }

    public void initRadius() {
        radius=INIT_RAD;
    }

    public void growRadius(SimpleCircle circle) {
        //pi*newr^2==pi*r^2+pi*reat^2
        //newr=sqrt(r^2+reat^2)
        radius=(int)Math.sqrt(Math.pow(radius,2)+Math.pow(circle.radius,2));
    }
}
