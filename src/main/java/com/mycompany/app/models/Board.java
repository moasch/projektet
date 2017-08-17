package com.mycompany.app.models;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rackarmattan on 2017-03-31.
 * Board's logic. Board existing of pieces
 */
public class Board {
    private int height;
    private int width;
    private Shape shape;
    private Random r = new Random();
    private List<Point> fixedPositions;
    private boolean hasFalling = false;
    private List<Shape> allFixedPieces;
    private int score;
    private static Board board = null;
    public boolean blow;
    private AbstractPowerUp powerUp;
    public boolean hasPowerUp;
    public int level;
    public boolean run;
    public boolean lockKeys;
    public boolean hasHitPowerUpAngel;
    public boolean hasHitPowerUpScore;
    private static int fiftenn;
    public static int levelUpScore;

    private int count;

    private Direction direction;

    private Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.fixedPositions = new ArrayList<>();
        this.allFixedPieces = new ArrayList<>();
        this.score = 0;
        this.direction = Direction.NONE;
        this.blow = false;
        this.hasPowerUp = false;
        this.powerUp = new ScoreUpPowerUp();
        this.hasHitPowerUpScore = false;
        this.count = 0;
        this.level=0;
        this.run = true;
        this.lockKeys = false;
        this.fiftenn= 50;
        this.levelUpScore=10;
    }

    public void runGame() {
        count++;
        if (count % fiftenn == 0) {
            placePowerUp();
        }
        if (hasFalling) {
            checkLevel();
            List<Point> tmp = shape.updatePositions(direction);
            if (canMove(tmp)) {
                shape.setPositions(tmp);
                handlePowerUp();
                if (!canFallDown(tmp)) {
                    for (int i = 0; i < 4; i++) {
                        Point p = shape.getShapeList().get(i);
                        fixedPositions.add(p);
                    }
                    //lägger in biten som inte kan falla längre i listan med fasta bitar
                    allFixedPieces.add(shape);
                    //ser om nånting behöver tas bort och tar isåfall bort det
                    remove();
                    checkFullBoard();
                    hasFalling = false;
                }
            }
        } else {
            randomShape();
            shape.setStartPosition(width);
            hasFalling = true;
        }
    }

    private void checkLevel(){
       if(score/levelUpScore>level) {
           level++;
       }
    }

    public boolean checkFullBoard() {
        for (int i = 0; i < fixedPositions.size(); i++) {
            if (fixedPositions.get(i).y <= 0) {
                return true;
            }
        }
        return false;
    }

    private void placePowerUp() {

        if (!hasPowerUp) {
            int random = r.nextInt(3);
            if (random == 2) {
                initPowerUp();
                hasPowerUp = true;
            }
        } else {
            powerUp.setPosition(new Point());
            hasPowerUp = false;
        }

    }


    private void handlePowerUp() {

        if (hasPowerUp && hitPowerUp()) {
            powerUp.setPosition(new Point());
            if (powerUp instanceof ScoreDownPowerUp) {
                score = score + powerUp.decrease();

            }
            else if (powerUp instanceof ScoreUpPowerUp) {
                score = score + powerUp.increase();
            }
            else if(powerUp instanceof LockKeysPowerUp){
                lockKeys = true;
            }
            else {
                blowColumn();
            }
            hasPowerUp = false;
        }

    }

    private boolean hitPowerUp() {
        for (int i = 0; i < shape.getShapeList().size(); i++) {
            if (shape.getShapeList().get(i).equals(powerUp.getPosition())) {
                hasHitPowerUpScore = true;
                hasHitPowerUpAngel= true;

                return true;
            }
        }
        return false;
    }

    private void initPowerUp() {
        randomPowerUp(level);
        powerUp.setPosition(pUStartPosition(findEmptyPositions()));
    }


    private boolean canFallDown(List<Point> actual) {
        List<Point> wanted = new ArrayList<>();
        for (int i = 0; i < actual.size(); i++) {
            Point p = new Point(actual.get(i).x, actual.get(i).y + 1);
            if (p.y >= height) {
                return false;
            } else {
                wanted.add(p);
            }
        }
        for (int j = 0; j < wanted.size(); j++) {
            if (fixedPositions.contains(wanted.get(j))) {
                return false;
            }
        }
        return true;
    }

    private boolean canMove(List<Point> tmp) {
        int[] xs = new int[tmp.size()];
        int[] ys = new int[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            xs[i] = tmp.get(i).x;
            ys[i] = tmp.get(i).y;
        }
        xs = returnSortedHighestLast(xs).clone();
        int yMax = returnSortedHighestLast(ys)[ys.length - 1];
        if (xs[0] < 0 || xs[xs.length - 1] >= width || yMax >= height) {
            return false;
        } else {
            for (int i = 0; i < tmp.size(); i++) {
                Point p = tmp.get(i);
                for (int j = 0; j < fixedPositions.size(); j++) {
                    Point p2 = fixedPositions.get(j);
                    if (p.equals(p2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //En metod som slumpar fram en shape
    private void randomShape() {
        int random = r.nextInt(4);
        switch (random) {
            case 0:
                shape = new LShape();
                break;
            case 1:
                shape = new TShape();
                break;
            case 2:
                shape = new IShape();
                break;
            case 3:
                shape = new SquareShape();
                break;
        }
    }

    private void remove() {
        int y = 0;
        List<Integer> checkedRows = new ArrayList<>();
        int[] tmp = new int[shape.getShapeList().size()];
        for (int i = 0; i < shape.getShapeList().size(); i++) {
            tmp[i] = shape.getShapeList().get(i).y;
        }
        int[] ys = returnSortedHighestLast(tmp).clone();
        for (int i = ys.length - 1; i > 0; i--) {
            if (checkRow(ys[i])) {
                blowPointRow(ys[i]);
                blowPieceRow(ys[i]);
                checkedRows.add(ys[i]);
            }
        }
        for (int i = 0; i < checkedRows.size(); i++) {
            //Ta alla rader ovanför i + 1
            movePointsDown(checkedRows.get(i));
            movePieceDown(checkedRows.get(i));
            for (int j = i; j < checkedRows.size(); j++) {
                int higher = checkedRows.get(j) + 1;
                checkedRows.set(j, higher);
            }
        }
    }

    private int[] returnSortedHighestLast(int[] ys) {
        int n = ys.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (ys[j - 1] > ys[j]) {
                    temp = ys[j - 1];
                    ys[j - 1] = ys[j];
                    ys[j] = temp;
                }
            }
        }
        return ys;
    }

    private boolean checkRow(int y) {
        int count = 0;
        for (int i = 0; i < fixedPositions.size(); i++) {
            if (fixedPositions.get(i).y == y) {
                count++;
            }
        }
        if (count == width) {
            score++;
            blow = true;
            return true;
        }
        return false;
    }

    private void blowPointRow(int row) {
        for (int i = 0; i < fixedPositions.size(); i++) {
            Point p = new Point(fixedPositions.get(i));
            if (p.y == row) {
                fixedPositions.remove(i);
                i--;
            }
        }
    }

    private void blowPieceRow(int row) {
        for (int i = 0; i < allFixedPieces.size(); i++) {
            int j = 0;
            while (j < allFixedPieces.get(i).getShapeList().size()) {
                if (allFixedPieces.get(i).getShapeList().size() == 0) {
                    allFixedPieces.remove(i);
                    i--;
                }
                if (allFixedPieces.get(i).getShapeList().get(j).y == row) {
                    allFixedPieces.get(i).getShapeList().remove(j);
                } else {
                    j++;
                }
            }
        }
    }

    private void blowColumn(){


        Random random = new Random();
        int xFun = random.nextInt(width-2);

        int xLeft;
        int xRight;

        if(xFun == 0){
            xLeft = xFun+ 1;
            xRight = xFun+ 2;
        }if(xFun == width){
            xLeft = xFun - 2;
            xRight = xFun - 1;
        }else{
            xLeft = xFun - 1;
            xRight = xFun+ 1;
        }

        if(score < 10){
            blowPointColumn(xFun);
            blowPieceColumn(xFun);
        }else{
            blowPointColumns(xFun, xLeft, xRight);
            blowPieceColumns(xFun, xLeft, xRight);
        }
        
    }

    private void blowPointColumn(int randX){

        for(int i = 0; i < fixedPositions.size(); i++){
            Point p = new Point(fixedPositions.get(i));         //Vill egentligen bara komma åt x-värdet från punkten!
            if(p.x == randX){
                fixedPositions.remove(i);
                i--;
            }
        }
    }

    private void blowPointColumns(int randX, int randLeft, int randRight){

        for(int i = 0; i < fixedPositions.size(); i++){
            Point p = new Point(fixedPositions.get(i));
            if(p.x == randX || p.x == randLeft || p.x == randRight){
                fixedPositions.remove(i);
                i--;
            }
        }
    }

    private void blowPieceColumn(int randX){

        for(int i = 0; i < allFixedPieces.size(); i++) {
            int j = 0;
            while (j < allFixedPieces.get(i).getShapeList().size()) {
                if (allFixedPieces.get(i).getShapeList().size() == 0) {
                    allFixedPieces.remove(i);
                    i--;
                }
                if (allFixedPieces.get(i).getShapeList().get(j).x == randX) {
                    allFixedPieces.get(i).getShapeList().remove(j);
                } else {
                    j++;
                }
            }
        }
    }

    private void blowPieceColumns(int randX, int randLeft, int randRight){

        for(int i = 0; i < allFixedPieces.size(); i++) {
            int j = 0;
            while (j < allFixedPieces.get(i).getShapeList().size()) {
                if (allFixedPieces.get(i).getShapeList().size() == 0) {
                    allFixedPieces.remove(i);
                    i--;
                }
                if (allFixedPieces.get(i).getShapeList().get(j).x == randX ||
                    allFixedPieces.get(i).getShapeList().get(j).x == randLeft ||
                    allFixedPieces.get(i).getShapeList().get(j).x == randRight) {
                    allFixedPieces.get(i).getShapeList().remove(j);
                }
                else {
                    j++;
                }
            }
        }



    }


    private void movePointsDown(int row) {
        for (int i = 0; i < fixedPositions.size(); i++) {
            if (fixedPositions.get(i).y < row) {
                fixedPositions.set(i, new Point(fixedPositions.get(i).x, fixedPositions.get(i).y + 1));
            }
        }
    }

    private void movePieceDown(int row) {
        for (int i = 0; i < allFixedPieces.size(); i++) {
            for (int j = 0; j < allFixedPieces.get(i).getShapeList().size(); j++) {
                if (allFixedPieces.get(i).getShapeList().get(j).y < row) {
                    Point p = new Point(allFixedPieces.get(i).getShapeList().get(j).x, allFixedPieces.get(i).getShapeList().get(j).y + 1);
                    allFixedPieces.get(i).getShapeList().set(j, p);
                }
            }
        }
    }

    public void updateDirection(KeyCode key) {
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

    private List<Point> findEmptyPositions() {

        List<Point> emptyPositions = new ArrayList<>();

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < height - 2; j++) {
                Point p = new Point(i, j);
                emptyPositions.add(p);
            }
        }
        for (int k = 0; k < emptyPositions.size(); k++) {
            for (int l = 0; l < fixedPositions.size(); l++) {
                if (emptyPositions.get(k).equals(fixedPositions.get(l))) {
                //java.lang.IndexOutOfBoundsException: Index: 294, Size: 294
                //ArrayIndexOutOfBoundsException: -1
                    emptyPositions.remove(k);
                    if(k!=0) {
                        k--;
                    }
                }
            }
        }
        return emptyPositions;
    }


    private Point pUStartPosition(List<Point> emptyPositions) {

        Random rand = new Random();
        int i = rand.nextInt(emptyPositions.size());

        while (!(isAboveClear(emptyPositions.get(i)))){
            i = rand.nextInt(emptyPositions.size());
            System.out.println("need to check again; p(i) = " + emptyPositions.get(i));
        }

        return emptyPositions.get(i);
    }

    private boolean isAboveClear(Point empty){

        List<Point> tmp = new ArrayList<>();
        //fel att skapa en ny lista?

        for(int i = 0; i < fixedPositions.size(); i++){
            Point fixed = new Point(fixedPositions.get(i));
            if(fixed.x == empty.x && fixed.y < empty.y){
                return false;
            }
        }
        return true;
    }

    private void randomPowerUp(int level) {
        int random = r.nextInt(1 + level);

        switch (random) {
            case 0:
                powerUp = new ScoreUpPowerUp();
                break;
            case 1:
                powerUp = new ScoreDownPowerUp();
                break;
            case 2:
                powerUp = new LockKeysPowerUp();
                break;
            case 3:
                powerUp = new BlowColumnPowerUp();
                break;
            default: {
                powerUp = new ScoreUpPowerUp();
                break;
            }}}

    public boolean badPowerUp(){
        return powerUp instanceof ScoreDownPowerUp || powerUp instanceof LockKeysPowerUp;
    }

    //Getters

    public AbstractPowerUp getPowerUp() {
        return powerUp;
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board(15, 25);
        }
        return board;
    }

    public Shape getShape() {
        return shape;
    }

    public int getScore() {
        return score;
    }

    public List<Shape> getAllFixedPieces() {
        return allFixedPieces;
    }

    public void setDirectionNone() {
        direction = Direction.NONE;
    }

}


