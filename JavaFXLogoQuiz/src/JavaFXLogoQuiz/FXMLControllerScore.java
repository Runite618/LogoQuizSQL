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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private Button reset;

    @FXML
    private Label score;
    
    @FXML
    private Label numCorrectGuesses;

    @FXML
    private HBox scoreBox;
    
    @FXML
    private HBox numGuessesBox;

    @FXML
    private TextField scoreField;
    
    @FXML
    private TextField numGuessesField;

    @FXML
    private TextField timeTakenField;
    
    @FXML
    private TableView<HiScore> tableView;

    @FXML
    private TableColumn<HiScore, String> value;

    @FXML
    private TableColumn<HiScore, String> scoreRange;

    public FXMLControllerGame.Total Total;

    public void setTotal(FXMLControllerGame.Total total) {
        this.Total = total;
    }
    
    public FXMLControllerGame.TimeSeconds TimeSeconds;
    
    public void setTimeSeconds(FXMLControllerGame.TimeSeconds timeSeconds)
    {
        this.TimeSeconds = timeSeconds;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        numGuessesField.setText(Integer.toString(Total.NumGuesses));
        timeTakenField.setText(Integer.toString(TimeSeconds.get()));
        
        displayScore.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                formatTextField();
                addTable();
            }
        });
        
        reset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                }

                changeScene(root);
            }
        });
    }
    
    public void changeScene(Parent root) {
        Stage stage;

        stage = (Stage) reset.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void formatTextField() {
        DecimalFormat myFormatter = new DecimalFormat("##");
        String output = myFormatter.format(Total.Score);
        System.out.print(Total.Score);
        scoreField.setText(output);
    }

    public void addTable() {
        ObservableList<HiScore> data = FXCollections.observableArrayList(
                new HiScore("<8", "Disappointing, try again"),
                new HiScore("8-15", "Ok"),
                new HiScore("16+", "Great!")
        );

        value.setCellValueFactory(new PropertyValueFactory<HiScore, String>("value"));
        scoreRange.setCellValueFactory(new PropertyValueFactory<HiScore, String>("scoreRange"));

        tableView.setItems(data);
        
        setRowFactory();
    }

    private void setRowFactory() {
        tableView.setRowFactory(tv -> new TableRow<HiScore>() {
            
            @Override
            protected void updateItem(HiScore item, boolean empty) {
                super.updateItem(item, empty);

                String color = "";
                if (Total.Score < 8) {
                    color = "-fx-background-color: red";
                } else if (Total.Score >= 8 && Total.Score <= 15) {
                    color = "-fx-background-color: orange";
                } else if (Total.Score >= 16) {
                    color = "-fx-background-color: green";
                }
                setStyle(color);
            }
        });
    }

    public class HiScore {

        public SimpleStringProperty ScoreRange;
        public SimpleStringProperty Value;

        public HiScore(String scoreRange, String value) {
            this.ScoreRange = new SimpleStringProperty(scoreRange);
            this.Value = new SimpleStringProperty(value);
        }

        public String getScoreRange() {
            return ScoreRange.get();
        }

        public String getValue() {
            return Value.get();
        }

        public void setScoreRange(String scoreRange) {
            ScoreRange.set(scoreRange);
        }

        public void setValue(String value) {
            Value.set(value);
        }
    }
}
