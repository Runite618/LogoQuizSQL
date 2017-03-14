/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLControllerScore implements Initializable {

    @FXML
    private Button displayScore;
    
    @FXML
    private Label score;

    @FXML
    private HBox scoreBox;

    @FXML
    private TextField scoreField;

    public int index;
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayScore.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                formatTextField();
            }
        });
    }
    
    public void formatTextField()
    {
        DecimalFormat myFormatter = new DecimalFormat("##");
        String output = myFormatter.format(index);
        System.out.print(index);
        scoreField.setText(output);
    }
}
