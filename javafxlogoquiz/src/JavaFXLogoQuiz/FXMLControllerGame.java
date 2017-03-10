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
        
        arrayLogos[0] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\ATP_400x400.jpeg", 2);
        arrayLogos[1] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Aston_Villa.svg.png", 1);
        arrayLogos[2] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Boots.svg.png", 1);
        arrayLogos[3] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Google_2015_logo.svg.png", 1);
        arrayLogos[4] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Minecraft-logo-transparent-background-ut05tirq.png", 1);
        arrayLogos[5] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Northern_large.jpg", 1);
        arrayLogos[6] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Premier_League_Logo.svg.png", 1);
        arrayLogos[7] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\Tesco-logo.png", 1);
        arrayLogos[8] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\javafx_logo_color_1.jpg", 1);
        arrayLogos[9] = new Logo("C:\\Users\\User\\Documents\\NetBeansProjects\\LogoQuiz\\javafxlogoquiz\\src\\Image\\shield.jpg", 1);
        
        int count = 0;
        
        for (Logo logo : arrayLogos)
        {
            SetImage(logo, count);
            count++;
        }
    }
    
    public void SetImage(Logo logo, int count) {
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