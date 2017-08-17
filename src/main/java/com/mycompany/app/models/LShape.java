package com.mycompany.app.models;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by rackarmattan on 2017-03-31.
 */
public class LShape extends AbstractShape{

    LShape() {
        super(1);
    }

    @Override
    public List<Point> rotate() {
        List<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < 4; i++){
            tmp.add(shapeList.get(i));
        }

        switch(shapeMode) {
            case 1:
                int tempy = tmp.get(1).y;
                int tempx = tmp.get(1).x;
                tmp.set(0, new Point(tempx+1, tempy));
                tmp.set(3, new Point(tempx+2, tempy));
                shapeMode = 2;
                return tmp;

            case 2:
                tempy = tmp.get(1).y;
                tempx = tmp.get(1).x;
                tmp.set(0, new Point(tempx, tempy-1));
                tmp.set(3, new Point(tempx-1, tempy-1));
                shapeMode = 3;
                return tmp;

            case 3:
                tempy = tmp.get(2).y;
                tempx = tmp.get(2).x;
                tmp.set(0, new Point(tempx+2, tempy));
                tmp.set(1, new Point(tempx+2, tempy-1));
                tmp.set(3, new Point(tempx+1, tempy));
                shapeMode = 4;
                return tmp;

            case 4:
                tempy = tmp.get(2).y;
                tempx = tmp.get(2).x;
                tmp.set(0, new Point(tempx, tempy-2));
                tmp.set(1, new Point(tempx, tempy-1));
                shapeMode = 1;
                return tmp;

    }
        return tmp;
    }

    @Override
    public int hashCode(){
        return 2*5;
    }

    @Override
    public void setStartPosition(int width){
        int start = rand.nextInt(width-3) +1;             //LShape är 2 pieces lång; får ej hamna utanför board (ej första raden heller pga rotate)
        for(int i = 0; i < 3; i++) {
            shapeList.add(new Point(start,i-3));
        }
        shapeList.add(new Point(start+1,-1));
    }

    @Override
    public List<Point> getShapeList() {
        return shapeList;
    }
}