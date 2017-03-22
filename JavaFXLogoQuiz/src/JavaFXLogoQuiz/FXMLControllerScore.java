/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import JavaFXLogoQuiz.FXMLController.UserName;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
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
    private TableView<?> userScoreTv;
    
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
    
    public void setUser(FXMLController.UserName userName) 
    {
        this.UserName = userName;
    }
    
    public void setTotal(FXMLControllerGame.Total total) {
        this.Total = total;
    }
    
    public FXMLControllerGame.TimeSeconds TimeSeconds;
    
    public void setTimeSeconds(FXMLControllerGame.TimeSeconds timeSeconds)
    {
        this.TimeSeconds = timeSeconds;
    }
    
    public boolean buttonPress = false;
    
    public boolean alreadyInserted = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            numGuessesField.setText(Integer.toString(Total.NumGuesses));
            timeTakenField.setText(Integer.toString(TimeSeconds.get()));
            
            setUserScoreData(userScoreTv);
            
            displayScore.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (buttonPress == false)
                    {
                        try {
                            buttonPress = true;
                            formatTextField();
                            setHiScoreData(tableView);
                            setUserScoreData(userScoreTv);
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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
            
            guessButton.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    String query = "SELECT user, num_correct_guesses, time, score FROM logo_quiz ORDER BY score";
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void setUserScoreData(TableView tableView) throws SQLException {
        ObservableList<UserScore> data = FXCollections.observableArrayList();
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logo_quiz", properties);
        DriverManager.deregisterDriver(driver);

        DatabaseMetaData dbm = con.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, "logo_quiz", null);
        
        if (tables.next()) 
        {
            // Table exists
            if(buttonPress == true)
            {
                String querySel = "SELECT user, num_correct_guesses, time, score FROM logo_quiz";
                java.sql.PreparedStatement st = con.prepareStatement(querySel);

                ResultSet rs = st.executeQuery(querySel);

                data = GetData(rs, data);
            }
            else
            {
                QueryInsert(con);

                ResultSet rs = QuerySelect(con);    
                
                data = GetData(rs, data);
            }
        } else {
            
            // Table does not exist
            CreateTable(con);
            
            QueryInsert(con);
            
            ResultSet rs = QuerySelect(con);

            data = GetData(rs, data);
        }
        addUserScoreTable(data, tableView, buttonPress);
        
        con.close ();
    }
    
    public void CreateTable(Connection con) throws SQLException
    {
        String sql = "CREATE TABLE logo_quiz " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " user VARCHAR(255), " + 
                    " num_correct_guesses INT, " + 
                    " time VARCHAR(255), " +
                    " score INT, " + 
                    " PRIMARY KEY ( id ))";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }
    
    public ObservableList<UserScore> GetData(ResultSet rs, ObservableList<UserScore> data) throws SQLException
    {
        while (rs.next()) {
            data.add(new UserScore(rs.getString(4), rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return data;
    }
    
    public ResultSet QuerySelect(Connection con) throws SQLException
    {
        String querySel = "SELECT user, num_correct_guesses, time, score FROM logo_quiz";
        java.sql.PreparedStatement st = con.prepareStatement(querySel);

        ResultSet rs = null;
        return rs = st.executeQuery(querySel);
    }
    
    public void QueryInsert(Connection con) throws SQLException
    {
        String query = "insert into logo_quiz (user, num_correct_guesses, time, score)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, UserName.User);
        preparedStmt.setInt(2, Total.NumGuesses);
        preparedStmt.setInt(3, Integer.parseInt(timeTakenField.getText()));
        preparedStmt.setInt(4, Total.Score);

        preparedStmt.execute();
    }
    
    public void setCellValueFactoryHiScoreTable()
    {
        value.setCellValueFactory(new PropertyValueFactory<HiScore, String>("value"));
        scoreRange.setCellValueFactory(new PropertyValueFactory<HiScore, String>("scoreRange"));
    }
    
    public void setCellValueFactoryUserScoreTable()
    {
        if(buttonPress == false)
        {
            cCorrectGuesses.setCellValueFactory(new PropertyValueFactory<>("numGuesses"));
            cUser.setCellValueFactory(new PropertyValueFactory<>("user"));
            cTime.setCellValueFactory(new PropertyValueFactory<>("timeSeconds"));
        }
        else
        {
            cScore.setText("Score");
            cScore.setCellValueFactory(new PropertyValueFactory<>("value"));
        }
    }
    
    public void addHiScoreTable(ObservableList<HiScore> data, TableView tableView)
    {
        setCellValueFactoryHiScoreTable();
        
        tableView.setItems(data);
        
        setRowFactory();
    }
    
    public void addUserScoreTable(ObservableList<UserScore> data, TableView tableView, boolean buttonPress)
    {
        setCellValueFactoryUserScoreTable();
        
        tableView.setItems(data);
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
    
    public class UserScore {
        
        public SimpleStringProperty Value;
        public SimpleStringProperty User;
        public SimpleStringProperty NumGuesses;
        public SimpleStringProperty TimeSeconds;


        public UserScore(String value, String user, String numGuesses, String timeSeconds) {
            this.Value = new SimpleStringProperty(value);
            this.User = new SimpleStringProperty(user);
            this.NumGuesses = new SimpleStringProperty(numGuesses);
            this.TimeSeconds = new SimpleStringProperty(timeSeconds);
        }

        public String getValue() {
            return Value.get();
        }
        
        public String getUser() {
            return User.get();
        }

        public String getNumGuesses() {
            return NumGuesses.get();
        }
        public String getTimeSeconds() {
            return TimeSeconds.get();
        }

        public void setValue(String value) {
            Value.set(value);
        }
        
        public void setUser(String user) {
            User.set(user);
        }

        public void setNumGuesses(String numGuesses) {
            NumGuesses.set(numGuesses);
        }
        
        public void setTimeSeconds(String time) {
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
