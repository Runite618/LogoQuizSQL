/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxlogoquiz;

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

/**
 *
 * @author matth
 */
public class JavaFXLogoQuiz extends Application {
    
    @Override
    public void start(Stage startStage) {
        Label user = new Label("User: ");
        TextField userField = new TextField ();
        HBox userBox = new HBox();
        userBox.getChildren().addAll(user, userField);
        userBox.setSpacing(10);
        
        Button startGame = new Button();
        startGame.setText("Start game");
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new GameStage();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(startGame);
        root.getChildren().add(userBox);
        
        Scene scene = new Scene(root, 300, 250);
        
        startStage.setTitle("Logo quiz!");
        startStage.setScene(scene);
        startStage.show();
    }
    
    public class GameStage extends Stage{
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
