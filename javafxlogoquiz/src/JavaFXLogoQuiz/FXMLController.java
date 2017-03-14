package JavaFXLogoQuiz;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLController implements Initializable{

    @FXML
    private TextField userField;

    @FXML
    private HBox userBox;

    @FXML
    private Button startGame;

    @FXML
    private Label user;
    
    public void initialize(URL url, ResourceBundle resource)
    {
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override 
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root = null;
                
                stage = (Stage) startGame.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("FXMLGame.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
