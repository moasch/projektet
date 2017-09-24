package com.mycompany.app.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sibas on 2017-08-21.
 */
public class PigShape {


    private Point pigPosition;
    private static final int pigStartX = 100;        //final?
    private static final int pigStartY = 100;
    //private List<Point> pigShapeList;                                   //***

    public PigShape() {
        this.pigPosition = new Point(pigStartX, pigStartY);
        //this.pigShapeList = new ArrayList<>();                          //***
    }

    public Point getPigPosition() {
        return pigPosition;
    }

    public Point updatePositionPig(Direction direction) {
        Point p = new Point();
        p.x = pigPosition.x;
        p.y = pigPosition.y;
        switch (direction) {
            case LEFT:
                p.x = pigPosition.x -= 1;
                return p;
            case RIGHT:
                p.x = pigPosition.x += 1;
                return p;
            case UP:
                p.y = pigPosition.y -= 1;
                return p;
            case DOWN:
                p.y = pigPosition.y += 1;
                return p;
            default:
                return p;
        }
    }


    public void setPigPosition(Point newPos) {
        pigPosition = newPos;
    }

}