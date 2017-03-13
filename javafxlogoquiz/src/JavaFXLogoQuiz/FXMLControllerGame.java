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
        Logo arrayLogos[] = new Logo[30];
        
        arrayLogos[0] = new Logo("src\\images\\ATP_400x400.jpeg", 2);
        arrayLogos[1] = new Logo("src\\images\\Aston_Villa.svg.png", 1);
        arrayLogos[2] = new Logo("src\\images\\Boots.svg.png", 1);
        arrayLogos[3] = new Logo("src\\images\\Google_2015_logo.svg.png", 1);
        arrayLogos[4] = new Logo("src\\images\\Minecraft-logo-transparent-background-ut05tirq.png", 1);
        arrayLogos[5] = new Logo("src\\images\\Northern_large.jpg", 1);
        arrayLogos[6] = new Logo("src\\images\\Premier_League_Logo.svg.png", 1);
        arrayLogos[7] = new Logo("src\\images\\Tesco-logo.png", 1);
        arrayLogos[8] = new Logo("src\\images\\javafx_logo_color_1.jpg", 1);
        arrayLogos[9] = new Logo("src\\images\\2437538b6e14481072d15cfc0bd76e6b_alienware-releases-4-new-alienware-logo-hd-clipart_500-500.jpg", 1);
        arrayLogos[10] = new Logo("src\\images\\5007__super_bowl-primary-2016.png", 1);
        
//        arrayLogos[11] = new Logo("src\\images\\519px-Tottenham_Hotspur.svg.png", 1);
//        arrayLogos[12] = new Logo("src\\images\\573f78ba4ec6dda5a2374bb5142ef614.jpg", 1);
//        arrayLogos[13] = new Logo("src\\images\\Aviva_Premiership_logo.svg.png", 1);
//        arrayLogos[14] = new Logo("src\\images\\Carlsberg-logo-4C1D97A3C4-seeklogo.com.gif", 1);
//        arrayLogos[15] = new Logo("src\\images\\Edgbaston-Priory-Club_logo_350x350px-300x300.jpg", 1);
//        arrayLogos[16] = new Logo("src\\images\\EvianLogo2.svg.png", 1);
//        arrayLogos[17] = new Logo("src\\images\\Google_Chrome_icon_(2011).svg.png", 1);
//        arrayLogos[18] = new Logo("src\\images\\Mario_kart_first_logo.png", 1);
//        arrayLogos[19] = new Logo("src\\images\\Middlesbrough_FC_crest.svg.png", 1);
//        arrayLogos[20] = new Logo("src\\images\\The_Legend_of_Zelda_-_Ocarina_of_Time_(logo).png", 1);
//        arrayLogos[21] = new Logo("src\\images\\Thinktank%20Logo.jpg", 1);
//        arrayLogos[22] = new Logo("src\\images\\West_Bromwich_Albion.svg.png", 1);
//        arrayLogos[23] = new Logo("src\\images\\guidelines-logo.7ea045a4e288.png", 1);
//        arrayLogos[24] = new Logo("src\\images\\mac-birmingham-logo.jpg", 1);
//        arrayLogos[25] = new Logo("src\\images\\sainsbury_logo.png", 1);
//        arrayLogos[26] = new Logo("src\\images\\thinkcentre-logo.jpg", 1);
//        arrayLogos[27] = new Logo("src\\images\\windows-logo.png", 1);
//        arrayLogos[28] = new Logo("src\\images\\shield.jpg", 1);
//        arrayLogos[29] = new Logo("src\\images\\IE9.png", 1);
        
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