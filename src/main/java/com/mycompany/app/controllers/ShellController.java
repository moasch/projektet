package com.mycompany.app.controllers;

import com.mycompany.app.models.*;
import com.mycompany.app.models.Shape;
import com.mycompany.app.services.IService;
import com.mycompany.app.services.ImageService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by rackarmattan on 2017-03-30.
 */
public class ShellController implements Initializable {

    @FXML
    AnchorPane shell;
    @FXML
    private ImageView borderView;
    @FXML
    private Label shellLabel;
    @FXML
    private ImageView backgroundLevel;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    @FXML
    private Pane background;

    private double activeX;
    private int activeY;
    private int powerUpTime;
    private int privateLevel;
    private int count;

    private IService service;
    private Board logicBoard;

    private int manipulateState;
    private int count2;
    private PauseBoard pauseBoard;

    public ShellController() {
        this.service = new ImageService();
        this.logicBoard = Board.getInstance();
        this.canvas = new Canvas(240, 400);
        this.background = new Pane();
        this.activeX = 0;
        this.activeY = 0;
        this.powerUpTime = 0;
        this.privateLevel = 0;
        this.count = 0;
        this.manipulateState = 0;
        this.count = 0;
        this.pauseBoard = PauseBoard.getInstance();
    }

    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        background.setPrefWidth(240);
        background.setPrefHeight(400);
        borderView = new ImageView(service.getImage("backgroundBorder"));
        moveBackround(20, 20);
        canvas.setFocusTraversable(true);
        shell.getChildren().add(background);
        shell.getChildren().add(canvas);
        background.getChildren().add(shellLabel);
        shell.getChildren().add(borderView);

        canvas.setOnKeyPressed(this::onEvent);
        canvas.setOnKeyReleased(this::offEvent);

        setBackroundLevel(0);

    }

    private void moveBackround(int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
        background.setTranslateX(x);
        background.setTranslateY(y);
    }

    private void gameLoop() {
        Timeline t = new Timeline();
        t.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(0.2), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                logicBoard.runGame();                   //skickar oss till Board o logiken!
                gc.clearRect(0, 0, 240, 400);
                showPowerUp();
                drawFixed();
                for (int i = 0; i < logicBoard.getShape().getShapeList().size(); i++) {
                    activeX = getActiveX(i);
                    activeY = getActiveY(i);
                    gc.drawImage(service.getShapeImage(logicBoard.getShape()), activeX, activeY);
                }

                if (logicBoard.lockKeys) {
                    powerUpTime++;
                    if (powerUpTime > 25) {
                        logicBoard.lockKeys = false;
                        powerUpTime = 0;
                    }
                }
                if (logicBoard.getScore() / logicBoard.levelUpScore > privateLevel) {
                    privateLevel++;
                    setBackroundLevel(logicBoard.level + 1);
                    count = 1;
                }
                /*
                TODO    lägg in ett if-villkor som ökar score enligt poäng från bonusrundan
                 */

                if (logicBoard.checkFullBoard() || logicBoard.getScore() >= 40) {
                    t.stop();
                }

                if (count >= 1) {
                    count++;
                }

                if (count >= 20) {
                    shellLabel.setText(" ");
                    count = 0;
                }

                if (logicBoard.getScore() == 1) {
                    manipulateState++;          //manipulateState blir 1 första gången
                    if (manipulateState == 1) {
                        t.pause();
                        gameLoop2();
                    }

                }
            }
        });
        t.getKeyFrames().add(keyFrame);
        t.play();
    }

    private void gameLoop2() {
        Timeline t2 = new Timeline();
        t2.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame2 = new KeyFrame(javafx.util.Duration.seconds(0.01), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseBoard.runPauseGame();
                gc.clearRect(0, 0, 240, 400);
                shellLabel.setTranslateX(60);
                shellLabel.setTranslateY(50);
                shellLabel.setText("       Grattis till 10p! \n Vi kör en paus i spelet");
                manipulateState++; //manipulateState går från 1 till 2
                count2++;
                drawPig();
                drawFlowers();
                if(count2 > 1000){               //10 sek
                    t2.stop();
                    shellLabel.setText("");
                    gameLoop();
                }
            }
        });
        t2.getKeyFrames().add(keyFrame2);
        t2.play();
    }

    private void drawPig(){                               //TODO ändra namn
        gc.drawImage(service.getImage("pig"),pauseBoard.pigShape.getPigPosition().getX(),pauseBoard.pigShape.getPigPosition().getY(),50,50);
    }

    private void drawFlowers(){
        //gc.drawImage(service.getImage("bonusFlower"),50,50,16,16);          //TODO
        for(int i = 0; i < pauseBoard.getRandomPositions().size(); i++){
            gc.drawImage(service.getImage("bonusFlower"),pauseBoard.getRandomPositions().get(i).x,pauseBoard.getRandomPositions().get(i).y,16,16);
        }
    }

    private void setBackroundLevel(int i) {
        if (i == 0) {
            backgroundLevel = new ImageView(service.getImage("sweden"));
        } else if (i == 1) {
            backgroundLevel = new ImageView(service.getImage("europe"));
        } else {
            backgroundLevel = new ImageView(service.getImage("world"));
        }
        background.getChildren().add(backgroundLevel);
        shellLabel = new Label();
        background.getChildren().add(shellLabel);
        if (privateLevel != 0 && logicBoard.getScore() < 4 * logicBoard.levelUpScore) {
            shellLabel.setTranslateX(50);
            shellLabel.setTranslateY(50);
            shellLabel.setText("Nya powerups upplåsta");

        }
    }


    private int getActiveY(int indexPosition) {
        return logicBoard.getShape().getShapeList().get(indexPosition).y * 16;
    }

    private int getActiveX(int indexPosition) {
        return logicBoard.getShape().getShapeList().get(indexPosition).x * 16;
    }

    private void showPowerUp() {
        if (logicBoard.hasPowerUp) {
            gc.drawImage(service.getPowerUpImage(logicBoard.getPowerUp()), logicBoard.getPowerUp().getPosition().x * 16, logicBoard.getPowerUp().getPosition().y * 16, 25, 25);
        }
    }

    private void drawFixed() {
        for (int i = 0; i < logicBoard.getAllFixedPieces().size(); i++) {
            Shape tmp = logicBoard.getAllFixedPieces().get(i);
            for (int j = 0; j < tmp.getShapeList().size(); j++) {
                gc.drawImage(service.getShapeImage(tmp), tmp.getShapeList().get(j).x * 16, tmp.getShapeList().get(j).y * 16);
            }
        }
    }

    private void onEvent(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.SPACE) && logicBoard.run) {
            gameLoop();
            logicBoard.run = false;
        }

        if (logicBoard.lockKeys) {
            logicBoard.setDirectionNone();
        } else {
            logicBoard.updateDirection(evt.getCode());
        }
        /*
        TODO Lägga in villkor som kollar om "pasueGame körs", typ pauseBoard.run?
         */
        pauseBoard.updateDirection(evt.getCode());
    }

    private void offEvent(KeyEvent evt) {
        logicBoard.setDirectionNone();
        pauseBoard.setDirectionNone();
    }

}
