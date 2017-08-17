package com.mycompany.app;


import com.mycompany.app.models.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Unit test for simple App.
 *
 */

public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /*
    public void testContains(){
        ArrayList<Point> tmp1 = new ArrayList<>();
        ArrayList<Point> tmp2 = new ArrayList<>();
        Point p = new Point(1, 1);
        Point p2 = new Point(1,1);
        tmp1.add(p);
        tmp2.add(p2);
        assertTrue(tmp1.contains(p2));
    }
    */
    /*
    public void testRemove(){
        ArrayList<Point> test = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            Point p = new Point(i, 3);
            test.add(p);
        }
        for (int j = 0; j < test.size(); j++){
            if (test.get(j).x == 2){
                test.remove(j);
            }
        }
        Point p2 = new Point(2, 3);
        assertTrue(!test.contains(new Point(2,3)) && test.size() == 3);
    }
    */
    /*
    public void testBlowColumn(){
        Board board = Board.getInstance();
        for(int i = 0; i < 10; i++){
            board.getFixedPositions().add(new Point(5,i));
        }
        board.blowColumn(5);
        assertTrue(board.getFixedPositions().size() == 0);
    }
    */
/*
    public void testFindEmptyPositions(){

        Board board = Board.getInstance();
        for(int i = 0; i < 25; i ++){
            for(int j = 0; j < 15; j++){
                Point p1 = new Point(i,j);
                board.fixedPositions.add(p1);
                //Börjar med att fylla hela fixedPositions med
                //brädets alla punkter.
            }
        }
        Point p2 = new Point(5,5);
        board.fixedPositions.remove(p2);
        //Point p3 = new Point(5,6);
        //Tar bort en bestämd punkt.

        assertTrue(board.findEmptyPositions().contains(p2));

    }
*/
/*
    public void testisAboveClear(){

        Board board = Board.getInstance();

        List<Point> fixed = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            fixed.add(new Point(5,i));
        }

        Point empty = new Point(5,11);

        assertFalse(board.isAboveClear(empty,fixed));
    }
*/

/*
    public void testBlowRow(){
        Board board = Board.getInstance();

    }
*/
/*
    public void testNewMoveDown(){
        Board board = Board.getInstance();
        for (int i = 0; i < 15; i++){
            board.fixedPositions.add(new Point(i, 10));
        }
        board.movePointsDown(11);
        assertTrue(board.fixedPositions.get(1).y == 11 && board.fixedPositions.get(5).y == 11);
    }*/

/*
    public void testBlowRow(){
        Board board = Board.getInstance();
        for (int i = 0; i < 15; i++){
            board.fixedPositions.add(new Point(i, 5));
        }
        board.blowRow(5);
        assertTrue(board.fixedPositions.size() == 0);
    }*/
/*
    public void testUpdatePosition(){
        Board.Direction d = Board.Direction.RIGHT;
        IShape testI = new IShape();
        testI.testIshape();
        ArrayList<Point> tmp = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 4; i++){
            Point p = new Point(testI.getShapeList().get(i).position.x + 1, testI.getShapeList().get(i).position.y);
            tmp.add(p);
        }
        ArrayList<Point> i = testI.updatePositions(d);
        for (int j = 0; j < 4; j++){
            if (tmp.get(j).equals(i.get(j))){
                count++;
            }
        }
        assertTrue(count==4);
    }

    public void testUpdateDirection(){
        Board board = new Board(20, 20);
        board.updateDirection(KeyCode.RIGHT);
        assertTrue(board.getDirection() == Board.Direction.RIGHT);
        board.updateDirection(KeyCode.K);
        assertTrue(board.getDirection() == Board.Direction.NONE);
    }

    public void testSetStartPosition(){
        Board board = new Board(20,20);
        board.setStartPosition();
        assertTrue(board.hasFalling);
    }

    /*
    public void testCanFall2(){
        Board board = new Board(20,20);
        board.getFixedPositions().add(new Point(1,1));
        board.getFixedPositions().add(new Point(2,2));
        board.getFixedPositions().add(new Point(1,2));
        ArrayList<Point> tmp = new ArrayList<>();
        tmp.add(new Point(1, 1));
        tmp.add(new Point(3,3));
        tmp.add(new Point(4,1));
        tmp.add(new Point(5,3));
        assertFalse(board.canFall(tmp));
        tmp.set(0, new Point(6,6));
        assertTrue(board.canFall(tmp));
    }

    public void testCanFallDown(){
        Board board = new Board(20,20);
        board.getFixedPositions().add(new Point(15,17));
        board.getFixedPositions().add(new Point(15,20));
        board.getFixedPositions().add(new Point(15,19));
        board.getFixedPositions().add(new Point(15,18));
        ArrayList<Point> tmp = new ArrayList<>();
        tmp.add(new Point(15,16));
        tmp.add(new Point(15,15));
        tmp.add(new Point(15, 14));
        tmp.add(new Point(15, 13));
        assertFalse(board.canFallDown(tmp));

    }*/
/*
    public void testBlowRow(){
        Board board = new Board(15, 25);

    }

*/
/*    public void testSetStartPosition(){
        Board board = new Board(40, 40);
        board.setStartPosition();
        int count = 0;
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 40; col++){
                if (board.board[row][col] != 0){
                    count++;
                }
            }
        }
        assertTrue(count ==4);
        assertTrue(board.piece.getPositions().size() == 4);
    }*/
/*
    public void testStartPosition(){
        Board board = new Board(40, 40);
        board.setStartPosition();
        assertTrue(board.piece.getPositions().size() == 4);
        assertTrue(board.hasFalling);
    }
    public void testCanFall(){
        Board board = new Board(10, 10);
        board.setStartPosition();
        assertTrue(board.canFall());
    }*/
/*
    public void testRandomPowerUp(){

        Board board = Board.getInstance();
        int random = 2;

        board.randomPowerUp(random);

        assertTrue(board.getPowerUp() instanceof LockKeysPowerUp);
    }
*/
/*
    public void testCheckFullBoard1(){
        Board board = Board.getInstance();
        Point p = new Point(0,0);
        board.fixedPositions.add(p);
        assertTrue(board.checkFullBoard());
    }
    public void testCheckFullBoard2(){
        Board board = Board.getInstance();
        for(int i = 0; i < board.height; i++){
            for(int j = 0; j < board.width; j++){
                Point p = new Point(i, j);
                board.fixedPositions.add(p);
            }
        }
        assertTrue(board.checkFullBoard());
    }
*/
/*
    public void testCanMove(){
        Board board = Board.getInstance();
        List<Point> shapeNextMove = new ArrayList<>();
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,0);
        Point p3 = new Point(2,0);
        Point p4 = new Point(1,1);
        shapeNextMove.add(p1);
        shapeNextMove.add(p2);
        shapeNextMove.add(p3);
        shapeNextMove.add(p4);
        Point pointBlockingShape = new Point(1,1);
        board.fixedPositions.add(pointBlockingShape);
        assertFalse(board.canMove(shapeNextMove));
    }
*/
}



