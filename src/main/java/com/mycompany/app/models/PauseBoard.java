package com.mycompany.app.models;

import javafx.scene.input.KeyCode;

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

    private PauseBoard(int width, int height){  //När skall dessa värden ges?
        this.width = width;
        this.height = height;
        this.pigShape = new PigShape();     //Skapa här?
    }

    public void runPauseGame(){
        //TODO logiken för "gris-spelet" här
        placePigShape();
        pigShape.updatePositionPig(direction);
        /*
        Om man trycker ner en knapp så skall positionen uppdateras
         */
        //pigShape.updatePositionPig(updateDirection());
    }

    private void placePigShape(){
        pigShape.setPigPosition(pigShape.getPigPosition());     //Kommer detta alltd blir samma position?
    }

    public void updateDirection(KeyCode key){  //Gör det ngt att denna heter samma som i Board?
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

    public static PauseBoard getInstance(){
        if(pauseBoard == null){
            pauseBoard = new PauseBoard(15,25);
        }
        return pauseBoard;
    }
}
