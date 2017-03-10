package JavaFXLogoQuiz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class FXMLControllerGame implements Initializable{

    @FXML
    private TextField logoField;

    @FXML
    private HBox logoBox;

    @FXML
    private Label logo;

    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL url, ResourceBundle resource)
    {
        
    }
}