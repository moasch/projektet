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

    private int height;
    private int width;
    public PigShape pigShape;
    private static PauseBoard pauseBoard = null;
    private Direction direction;
    private List<Point> randomPositions;
    private List<Point> actualPositions;
    private boolean firstTime;
    private int countFlowers;

    private PauseBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.pigShape = new PigShape();
        this.actualPositions = new ArrayList<>();
        this.firstTime = true;
        this.countFlowers = 0;
    }

    public void runPauseGame() {
        placePigShape();
        pigShape.updatePositionPig(direction);
        showBonusFlowers();
    }

    private void showBonusFlowers() {
        System.out.println("showBonusFlowers");
        if(firstTime){
            System.out.println("showBonusFlowers > firstTime");
            //addera random element till actualPositions
            findRandomPositions(fillPauseBoardPositions());     //skapar randomPositions 1a gng, sedan inget mer.
            initActualPositions();
            firstTime = false;
        }else{
            //uppdatera actualPositions (ta bort de som grisen kommer åt)
            handleBonusFlowers();
        }
    }

    private void handleBonusFlowers(){
        System.out.println("handeBonusFlowers");
        if(hitBonusFlower()){
            if(countFlowers > 31){
                Board.getInstance().score++;
                countFlowers = 0;
            }
        }
    }

    private boolean hitBonusFlower(){
        System.out.println("hitBonusFlower");
        for(int i = 0; i < actualPositions.size(); i++){
            if((pigShape.getPigPosition().x == actualPositions.get(i).x)||(pigShape.getPigPosition().y == actualPositions.get(i).y)){
                System.out.println("KROCK");
                actualPositions.remove(i);
                i--;
                countFlowers++;
                return true;
            }
        }
        return false;
    }

    private void findRandomPositions(List<Point> boardPositions) {
        System.out.println("findRandomPositions");
        //En metod som fyller en lista med 20 random platser från brädet
        randomPositions = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 101; i++) {
            int k = rand.nextInt(boardPositions.size());
            randomPositions.add(boardPositions.get(k));
        }
    }

    private void initActualPositions(){
        System.out.println("initActualPositions");
        for(int i = 0; i < randomPositions.size(); i++){
            actualPositions.add(randomPositions.get(i));
        }
    }


    public List<Point> getActualPositions(){
        return actualPositions;
    }

    private List<Point> fillPauseBoardPositions() {
        System.out.println("fillPauseBoardPositions");
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
