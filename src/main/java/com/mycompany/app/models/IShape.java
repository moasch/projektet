package com.mycompany.app.models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by rackarmattan on 2017-03-31.
 */
public class IShape extends AbstractShape {

    IShape() {
        super(1);
    }

    @Override
    public List<Point> rotate() {
        List<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            tmp.add(shapeList.get(i));
        }

        switch (shapeMode) {
            case 1:
                int tempy = tmp.get(3).y;
                int tempx = tmp.get(3).x;
                tmp.set(0, new Point(tempx + 1, tempy));
                tmp.set(1, new Point(tempx + 2, tempy));
                tmp.set(2, new Point(tempx + 3, tempy));
                shapeMode = 2;
                return tmp;

            case 2:
                tempy = tmp.get(3).y;
                tempx = tmp.get(3).x;
                tmp.set(0, new Point(tempx, tempy - 1));
                tmp.set(1, new Point(tempx, tempy - 2));
                tmp.set(2, new Point(tempx, tempy - 3));
                shapeMode = 1;
                return tmp;
        }
        return tmp;
    }

    @Override
    public int hashCode() {
        return 3 * 3;
    }

    @Override
    public void setStartPosition(int width) {
        int col = rand.nextInt(width);
        for (int i = 0; i < 4; i++) {
            //TEST
            shapeList.add(new Point(col, i - 3));
        }
    }

    @Override
    public List<Point> getShapeList() {
        return shapeList;
    }
}