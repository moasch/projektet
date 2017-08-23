package com.mycompany.app.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sibas on 2017-08-21.
 */
public class PigShape {

    private Point pigPosition;
    private int pigStartX = 100;        //final?
    private int getPigStartY = 100;

    public PigShape(){
        this.pigPosition = new Point(pigStartX,getPigStartY);
    }

    public Point getPigPosition(){
        return pigPosition;
    }

    public Point updatePositionPig(Direction direction){       //TODO skall denna vara private?
        Point p = new Point();
        p.x = pigPosition.x;
        p.y = pigPosition.y;
        switch (direction){
            case LEFT:
                p.x = pigPosition.x -= 1;
                //p.x = pigPosition.x -= 5;
                return p;
            case RIGHT:
                p.x = pigPosition.x += 1;
                //p.x = pigPosition.x += 5;
                return p;
            case UP:
                p.y = pigPosition.y -= 1;
                //p.y = pigPosition.y -= 5;
                return p;
            case DOWN:
                p.y = pigPosition.y += 1;
                //p.y = pigPosition.y += 5;
                return p;
            default:
                return p;
        }
    }


    public void setPigPosition(Point newPos){
        pigPosition = newPos;
    }

}

