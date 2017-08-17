package com.mycompany.app.controllers;


/**
 * Created by Alice on 2017-05-02.
 */

import com.mycompany.app.models.Board;
import com.mycompany.app.services.IService;
import com.mycompany.app.services.ImageService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;


public class AngelPaneController implements Initializable {

    @FXML
    AnchorPane AngelPane;
    @FXML
    TextArea angelTalk;
    @FXML
    private GraphicsContext gc;
    @FXML
    private Canvas canvas;

    private Board logicBoard;
    private IService service;

    public AngelPaneController() {
        this.service = new ImageService();
        this.logicBoard = Board.getInstance();
        this.canvas = new Canvas(337, 184);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        AngelPane.getChildren().add(canvas);

        gc.drawImage(service.getImage("closedMouth"), 95, 60, 25, 25);
        angelTalk.setText("       Hej! Välkommen \n till PK-tetris! Tryck på \n  space för att starta!");

        gc.drawImage(service.getImage("conversation"), 150, 5, 180, 140);
        messages();


    }


    private void messages() {
        Timeline t = new Timeline();
        t.setCycleCount(Animation.INDEFINITE);
        KeyFrame mouth = new KeyFrame(javafx.util.Duration.seconds(1), new javafx.event.EventHandler<ActionEvent>() {
            int c = 0;

            @Override
            public void handle(ActionEvent event) {

                if(logicBoard.getScore()<4*logicBoard.levelUpScore) {
                if (!logicBoard.run && logicBoard.getScore() == 0) {
                    angelTalk.setText("       Samla poäng \n genom att spränga bort \n      rader och ta bra  \n            powerups");
                }


                if (logicBoard.blow) {
                    gc.drawImage(service.getImage("openMouth"), 95, 60, 25, 25);
                    angelTalk.setText("             Grattis du \n     radera en hel rad");
                    logicBoard.blow = false;
                    c = 1;

                }


                if (logicBoard.hasHitPowerUpAngel) {
                    if (logicBoard.badPowerUp()) {
                        gc.drawImage(service.getImage("openMouth"), 95, 60, 25, 25);
                        angelTalk.setText("             Nej! Ta  \n     inte dåliga powerups");
                        c = 1;

                    }
                    if (!(logicBoard.badPowerUp())) {
                        gc.drawImage(service.getImage("openMouth"), 95, 60, 25, 25);
                        angelTalk.setText("             Ja! Fortsätt  \n     ta bra powerups");
                        c = 1;

                    }
                    logicBoard.hasHitPowerUpAngel = false;
                }

                if (logicBoard.getScore() == 2 * logicBoard.levelUpScore) {
                    angelTalk.setText("             Bra jobbat! \n  Du har kommit halvvägs");
                }

                if (c >= 1) {
                    c++;
                }
                if (c >= 5) {
                    close();
                    c = 0;
                }

                if (logicBoard.checkFullBoard()) {
                    angelTalk.setText("             Tyvärr du \n    är inte tillräckligt PK");
                }

            }
                if(logicBoard.getScore()>=4*logicBoard.levelUpScore) {
                        angelTalk.setText("             Grattis! Du \n       är tillräckligt PK");
                    }


            }

        });
        t.getKeyFrames().add(mouth);
        t.play();

    }

    private void close() {
        gc.clearRect(95, 60, 25, 25);
        gc.drawImage(service.getImage("closedMouth"), 95, 60, 25, 25);
        angelTalk.setText("             Fortsätt nu \n     så blir du kanske lika  \n               pk som jag");
    }
}
