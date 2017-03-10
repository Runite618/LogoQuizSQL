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
        
        
        Label user = new Label("User: ");
        TextField userField = new TextField ();
        HBox userBox = new HBox();
        StackPane root = new StackPane();
        userBox.getChildren().addAll(user, userField);
        userBox.setSpacing(10);
        
        root.getChildren().add(userBox);
        
        Button startGame = new Button("Start game");
                
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override 
            public void handle(ActionEvent event) {
                startStage.setScene(gameScene());
                startStage.show();
            }
        });
        
        root.getChildren().add(startGame);
        
        Scene scene = new Scene(startPane);
        
        startStage.setTitle("Logo quiz!");
        startStage.setScene(scene);
        startStage.show();
    }
    
    public Scene gameScene()
    {
        StackPane rootGame = new StackPane();
        
        Logo logoOne = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\javafx_logo_color_1.jpg", 1);
        
        File file = new File(logoOne.Url);
        Image img = new Image(file.toURI().toString());
        ImageView imgView = new ImageView(img);
        
        Label logo = new Label("Logo: ");
        TextField logoField = new TextField ();
        HBox logoBox = new HBox();;
        logoBox.getChildren().addAll(logo, logoField);
        logoBox.setSpacing(10);
        
        rootGame.getChildren().add(imgView);
        rootGame.getChildren().add(logoBox);

        return new Scene(rootGame, 1500, 1000);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }  
    
    public class Logo
    {
        public String Url;
        public int Point;
        
        public Logo(String url, int point)
        {
            Url = url;
            Point = point;
        }
    }
}
