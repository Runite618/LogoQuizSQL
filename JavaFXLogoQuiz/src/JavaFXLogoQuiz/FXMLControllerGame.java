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
    private Label logo2;

    @FXML
    private HBox logoBox3;

    @FXML
    private HBox logoBox2;

    @FXML
    private HBox logoBox5;

    @FXML
    private HBox logoBox4;

    @FXML
    private HBox logoBox7;

    @FXML
    private HBox logoBox6;

    @FXML
    private HBox logoBox9;

    @FXML
    private Label logo8;

    @FXML
    private HBox logoBox;

    @FXML
    private HBox logoBox8;

    @FXML
    private Label logo7;

    @FXML
    private ImageView imgView;

    @FXML
    private Label logo9;

    @FXML
    private Label logo4;

    @FXML
    private Label logo3;

    @FXML
    private Label logo6;

    @FXML
    private Label logo5;

    @FXML
    private TextField logoField10;

    @FXML
    private Label logo10;

    @FXML
    private TextField logoField;

    @FXML
    private Label logo;

    @FXML
    private HBox logoBox10;

    @FXML
    private ImageView imgView8;

    @FXML
    private ImageView imgView9;

    @FXML
    private ImageView imgView10;

    @FXML
    private TextField logoField9;

    @FXML
    private TextField logoField8;

    @FXML
    private TextField logoField7;

    @FXML
    private ImageView imgView2;

    @FXML
    private TextField logoField6;

    @FXML
    private ImageView imgView3;

    @FXML
    private TextField logoField5;

    @FXML
    private TextField logoField4;

    @FXML
    private TextField logoField3;

    @FXML
    private TextField logoField2;

    @FXML
    private ImageView imgView6;

    @FXML
    private ImageView imgView7;

    @FXML
    private ImageView imgView4;

    @FXML
    private ImageView imgView5;

    @Override
    public void initialize(URL url, ResourceBundle resource)
    {
        Logo arrayLogos[] = new Logo[10];
        
        arrayLogos[0] = new Logo("src\\images\\ATP_400x400.jpeg", 2);
        arrayLogos[1] = new Logo("src\\images\\Aston_Villa.svg.png", 1);
        arrayLogos[2] = new Logo("src\\images\\Boots.svg.png", 1);
        arrayLogos[3] = new Logo("src\\images\\Google_2015_logo.svg.png", 1);
        arrayLogos[4] = new Logo("src\\images\\Minecraft-logo-transparent-background-ut05tirq.png", 1);
        arrayLogos[5] = new Logo("src\\images\\Northern_large.jpg", 1);
        arrayLogos[6] = new Logo("src\\images\\Premier_League_Logo.svg.png", 1);
        arrayLogos[7] = new Logo("src\\images\\Tesco-logo.png", 1);
        arrayLogos[8] = new Logo("src\\images\\javafx_logo_color_1.jpg", 1);
        arrayLogos[9] = new Logo("src\\images\\shield.jpg", 1);
        
        SetImage(arrayLogos[0], imgView);
        SetImage(arrayLogos[1], imgView2);
        SetImage(arrayLogos[2], imgView3);
        SetImage(arrayLogos[3], imgView4);
        SetImage(arrayLogos[4], imgView5);
        SetImage(arrayLogos[5], imgView6);
        SetImage(arrayLogos[6], imgView7);
        SetImage(arrayLogos[7], imgView8);
        SetImage(arrayLogos[8], imgView9);
        SetImage(arrayLogos[9], imgView10);
    }
    
    public void SetImage(Logo logo, ImageView imgView) {
        File file = new File(logo.Url);
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