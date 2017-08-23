package com.mycompany.app.models;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by sibas on 2017-08-22.
 */
public class PauseBoard {

    private int height;     //Behövs detta? När?
    private int width;
    public PigShape pigShape;
    private static PauseBoard pauseBoard = null;
    //public int score //TODO använda samma som i Board?
    private Direction direction;
    private List<Point> randomPositions;
    private boolean hasNoRandomList;

    private PauseBoard(int width, int height) {  //När skall dessa värden ges?
        this.width = width;
        this.height = height;
        this.pigShape = new PigShape();     //Skapa här?
        this.hasNoRandomList = true;
    }

    public void runPauseGame() {
        //TODO logiken för "gris-spelet" här
        placePigShape();
        /*
        if(showFlowers){
            placeBonusFlowers();
            showFlowers = false;                  //Innebär detta att vi kan "dölja" blommor när de äts upp?
        }
        */
        placeBonusFlowers();
        pigShape.updatePositionPig(direction);
        //System.out.println("Hello");
    }

    private void placeBonusFlowers() {
        findRandomPositions(pauseBoardPositions());
    }

    private List<Point> pauseBoardPositions() {
    //En metod som skapar en lista över brädets alla positioner, förutom den där grisen startar
        List<Point> pauseBoardPositions = new ArrayList<>();
        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                Point p = new Point(i, j);
                if ((p.x != 100) && (p.y != 100)) {       //vill ej placera blomma på grisens startplats
                    pauseBoardPositions.add(p);
                }
            }
        }
        return pauseBoardPositions;
    }

    private void findRandomPositions(List<Point> boardPositions) {
    //En metod som fyller en lista med 5 random platser från brädet
        Random rand = new Random();
        if(hasNoRandomList){
            randomPositions = new ArrayList<>();
            for (int i = 0; i < 21; i++) {
                int k = rand.nextInt(boardPositions.size());
                randomPositions.add(boardPositions.get(k));
            }
            hasNoRandomList = false;
        }
    }

    public List<Point> getRandomPositions(){
        return randomPositions;
    }

    private void placePigShape() {
        pigShape.setPigPosition(pigShape.getPigPosition());     //Kommer detta alltd blir samma position?
    }

    public void updateDirection(KeyCode key) {  //Gör det ngt att denna heter samma som i Board?
        switch (key) {
            case LEFT:
                this.direction = Direction.LEFT;
                break;
            case RIGHT:
                this.direction = Direction.RIGHT;
                break;
            case UP:
                this.direction = Direction.UP;
                break;
            case DOWN:
                this.direction = Direction.DOWN;
                break;
            default:
                this.direction = Direction.NONE;
        }
    }

    public void setDirectionNone() {
        direction = Direction.NONE;
    }

    public static PauseBoard getInstance() {
        if (pauseBoard == null) {
            pauseBoard = new PauseBoard(285, 450);
        }
        return pauseBoard;
    }
}
