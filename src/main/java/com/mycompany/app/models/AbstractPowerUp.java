package com.mycompany.app.models;

import java.awt.*;
import java.util.Random;

/**
 * Created by rackarmattan on 2017-05-10.
 */
public abstract class AbstractPowerUp implements ScorePowerUp {

    private Point position;
    private Random random;

    AbstractPowerUp(){
        this.position = new Point();
        this.random = new Random();
    }

    public Point getPosition(){
        return position;
    }

    public Point startPosition(int width, int height) {
        int x = random.nextInt(width-2);
        int y = random.nextInt(height-2);
        return new Point(x,y);
    }

    void setPosition(Point p){
        this.position = p;
    }

    @Override
    public int increase(){
        return 1;
    }
    @Override
    public int decrease(){
        return -1;
    }
}
