package com.mycompany.app.models;

import java.awt.Point;
import java.util.List;

/**
 * Created by rackarmattan on 2017-04-24.
 */
public interface Shape {

    List<Point> rotate(); //TODO
    List<Point> updatePositions(Direction direction);
    void setStartPosition(int width);
    List<Point> getShapeList();
    void setPositions(List<Point> newPos);
    //public Image getImage();
}
