package com.mycompany.app.services;

import com.mycompany.app.models.AbstractPowerUp;
import com.mycompany.app.models.Shape;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Created by rackarmattan on 2017-05-11.
 */
public interface IService {
    Image getShapeImage(Shape shapeType);
    Image getPowerUpImage(AbstractPowerUp powerUp);
    List<Image> getThermometers();
    Image getImage(String name);
}
