package com.example.ren95.circles;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by ren95 on 10.10.2016.
 */

public class EnemyCircle extends SimpleCircle {

    public static final int FROM_RAD = 10;
    public static final int TO_RAD = 110;
    public static final int FOOD_COLOR = Color.YELLOW;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int RANDOM_SPEED = 10;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius,int dx,int dy) {
        super(x, y, radius);
        this.dx=dx;
        this.dy=dy;
    }


    public static EnemyCircle getRandomCircle() {
        Random random=new Random();
        int x=random.nextInt(GameManager.getWidth());
        int y=random.nextInt(GameManager.getHeight());
        int dx=1+random.nextInt(RANDOM_SPEED);
        int dy=1+random.nextInt(RANDOM_SPEED);
        int radius=FROM_RAD+random.nextInt(TO_RAD - FROM_RAD);
        EnemyCircle enemyCircle=new EnemyCircle(x,y,radius,dx,dy);
        return enemyCircle;
    }

    public void setEnemyOfFoodColorDependsOn(MainCircle mainCircle) {
        if(isSmallerThan(mainCircle)){
            setColor(FOOD_COLOR);
        }else {
            setColor(ENEMY_COLOR);
        }
    }

    public boolean isSmallerThan(SimpleCircle circle) {
        if(radius<circle.radius){
            return true;
        }
        return false;
    }

    public void moveOneStep() {
        x+=dx;
        y+=dy;
        checkBounds();
    }

    private void checkBounds() {
        if(x<0||x>GameManager.getWidth()){
            dx=-dx;
        }
        if (y<0||y>GameManager.getHeight()){
            dy=-dy;
        }
    }
}
