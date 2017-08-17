package com.mycompany.app.models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by sibas on 2017-03-31.
 */
public class TShape extends AbstractShape {

    TShape() {
        super(1);
    }

    @Override
    public int hashCode() {
        return 4 * 11;
    }

    @Override
    public void setStartPosition(int width) {
        int start = rand.nextInt(width - 3);                 //TShape är 3 pieces lång; får ej hamna utanför board
        for (int i = start; i < start + 3; i++) {
            shapeList.add(new Point(i, -1));
        }
        shapeList.add(new Point(start + 1, 0));
    }

    @Override
    public List<Point> getShapeList() {
        return shapeList;
    }

    @Override
    public List<Point> rotate(){
        List<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < 4; i++){
            tmp.add(shapeList.get(i));
        }

        switch(shapeMode) {
            case 1:
                int tempy = tmp.get(1).y;
                int tempx = tmp.get(1).x;
                tmp.set(0, new Point(tempx, tempy - 1));
                shapeMode = 2;
                return tmp;

            case 2:
                tempy = tmp.get(1).y;
                tempx = tmp.get(1).x;
                tmp.set(3, new Point(tempx-1, tempy ));
                shapeMode = 3;
                return tmp;

            case 3:
                tempy = tmp.get(1).y;
                tempx = tmp.get(1).x;
                tmp.set(2, new Point(tempx, tempy+1 ));
                shapeMode = 4;
                return tmp;

            case 4:
                tempy = tmp.get(1).y;
                tempx = tmp.get(1).x;
                tmp.set(0, new Point(tempx-1, tempy ));
                tmp.set(2, new Point(tempx+1, tempy ));
                tmp.set(3, new Point(tempx, tempy+1 ));
                shapeMode = 1;
                return tmp;
        }
        return tmp;
    }
}