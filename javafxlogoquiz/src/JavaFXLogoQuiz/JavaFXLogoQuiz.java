/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxlogoquiz;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author matth
 */
public class JavaFXLogoQuiz extends Application 
{
    @Override
    public void start(Stage startStage) 
    {
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
        
        Scene scene = new Scene(root, 300, 250);
        
        startStage.setTitle("Logo quiz!");
        startStage.setScene(scene);
        startStage.show();
    }
    
    public Scene gameScene()
    {
        StackPane rootGame = new StackPane();
        File file = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\javafx_logo_color_1.jpg");
        Image img = new Image(file.toURI().toString());
        ImageView imgView = new ImageView(img);
        rootGame.getChildren().add(imgView);
        
        return new Scene(rootGame, 1500, 1000);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }  
    
//    public class LogoRepo(String url, int point)
//    {
//        public String Url;
//        public int Point;
//        
//        public LogoRepo(String url, int point)
//        {
//            Url = url;
//            Point = point;
//        }
//    }
}
