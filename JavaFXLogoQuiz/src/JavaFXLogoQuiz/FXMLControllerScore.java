/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import JavaFXLogoQuiz.FXMLController.UserName;
import JavaFXLogoQuiz.FXMLControllerScore.UserScore.GetComparator;
import static com.sun.deploy.cache.Cache.reset;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import static sun.plugin.ClassLoaderInfo.reset;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLControllerScore implements Initializable {

   @FXML
   private TableView<UserScore> userScoreTv;

   @FXML
   private TableColumn<?, ?> cUser;

   @FXML
   private TableColumn<?, ?> cScore;

   @FXML
   private TableColumn<?, ?> cCorrectGuesses;

   @FXML
   private TableColumn<?, ?> cTime;

   @FXML
   private Button displayScore;

   @FXML
   private Button reset;
   
   @FXML
   private Button guessButton;
           
   @FXML
   private Button timeButton;
   
   @FXML
   private Button scoreButton;

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

   public FXMLController.UserName UserName;

   public void setUser(FXMLController.UserName userName) {
      this.UserName = userName;
   }

   public void setTotal(FXMLControllerGame.Total total) {
      this.Total = total;
   }

   public FXMLControllerGame.TimeSeconds TimeSeconds;

   public void setTimeSeconds(FXMLControllerGame.TimeSeconds timeSeconds) {
      this.TimeSeconds = timeSeconds;
   }

   public boolean buttonPress = false;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      try {
         // TODO
         // Displays main elements of page
         numGuessesField.setText(Integer.toString(Total.NumGuesses));
         timeTakenField.setText(Integer.toString(TimeSeconds.get()));
         scoreButton.setVisible(false);
         
         ObservableList<UserScore> data = FXCollections.observableArrayList(
                 new UserScore(Total.Score, UserName.User, Total.NumGuesses, Integer.parseInt(timeTakenField.getText())) {
            @Override
            public int compareTo(UserScore o) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
         });
         writeToTxtFile(data);
         setUserScoreData(userScoreTv);
         ObservableList<UserScore> wholeList = readFromTxtFile();
         
         //When button is pressed
         displayScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (buttonPress == false) {
                  try {
                     buttonPress = true;
                     formatTextField();
                     setHiScoreData(tableView);
                     setUserScoreData(userScoreTv);
                     readFromTxtFile();
                     scoreButton.setVisible(true);
                  } catch (IOException ex) {
                     Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                  }
               }
            }
         });
         
         reset.setOnAction((ActionEvent event) -> {
            Parent root = null;
            try {
               root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));
            } catch (IOException ex) {
               Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            changeScene(root);
         });
         
         // The following three buttons are filter buttons
         guessButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               try {
                  UserScore[] userScoreArray = setFilter();
                  Arrays.sort(userScoreArray);
                  sortAndSetTable(userScoreArray);
               } catch (IOException ex) {
                  Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         });
         
         timeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               try {
                  UserScore[] userScoreArray = setFilter();
                  UserScore userScore = new UserScore();
                  GetComparator comparator = userScore.new GetComparator();
                  Arrays.sort(userScoreArray, comparator.getComparator("time"));
                  sortAndSetTable(userScoreArray);
               } catch (IOException ex) {
                  Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         });
         
         scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               try {
                  UserScore[] userScoreArray = setFilter();
                  UserScore userScore = new UserScore();
                  GetComparator comparator = userScore.new GetComparator();
                  Arrays.sort(userScoreArray, comparator.getComparator("score"));
                  sortAndSetTable(userScoreArray);
               } catch (IOException ex) {
                  Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         });
      } catch (IOException ex) {
         Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   // Transfers a list into an array and then sets the tableView
   public void sortAndSetTable(UserScore[] userScoreArray)
   {
      ObservableList<UserScore> obs = FXCollections.observableArrayList();
      for (UserScore score : userScoreArray) {
         obs.add(score);
      }
      userScoreTv.setItems(obs);
   }
   
   // Set filter method
   public UserScore[] setFilter() throws IOException
   {
      List<String> list = Files.readAllLines(new File("UserScores.txt").toPath());

      UserScore[] userScoreArray = new UserScore[list.size() / 4];

      int count = 0;
      for (int i = 0; i < list.size(); i += 4) {
         userScoreArray[count] = new UserScore(Integer.parseInt(list.get(i + 3)), list.get(i), Integer.parseInt(list.get(i + 1)), Integer.parseInt(list.get(i + 2)));
         count++;
      }
      return userScoreArray;
   }
   
   public void writeToTxtFile(ObservableList<UserScore> data) throws IOException
   {
      File file = new File("UserScores.txt");
      //Create the file
      file.createNewFile();
      WriteFile writeFile = new WriteFile(file, true);
      for (UserScore userScore : data) {
         writeFile.writeToFile(userScore.getUser());
         writeFile.writeToFile(Integer.toString(userScore.getNumGuesses()));
         writeFile.writeToFile(Integer.toString(userScore.getTimeSeconds()));
         writeFile.writeToFile(Integer.toString(userScore.getValue()));
      }
   }
   
   public ObservableList<UserScore> readFromTxtFile() throws IOException
   {
      List<String> list = Files.readAllLines(new File("UserScores.txt").toPath());

      ObservableList<UserScore> obs = FXCollections.observableArrayList();
      for (int i = 0; i < list.size(); i += 4) {
         obs.add(new UserScore(Integer.parseInt(list.get(i + 3)), list.get(i), Integer.parseInt(list.get(i + 1)), Integer.parseInt(list.get(i + 2))) {
            @Override
            public int compareTo(UserScore o) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
         });
      }

      userScoreTv.setItems(obs);
      return obs;
   }
   
   public class WriteFile {

      private File File;
      private boolean AppendToFile = false;

      public WriteFile(File file) {
         File = file;
      }

      public WriteFile(File file, boolean appendToFile) {
         File = file;
         AppendToFile = appendToFile;
      }

      public void writeToFile(String textLine) throws IOException {
         FileWriter write = new FileWriter(File, AppendToFile);
         PrintWriter printLine = new PrintWriter(write);
         printLine.printf("%s" + "%n", textLine);
         printLine.close();
      }
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
      scoreField.setText(output);
   }

   public void setHiScoreData(TableView tableView) {
      ObservableList<HiScore> data = FXCollections.observableArrayList(
              new HiScore("<8", "Disappointing, try again"),
              new HiScore("8-15", "Ok"),
              new HiScore("16+", "Great!")
      );
      addHiScoreTable(data, tableView);
   }

   public void setUserScoreData(TableView tableView) throws IOException {
      List<String> list = Files.readAllLines(new File("UserScores.txt").toPath());

      ObservableList<UserScore> obs = FXCollections.observableArrayList();
      for (int i = 0; i < list.size(); i += 4) {
         obs.add(new UserScore(Integer.parseInt(list.get(i + 3)), list.get(i), Integer.parseInt(list.get(i + 1)), Integer.parseInt(list.get(i + 2))) {
            @Override
            public int compareTo(UserScore o) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
         });
      }
      
      addUserScoreTable(obs, tableView, buttonPress);
   }

   public void setCellValueFactoryHiScoreTable() {
      value.setCellValueFactory(new PropertyValueFactory<HiScore, String>("value"));
      scoreRange.setCellValueFactory(new PropertyValueFactory<HiScore, String>("scoreRange"));
   }

   public void setCellValueFactoryUserScoreTable() {
      if (buttonPress == false) {
         cCorrectGuesses.setCellValueFactory(new PropertyValueFactory<>("numGuesses"));
         cUser.setCellValueFactory(new PropertyValueFactory<>("user"));
         cTime.setCellValueFactory(new PropertyValueFactory<>("timeSeconds"));
      } else {
         cScore.setText("Score");
         cScore.setCellValueFactory(new PropertyValueFactory<>("value"));
      }
   }

   public void addHiScoreTable(ObservableList<HiScore> data, TableView tableView) {
      setCellValueFactoryHiScoreTable();

      tableView.setItems(data);

      setRowFactory();
   }

   public void addUserScoreTable(ObservableList<UserScore> data, TableView tableView, boolean buttonPress) {
      setCellValueFactoryUserScoreTable();

      tableView.setItems(data);
   }
   
   // This is for changing the color of the score ranges table depending on how well you've done
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

   // A class implementing Comparable which means that filtering can be implemented
   public class UserScore implements Comparable<UserScore>
   {
      public SimpleIntegerProperty Value;
      public SimpleStringProperty User;
      public SimpleIntegerProperty NumGuesses;
      public SimpleIntegerProperty TimeSeconds;
      
      public UserScore() {
         this.Value = this.Value;
         this.User = this.User;
         this.NumGuesses = this.NumGuesses;
         this.TimeSeconds = this.TimeSeconds;
      }
      
      public UserScore(Integer value, String user, Integer numGuesses, Integer timeSeconds) {
         this.Value = new SimpleIntegerProperty(value);
         this.User = new SimpleStringProperty(user);
         this.NumGuesses = new SimpleIntegerProperty(numGuesses);
         this.TimeSeconds = new SimpleIntegerProperty(timeSeconds);
      }
      
      public int compareTo(UserScore other) {
         if (other.getNumGuesses() > this.NumGuesses.get()) {
            return -1;
         }

         if (other.getNumGuesses() < this.NumGuesses.get()) {
            return 1;
         }

         return 0;
      }
      
      public class GetComparator {
         public Comparator<UserScore> getComparator(String comparator) {
            if (comparator.equals("time")) {
               return new Comparator<UserScore>() {
                  public int compare(UserScore userScore1, UserScore userScore2) {
                     Integer id1 = userScore1.getTimeSeconds();
                     Integer id2 = userScore2.getTimeSeconds();
                     if (id1 == null) {
                        return id2 == null ? 0 : 1;
                     }
                     if (id2 == null) {
                        return -1;
                     }
                     return id1.compareTo(id2);
                  }
               };
            } else if (comparator.equals("score")) {
               return new Comparator<UserScore>() {
                  public int compare(UserScore userScore1, UserScore userScore2) {
                     Integer id1 = userScore1.getValue();
                     Integer id2 = userScore2.getValue();
                     if (id1 == null) {
                        return id2 == null ? 0 : 1;
                     }
                     if (id2 == null) {
                        return -1;
                     }
                     return id1.compareTo(id2);
                  }
               };
            }
            return null;
         }
      }
      
      public int getValue() {
         return Value.get();
      }

      public String getUser() {
         return User.get();
      }

      public Integer getNumGuesses() {
         return NumGuesses.get();
      }

      public Integer getTimeSeconds() {
         return TimeSeconds.get();
      }

      public void setValue(Integer value) {
         Value.set(value);
      }

      public void setUser(String user) {
         User.set(user);
      }

      public void setNumGuesses(Integer numGuesses) {
         NumGuesses.set(numGuesses);
      }

      public void setTimeSeconds(Integer time) {
         TimeSeconds.set(time);
      }
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
