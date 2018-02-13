/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXLogoQuiz;

import JavaFXLogoQuiz.FXMLController.UserName;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
            scoreButton.setVisible(buttonPress);

            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            properties.setProperty("useSSL", "false");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logo_quiz", properties);
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "logo_quiz", null);
            setUserScoreData(userScoreTv, con, tables);
            
            displayScore.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (buttonPress == false) {
                        try {
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logo_quiz", properties);
                            DatabaseMetaData dbm = con.getMetaData();
                            ResultSet tables = dbm.getTables(null, null, "logo_quiz", null);
                            buttonPress = true;
                            scoreButton.setVisible(buttonPress);
                            formatTextField();
                            setHiScoreData(tableView);
                            setUserScoreData(userScoreTv, con, tables);
                            con.close();
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
                        DriverManager.deregisterDriver(driver);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    changeScene(root);
                }
            });

            // The following three buttons are filter buttons
            guessButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ObservableList<UserScore> userScoreObsList = setFilter(con);
                        userScoreTv.setItems(userScoreObsList.sorted());
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            timeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ObservableList<UserScore> userScoreObsList = setFilter(con);
                        UserScore userScore = new UserScore();
                        UserScore.GetComparator comparator = userScore.new GetComparator();
                        userScoreTv.setItems(userScoreObsList.sorted(comparator.getComparator("time")));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            scoreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ObservableList<UserScore> userScoreObsList = setFilter(con);
                        UserScore userScore = new UserScore();
                        UserScore.GetComparator comparator = userScore.new GetComparator();
                        userScoreTv.setItems(userScoreObsList.sorted(comparator.getComparator("score")));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLControllerScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Set filter method
    public ObservableList<UserScore> setFilter(Connection con) throws IOException, SQLException {
        ObservableList<UserScore> data = FXCollections.observableArrayList();

        ResultSet rs = QuerySelect(con);

        return GetData(rs, data);
    }
    
    public static ObservableList<UserScore> Sort(ObservableList<UserScore> data) {
        return Sort(data);
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

    public void setUserScoreData(TableView tableView, Connection con, ResultSet tables) throws SQLException {
        ObservableList<UserScore> data = FXCollections.observableArrayList();

        if (tables.next()) {
            // Table exists
            if (buttonPress == true) {
                String querySel = "SELECT user, num_correct_guesses, time, score FROM logo_quiz";
                java.sql.PreparedStatement st = con.prepareStatement(querySel);

                ResultSet rs = st.executeQuery(querySel);

                data = GetData(rs, data);
            } else {
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
        addUserScoreTable(data, tableView);
    }

    public void CreateTable(Connection con) throws SQLException {
        String sql = "CREATE TABLE logo_quiz "
                + "(id INTEGER not NULL AUTO_INCREMENT, "
                + " user VARCHAR(255), "
                + " num_correct_guesses INT, "
                + " time VARCHAR(255), "
                + " score INT, "
                + " PRIMARY KEY ( id ))";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }

    public ObservableList<UserScore> GetData(ResultSet rs, ObservableList<UserScore> data) throws SQLException {
        while (rs.next()) {
            data.add(new UserScore(rs.getInt(4), rs.getString(1), rs.getInt(2), rs.getInt(3)));
        }
        return data;
    }

    public ResultSet QuerySelect(Connection con) throws SQLException {
        String querySel = "SELECT user, num_correct_guesses, time, score FROM logo_quiz";
        java.sql.PreparedStatement st = con.prepareStatement(querySel);

        ResultSet rs = null;
        return rs = st.executeQuery(querySel);
    }

    public void QueryInsert(Connection con) throws SQLException {
        String query = "insert into logo_quiz (user, num_correct_guesses, time, score)"
                + " values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, UserName.User);
        preparedStmt.setInt(2, Total.NumGuesses);
        preparedStmt.setInt(3, Integer.parseInt(timeTakenField.getText()));
        preparedStmt.setInt(4, Total.Score);

        preparedStmt.execute();
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

    public void addUserScoreTable(ObservableList<UserScore> data, TableView tableView) {
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

    // A class implementing Comparable which means that filtering can be implemented
    public class UserScore implements Comparable<UserScore> {

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
