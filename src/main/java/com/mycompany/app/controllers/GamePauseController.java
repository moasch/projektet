package com.mycompany.app.controllers;

import com.mycompany.app.models.Board;
import com.mycompany.app.services.IService;
import com.mycompany.app.services.ImageService;
import com.sun.xml.internal.bind.v2.TODO;
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
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sibas on 2017-08-19.
 */
public class GamePauseController implements Initializable {

    @FXML
    AnchorPane shell;
    @FXML
    private ImageView borderView;
    @FXML
    private Label shellLabel; //behövs denna? Om vi vill skriva nåt
    @FXML
    private ImageView backgroundLevel;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    @FXML
    private Pane background;

    private IService service;
    private Board logicBoard;

    public GamePauseController(){
        this.service = new ImageService();
        this.logicBoard = Board.getInstance();
        this.canvas = new Canvas(240, 400);
        this.background = new Pane();
    }

    public void initialize(URL location, ResourceBundle resourses){
        background.setPrefWidth(240);
        background.setPrefHeight(400);
        borderView = new ImageView(service.getImage("backgroundBorder"));
        moveBackground(20, 20);
        canvas.setFocusTraversable(true);
        shell.getChildren().add(background);
        shell.getChildren().add(canvas);
        background.getChildren().add(shellLabel); //Relevant här?
        shell.getChildren().add(borderView);
        gc = canvas.getGraphicsContext2D();

        canvas.setOnKeyPressed(this::onEvent);
        canvas.setOnKeyReleased(this::offEvent);

        setBackGroundLevel(0);
        //OBS OBS här vill vi hämta level från spelet!!
    }

    private void gamePauseLoop(){
        Timeline t = new Timeline();
        t.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(0.2), new EventHandler<ActionEvent>() {
        //Skall Duration vara 0.2???
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(0, 0, 240, 400);
                /*
                Gör något här!
                Skriva ut en text? ShellLabel?
                Ändra score till 2!
                 */
                if(logicBoard.getScore()==2){
                    t.stop();
                }
            }
        });
        t.getKeyFrames().add(keyFrame);
        t.play();
    }

    private void moveBackground(int x, int y){
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
        background.setTranslateX(x);
        background.setTranslateY(y);
    }

    private void onEvent(KeyEvent evt){
        if(evt.getCode().equals(logicBoard.getScore()==1)){
            gamePauseLoop();
            // && logicBoard.run ?
            //logicBoard.run=false; //Är detta verkligen relevant?
        }
    }
    //Funkar det verkligen att göra så här? Lyssnar canvas av score...?

    private void offEvent(KeyEvent evt){
    }

    private void setBackGroundLevel(int i){
        if(i == 0){
            backgroundLevel = new ImageView(service.getImage("sweden"));
        }else if(i == 1){
            backgroundLevel = new ImageView(service.getImage("europe"));
        }else {
            backgroundLevel = new ImageView(service.getImage("world"));
        }
        background.getChildren().add(backgroundLevel);
        shellLabel = new Label();
        background.getChildren().add(shellLabel);
        shellLabel.setText("Vi kör en paus i spelet!");
    }
}
