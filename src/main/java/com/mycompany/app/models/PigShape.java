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

    public PigShape(){
        this.pigPosition = new Point(pigStartX,pigStartY);
        //this.pigShapeList = new ArrayList<>();                          //***
    }
/*
    public List<Point> getPigShapeList(){                               //***
        return pigShapeList;
    }
*/
    public Point getPigPosition(){
        return pigPosition;
    }
/*
    public List<Point> updatePigPositions(Direction direction){         //***
        List<Point> tmp = new ArrayList<>();
        switch (direction){
            case LEFT:
                for (int i = 0; i < 4; i++){
                    Point p = new Point(pigShapeList.get(i).x - 1, pigShapeList.get(i).y);
                    tmp.add(p);
                }
                return tmp;
            case RIGHT:
                for(int i = 0; i < 4; i++){
                    Point p = new Point(pigShapeList.get(i).x + 1, pigShapeList.get(i).y);
                    tmp.add(p);
                }
                return tmp;
            case UP:
                for (int i = 0; i < 4; i++){
                    Point p = new Point(pigShapeList.get(i).x, pigShapeList.get(i).y - 1);
                    tmp.add(p);
                }
                return tmp;
            case DOWN:
                for(int i = 0; i < 4; i++){
                    Point p = new Point(pigShapeList.get(i).x, pigShapeList.get(i).y + 1);
                    tmp.add(p);
                }
                return tmp;
            default:
                return tmp;                             //TODO funkar detta?
        }
    }
*/
    public Point updatePositionPig(Direction direction){
        Point p = new Point();
        p.x = pigPosition.x;
        p.y = pigPosition.y;
        switch (direction){
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


    public void setPigPosition(Point newPos){
        pigPosition = newPos;
    }
/*
    public void setPigPositions(){
        for (int i = pigStartX; i < pigStartX+2; i++){
            pigShapeList.add(new Point(i, pigStartY));
            pigShapeList.add(new Point(i, pigStartY+1));
        }
    }
*/

}

