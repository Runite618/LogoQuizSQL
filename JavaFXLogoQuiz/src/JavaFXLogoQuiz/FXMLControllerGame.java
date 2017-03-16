package JavaFXLogoQuiz;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import static javafx.util.Duration.INDEFINITE;

public class FXMLControllerGame implements Initializable {

    @FXML
    private Button submit;

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
    private Label timerLabel;

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
    
    private static final int STARTTIME = 0;
    
    private Timeline timeline;
    
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        Logo arrayLogos[] = setLogoArray();

        Logo[] arrayChosenLogos = randLogos(arrayLogos);

        setAllImageViews(arrayChosenLogos);
        
        TimeSeconds timeSeconds = gameTimer();
        
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                submitPress(arrayChosenLogos, timeSeconds);
            }
        });
    }
    
    public TimeSeconds gameTimer()
    {
        TimeSeconds timeSeconds = new TimeSeconds(new SimpleIntegerProperty(STARTTIME));
        
        timerLabel.textProperty().bind(timeSeconds.TimeSeconds.asString());
        
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 9999),
                new KeyValue(timeSeconds.TimeSeconds, 9999)));
        timeline.play();
        
        return timeSeconds;
    }
    
    public class TimeSeconds
    {
        private IntegerProperty TimeSeconds;
        
        public TimeSeconds(IntegerProperty timeSeconds)
        {
            this.TimeSeconds = timeSeconds;
        }
        
        public void set(int timeSeconds) 
        {
            TimeSeconds.set(timeSeconds);
        }
        
        public int get()
        {
            return TimeSeconds.get();
        }
    }
    
    public static class Logo {

        public String Answer;
        public String Url;
        public int Point;

        public Logo(String answer, String url, int point) {
            Answer = answer;
            Url = url;
            Point = point;
        }
    }

    public Logo[] setLogoArray() {
        Logo[] arrayLogos = new Logo[30];

        // Stores relevant logo info in new Logo object instances
        arrayLogos[0] = new Logo("ATP", "src\\images\\ATP_400x400.jpeg", 2);
        arrayLogos[1] = new Logo("Aston Villa", "src\\images\\Aston_Villa.svg.png", 2);
        arrayLogos[2] = new Logo("Boots", "src\\images\\Boots.svg.png", 1);
        arrayLogos[3] = new Logo("Google", "src\\images\\Google_2015_logo.svg.png", 1);
        arrayLogos[4] = new Logo("Minecraft", "src\\images\\Minecraft-logo-transparent-background-ut05tirq.png", 1);
        arrayLogos[5] = new Logo("The Northern", "src\\images\\Northern_large.jpg", 3);
        arrayLogos[6] = new Logo("Premier League", "src\\images\\Premier_League_Logo.svg.png", 1);
        arrayLogos[7] = new Logo("Tesco", "src\\images\\Tesco-logo.png", 1);
        arrayLogos[8] = new Logo("JavaFX", "src\\images\\javafx_logo_color_1.jpg", 1);
        arrayLogos[9] = new Logo("Alienware", "src\\images\\2437538b6e14481072d15cfc0bd76e6b_alienware-releases-4-new-alienware-logo-hd-clipart_500-500.jpg", 1);
        arrayLogos[10] = new Logo("Super Bowl", "src\\images\\5007__super_bowl-primary-2016.png", 1);
        arrayLogos[11] = new Logo("Spurs", "src\\images\\519px-Tottenham_Hotspur.svg.png", 2);
        arrayLogos[12] = new Logo("Bleach", "src\\images\\573f78ba4ec6dda5a2374bb5142ef614.jpg", 1);
        arrayLogos[13] = new Logo("Aviva Premiership", "src\\images\\Aviva_Premiership_logo.svg.png", 2);
        arrayLogos[14] = new Logo("Carlsberg", "src\\images\\Carlsberg-logo-4C1D97A3C4-seeklogo.com.gif", 1);
        arrayLogos[15] = new Logo("Edgbaston Priory Club", "src\\images\\Edgbaston-Priory-Club_logo_350x350px-300x300.jpg", 3);
        arrayLogos[16] = new Logo("Evian", "src\\images\\EvianLogo2.svg.png", 1);
        arrayLogos[17] = new Logo("Google Chrome", "src\\images\\Google_Chrome_icon_(2011).svg.png", 1);
        arrayLogos[18] = new Logo("Mario Kart", "src\\images\\Mario_kart_first_logo.png", 1);
        arrayLogos[19] = new Logo("Middlesbrough", "src\\images\\Middlesbrough_FC_crest.svg.png", 2);
        arrayLogos[20] = new Logo("The Legend of Zelda", "src\\images\\The_Legend_of_Zelda_-_Ocarina_of_Time_(logo).png", 1);
        arrayLogos[21] = new Logo("Thinktank", "src\\images\\Thinktank%20Logo.jpg", 3);
        arrayLogos[22] = new Logo("West Bromwich Albion", "src\\images\\West_Bromwich_Albion.svg.png", 2);
        arrayLogos[23] = new Logo("Mozilla Firefox", "src\\images\\guidelines-logo.7ea045a4e288.png", 1);
        arrayLogos[24] = new Logo("Mac", "src\\images\\mac-birmingham-logo.jpg", 3);
        arrayLogos[25] = new Logo("Sainsburys", "src\\images\\sainsbury_logo.png", 1);
        arrayLogos[26] = new Logo("Thinkcentre", "src\\images\\thinkcentre-logo.jpg", 2);
        arrayLogos[27] = new Logo("Windows", "src\\images\\windows-logo.png", 1);
        arrayLogos[28] = new Logo("NFL", "src\\images\\shield.jpg", 2);
        arrayLogos[29] = new Logo("Internet Explorer", "src\\images\\IE9.png", 1);

        return arrayLogos;
    }

    public Logo[] randLogos(Logo[] arrayLogos) {
        List<Logo> chosenLogos = new ArrayList<Logo>();
        // Randomises the logos and makes sure no random logo is selected twice.
        for (int count = 0; count < 10; count++) {
            Logo logo;
            do {
                logo = getRandom(arrayLogos);
            } while (chosenLogos.contains(logo));
            chosenLogos.add(logo);
        }
        return chosenLogos.toArray(new Logo[chosenLogos.size()]);
    }

    public static Logo getRandom(Logo[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    
    public void setAllImageViews(Logo[] arrayChosenLogos) {
        SetImage(arrayChosenLogos[0], imgView);
        SetImage(arrayChosenLogos[1], imgView2);
        SetImage(arrayChosenLogos[2], imgView3);
        SetImage(arrayChosenLogos[3], imgView4);
        SetImage(arrayChosenLogos[4], imgView5);
        SetImage(arrayChosenLogos[5], imgView6);
        SetImage(arrayChosenLogos[6], imgView7);
        SetImage(arrayChosenLogos[7], imgView8);
        SetImage(arrayChosenLogos[8], imgView9);
        SetImage(arrayChosenLogos[9], imgView10);
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

    // When you press submit, this method happens
    public void submitPress(Logo[] arrayChosenLogos, TimeSeconds timeSeconds) {
        Total total = logoTotal(arrayChosenLogos);

        FXMLLoader fxmlLoader = controllerFactory(total, timeSeconds);

        Parent root = loadFxml(fxmlLoader);

        changeScene(root);
    }

    public Total logoTotal(Logo[] arrayChosenLogos) {
        int score = 0;
        int numGuesses = 0;

        for (int count = 1; count <= 10; count++) {
            score = score + RunningTotal(arrayChosenLogos[count - 1], count, true);
            numGuesses = numGuesses + RunningTotal(arrayChosenLogos[count - 1], count, false);
        }
        Total total = new Total(numGuesses, score);
        return total;
    }
    
    public class Total
    {
        public int NumGuesses;
        public int Score;
        
        private Total(int numGuesses, int score)
        {
            NumGuesses = numGuesses;
            Score = score;
        }
    }
    
    public int RunningTotal(Logo logo, int count, boolean isScore) {

        String gottenText = "";

        // Compares the count index to what logoField is required
        switch (count) {
            case 1:
                gottenText = logoField.getText();
                break;
            case 2:
                gottenText = logoField2.getText();
                break;
            case 3:
                gottenText = logoField3.getText();
                break;
            case 4:
                gottenText = logoField4.getText();
                break;
            case 5:
                gottenText = logoField5.getText();
                break;
            case 6:
                gottenText = logoField6.getText();
                break;
            case 7:
                gottenText = logoField7.getText();
                break;
            case 8:
                gottenText = logoField8.getText();
                break;
            case 9:
                gottenText = logoField9.getText();
                break;
            case 10:
                gottenText = logoField10.getText();
                break;
            default:
                gottenText = "";
        }
        if (gottenText.equals(logo.Answer) && (isScore == true)) {
            return logo.Point;
        } else if(gottenText.equals(logo.Answer) && (isScore == false)) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public FXMLLoader controllerFactory(Total total, TimeSeconds timeSeconds) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLScore.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                if (controllerClass == FXMLControllerScore.class) {
                    FXMLControllerScore controller = new FXMLControllerScore();
                    controller.setTotal(total);
                    controller.setTimeSeconds(timeSeconds);
                    return controller;
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc); // just bail
                    }
                }
            }
        });
        return fxmlLoader;
    }

    public Parent loadFxml(FXMLLoader fxmlLoader) {
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLControllerGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }
    
    public void changeScene(Parent root) {
        Stage stage;

        stage = (Stage) submit.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
