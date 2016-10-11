package com.example.ren95.circles;

import android.graphics.Canvas;

import java.util.ArrayList;


/**
 * Created by ren95 on 09.10.2016.
 */

public class GameManager {
    public static final int MAX_CIRCLES = 10;
    private MainCircle mMainCircle;
    private ArrayList<EnemyCircle> mCircles;
    private CanvasView mCanvasView;
    private static int width;
    private static int height;

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }


    public GameManager(CanvasView canvas, int wid, int heig) {
        mCanvasView=canvas;
        width=wid;
        height=heig;
        initMainCircle();
        initEnemyCircle();

    }

    private void initEnemyCircle() {
        SimpleCircle mainCircleArea=mMainCircle.getCircleArea();
        mCircles=new ArrayList<EnemyCircle>();
        for(int i = 0; i< MAX_CIRCLES; i++){
            EnemyCircle circle;
            do {
                circle=EnemyCircle.getRandomCircle();
            }while (circle.isIntersect(mainCircleArea));

            mCircles.add(circle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle:mCircles) {
            circle.setEnemyOfFoodColorDependsOn(mMainCircle);
        }
    }

    public void onDraw() {
        mCanvasView.drawCircle(mMainCircle);
        for (EnemyCircle circle:mCircles) {
            mCanvasView.drawCircle(circle);
        }
    }


    private void initMainCircle() {
        mMainCircle=new MainCircle(width/2,height/2);
    }

    public void onTouchEvent(int x, int y) {
        mMainCircle.moveMainCircleWhenTouchAt(x,y);
        checkColusion();
        moveCircles();
    }

    private void checkColusion() {
        SimpleCircle circleForDel=null;
        for (EnemyCircle circle:mCircles) {
            if(mMainCircle.isIntersect(circle)){
                if(circle.isSmallerThan(mMainCircle)){
                    mMainCircle.growRadius(circle);
                    circleForDel=circle;
                    calculateAndSetCirclesColor();
                    break;
                }else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }
        }
        if(circleForDel!=null){
            mCircles.remove(circleForDel);
        }
        if(mCircles.isEmpty()){
            gameEnd("YOU WIN!");
        }

    }

    private void gameEnd(String text) {
        mCanvasView.showMassage(text);
        mMainCircle.initRadius();
        initEnemyCircle();
        mCanvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle:mCircles) {
            circle.moveOneStep();
        }
    }

}
