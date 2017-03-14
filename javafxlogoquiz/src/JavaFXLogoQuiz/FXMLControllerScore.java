/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLControllerScore implements Initializable {
    
    @FXML
    private Label score;

    @FXML
    private HBox scoreBox;

    @FXML
    private TextField scoreField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}