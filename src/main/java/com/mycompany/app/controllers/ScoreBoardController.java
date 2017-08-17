package com.mycompany.app.controllers;

import com.mycompany.app.models.Board;
import com.mycompany.app.services.IService;
import com.mycompany.app.services.ImageService;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Duration;
import javafx.scene.canvas.GraphicsContext;





/**
 * Created by rackarmattan on 2017-05-02.
 */
public class ScoreBoardController implements Initializable {

    @FXML
    private GraphicsContext gc;
    @FXML
    private Canvas canvas;
    @FXML
    private Rectangle rectangle;
    @FXML
    AnchorPane scoreBoard;
    @FXML
    private ImageView imageViewPig;
    @FXML
    private ImageView imageViewConversation;

    private TranslateTransition tP;
    private FadeTransition fC;

    private Board logicBoard;
    private IService service;
    private int stageTime;
    private int halfLevel;
    private int thermMode;
    private boolean pigStage;


    public ScoreBoardController() {
        this.service = new ImageService();
        this.logicBoard = Board.getInstance();
        this.rectangle = new Rectangle(230, 200);
        this.imageViewPig = new ImageView();
        this.imageViewConversation = new ImageView();
        this.tP = new TranslateTransition(Duration.millis(2000), imageViewPig);
        this.fC = new FadeTransition(Duration.millis(3000), imageViewConversation);
        this.canvas = new Canvas(480, 410); //skapar en canvas
        this.thermMode = 0;
        this.halfLevel = 0;
        this.stageTime = 0;
        this.pigStage = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        scoreBoard.getChildren().add(rectangle);
        handleScoreBoard();

        rectangle.setFill(Color.ALICEBLUE);
        rectangle.setTranslateY(80);
        rectangle.setTranslateX(80);
        scoreBoard.getChildren().add(canvas); //adderar canvas till anchorpanen

        gc.drawImage(service.getImage("frame"), 60, 40, 280, 260);
        gc.drawImage(service.getImage("basicThermometer"), 20, 44, 200, 240);
        gc.setStroke(Color.BLACK);
        gc.setFont(new Font("sans-serif", 20));
        gc.strokeText("POÄNGTAVLA", 150, 120, 500);
        gc.setFont(new Font("sans-serif", 60));

        gc.setStroke(javafx.scene.paint.Color.GREEN);
        scoreBoard.getChildren().add(imageViewPig);
        scoreBoard.getChildren().add(imageViewConversation);
    }

    private void handleScoreBoard() {
        Timeline t = new Timeline();
        t.setCycleCount(Animation.INDEFINITE);
        KeyFrame points = new KeyFrame(javafx.util.Duration.seconds(1), new javafx.event.EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {

                gc.clearRect(150, 145, 140, 59);
                gc.setFont(new Font("sans-serif", 60));
                gc.setStroke(Color.GREEN);
                gc.strokeText(logicBoard.getScore() + "p", 170, 190, 500);

                pigMessages();

                if (pigStage) {
                    stageTime++;
                }

                if (stageTime > 5) {
                    pigOffStage();
                }

                    if (logicBoard.getScore() / 5 > halfLevel) {

                        gc.drawImage(service.getThermometers().get(thermMode), 20, 44, 200, 240);
                        if (thermMode < service.getThermometers().size() - 1) {
                            thermMode++;
                        }
                        halfLevel++;
                    }

                }

        });
        t.getKeyFrames().add(points);
        t.play();

    }

    private void setPigStage() {
        if (logicBoard.level <= 1) {
            imageViewPig.setImage(service.getImage("pig"));
        }
        if (logicBoard.level == 2) {
            imageViewPig.setImage(service.getImage("pigWithGlasses"));
        }
        if (logicBoard.level >= 3) {
            imageViewPig.setImage(service.getImage("pigWithPoop"));
        }
        imageViewPig.setFitHeight(100);
        imageViewPig.setFitWidth(100);
        imageViewPig.setX(400);
        imageViewPig.setY(250);
    }

    private void setConversation() {
        imageViewConversation.setImage(service.getImage("conversation2"));
        imageViewConversation.setFitHeight(100);
        imageViewConversation.setFitWidth(100);
        imageViewConversation.setX(150);
        imageViewConversation.setY(220);
    }


    private void pigHello() {
        setPigStage();
        tP.setByX(-150);
        tP.play();
        setConversation();
        fC.setFromValue(0.0);
        fC.setToValue(1.0);
        fC.play();
    }

    private void pigGoodbye() {
        setPigStage();
        tP.setByX(150);
        tP.play();
        fC.setFromValue(1.0);
        fC.setToValue(0.0);
        fC.play();
    }

    private void pigOffStage() {
        if (pigStage) {
            pigGoodbye();
            pigStage = false;
            stageTime = 0;
        }
    }

    private void pigMessages() {
        if (logicBoard.hasHitPowerUpScore) {
            if (logicBoard.badPowerUp()) {
                pigOnStage();
            }

            logicBoard.hasHitPowerUpScore = false;

        }
    }


    private void pigOnStage() {
        if (!pigStage) {
            pigHello();
            pigStage = true;
        }


    }
}