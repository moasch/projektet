package com.mycompany.app.services;

import com.mycompany.app.models.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rackarmattan on 2017-05-11.
 */
public class ImageService implements IService {
    @FXML
    private Image IColor;
    @FXML
    private Image SquareColor;
    @FXML
    private Image LColor;
    @FXML
    private Image TColor;
    @FXML
    private Image firstFill;
    @FXML
    private Image secondFill;
    @FXML
    private Image thirdFill;
    @FXML
    private Image fourthFill;
    @FXML
    private Image fifthFill;
    @FXML
    private Image sixthFill;
    @FXML
    private Image seventhFill;
    @FXML
    private Image lastFill;
    @FXML
    private Image pipe;
    @FXML
    private Image angelWings;
    @FXML
    private Image handshake;
    @FXML
    private Image passPort;

    private Map<String, Image> nameImage = new HashMap<>();

    public ImageService() {
        nameImage.put("openMouth", new Image("pics/openmouth.png"));
        nameImage.put("closedMouth", new Image("pics/closedmouth.png"));
        nameImage.put("conversation", new Image("pics/conversation.png"));
        nameImage.put("europe", new Image("pics/europe.png"));
        nameImage.put("world", new Image("pics/world.png"));
        nameImage.put("sweden", new Image("pics/sweden.png"));
        nameImage.put("basicThermometer", new Image("pics/basicthermometer.png"));
        nameImage.put("backgroundBorder", new Image("pics/ram1.png"));
        nameImage.put("pig", new Image("pics/pig2.png"));
        nameImage.put("pigWithGlasses", new Image("pics/pigwithglasses.png"));
        nameImage.put("pigWithPoop", new Image("pics/pigwithpoop.png"));
        nameImage.put("conversation2", new Image("pics/conversation2.png"));
        nameImage.put("frame", new Image("pics/frame.png"));
        this.IColor = new Image("pics/clematis.png");
        this.SquareColor = new Image("pics/flower.png");
        this.LColor = new Image("pics/sunflower.png");
        this.TColor = new Image("pics/zinnia.png");
        this.pipe = new Image("pics/pipes1.png");
        this.angelWings = new Image("pics/angel.png");
        this.firstFill = new Image("pics/firstfill.png");
        this.secondFill = new Image("pics/secondfill.png");
        this.thirdFill = new Image("pics/thirdfill.png");
        this.fourthFill = new Image("pics/fourthfill.png");
        this.fifthFill = new Image("pics/fifthfill.png");
        this.sixthFill = new Image("pics/sixthfill.png");
        this.seventhFill = new Image("pics/seventhfill.png");
        this.lastFill = new Image("pics/lastfill.png");
        this.passPort = new Image("pics/passport-card.png");
        this.handshake = new Image("pics/handshake.png");
    }

    @Override
    public Image getShapeImage(Shape shapeType) {
        if (shapeType instanceof IShape) {
            return IColor;
        } else if (shapeType instanceof TShape) {
            return TColor;
        } else if (shapeType instanceof SquareShape) {
            return SquareColor;
        } else {
            return LColor;
        }
    }

    @Override
    public Image getPowerUpImage(AbstractPowerUp powerUp) {
        if (powerUp instanceof ScoreDownPowerUp) {
            return pipe;

        } else if (powerUp instanceof ScoreUpPowerUp) {
            return angelWings;
        } else if (powerUp instanceof LockKeysPowerUp) {
            return passPort;
        } else {
            return handshake;
        }
    }

    @Override
    public List<Image> getThermometers() {
        List<Image> thermometers = new ArrayList<>();
        thermometers.add(firstFill);
        thermometers.add(secondFill);
        thermometers.add(thirdFill);
        thermometers.add(fourthFill);
        thermometers.add(fifthFill);
        thermometers.add(sixthFill);
        thermometers.add(seventhFill);
        thermometers.add(lastFill);
        return thermometers;
    }

    public Image getImage(String name) {
        return nameImage.get(name);
    }
}
