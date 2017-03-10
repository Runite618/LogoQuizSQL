/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author matth
 */
public class JavaFXLogoQuiz extends Application 
{
    @Override
    public void start(Stage startStage)
    {
        AnchorPane startPane = null;
        try 
        {
            startPane = (AnchorPane) FXMLLoader.load(JavaFXLogoQuiz.class.getResource("FXMLStart.fxml"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }     
        
        Scene scene = new Scene(startPane);
        
        startStage.setTitle("Logo quiz!");
        startStage.setScene(scene);
        startStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }  
}
