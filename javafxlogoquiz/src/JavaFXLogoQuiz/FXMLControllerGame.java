package JavaFXLogoQuiz;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        Logo logoOne = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\javafx_logo_color_1.jpg", 1);
        
        File file = new File(logoOne.Url);
        Image img = null;
        try {
            img = new Image(file.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLControllerGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        imgView.setImage(img);
    }
    
    public static class Logo
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