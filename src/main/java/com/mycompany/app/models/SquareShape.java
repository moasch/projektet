package com.mycompany.app.models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by sibas on 2017-03-31.
 */
public class SquareShape extends AbstractShape {

    public SquareShape() {
        super(1);
    }

    @Override
    public List<Point> rotate() {
        List<Point> tmp = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point p = new Point(shapeList.get(i).x, shapeList.get(i).y + 1);
            tmp.add(p);
        }
        return tmp;
    }

    @Override
    public int hashCode(){
        return 1*7;
    }

    @Override
    public void setStartPosition(int width) {
        int start = rand.nextInt(width-2);
        for (int i = start; i < start+2; i++){
            shapeList.add(new Point(i, -1));
            shapeList.add(new Point(i, 0));
        }
    }

    @Override
    public List<Point> getShapeList() {
        return shapeList;
    }
}