package com.mycompany.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane page = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Shell.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Main");

            AudioClip audio = new AudioClip(new File("src/main/resources/audio/sandstorm.mp3").toURI().toString());
            //audio.play();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
