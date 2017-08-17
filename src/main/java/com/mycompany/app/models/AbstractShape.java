package com.mycompany.app.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sibas on 2017-05-04.
 */
public abstract class AbstractShape implements Shape{

    Random rand;
    List<Point> shapeList;
    int shapeMode;

    AbstractShape(int shapeMode){
        this.rand = new Random();
        this.shapeList = new ArrayList<>();
        this.shapeMode = shapeMode;
    }

    @Override
    public List<Point> updatePositions(Direction direction) {
        List<Point> tmp = new ArrayList<Point>();
        switch (direction) {
            //flytta vänster
            case LEFT:
                for (int i = 0; i < 4; i++) {
                    Point p = new Point(shapeList.get(i).x - 1, shapeList.get(i).y);
                    tmp.add(p);
                }
                return tmp;
            //Flytta höger
            case RIGHT:
                for (int i = 0; i < 4; i++) {
                    Point p = new Point(shapeList.get(i).x + 1, shapeList.get(i).y);
                    tmp.add(p);
                }
                return tmp;
            case UP:
                return rotate();

            case DOWN:
                int faster = 2;
                for (int i = 0; i < 4; i++) {
                    Point p = new Point(shapeList.get(i).x, shapeList.get(i).y + faster);
                    tmp.add(p);
                }
                return tmp;
            //default, bara ramla ner. sen måste vi göra en för att snurra med, men det kanske borde vara en egen metod
            default:
                faster =1;
                for (int i = 0; i < 4; i++) {
                    Point p = new Point(shapeList.get(i).x, shapeList.get(i).y + faster);
                    tmp.add(p);
                }
                return tmp;
        }
    }

    @Override
    public void setPositions(List<Point> newPos) {
        for (int i = 0; i < 4; i++){
            shapeList.set(i, newPos.get(i));
        }
    }


}
