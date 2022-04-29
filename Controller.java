package application;





import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Question;
import model.QuestionBank;


public class Controller {

	private QuestionBank questionBank = new QuestionBank();
	private Question[] allQuestions = questionBank.getQuestions();
	private boolean pickAnswer = false;
	private int  questionNumber = 0;
	private int score = 0;
	
	public void initialize(){
		//System.out.println("erster Test");
		Question firstQuestion = allQuestions[0];
		frageLabel.setText(firstQuestion.getQuestionText());
		
		hBox.getChildren().add(new javafx.scene.shape.Rectangle(40, 20, Color.BLUE));
	}

	    @FXML
	    private Label frageLabel;

	    @FXML
	    private Button JaButton;

	    @FXML
	    private Button NeinButton;

	    @FXML
	    private HBox hBox;

	    @FXML
	    private Label counterLabel;

	    @FXML
	    private Label scoreNumberLabel;

	    @FXML
	    void JaNeinAction(ActionEvent event) {
	    	//System.out.println("Test");
	    	Button tappedButton = (Button) event.getSource();
	    	
	    	if(tappedButton.getId().equals("JaButton")) {
	    		//System.out.println("Ja Button tapped");
	    		pickAnswer = true;
	    		
	    	} else if(tappedButton.getId().equals("NeinButton")) {
	    		//System.out.println("Nein Button Tapped");
	    		pickAnswer = false;
	    	}
	    	checkAnswer();
	    	questionNumber++;
	    	nextQuestion();
	    }
	    
	    private void checkAnswer() {
	    	boolean correctAnswer = allQuestions[questionNumber].isAnswer();
	    	if(correctAnswer == pickAnswer) {
	    		score += 20;
	    		System.out.println("Richtig");
	    		createAlertBox("Richtig");
	    	} else {
	    		System.out.println("Falsch");
	    		createAlertBox("Falsch");
	    	}
	    }
	    
	 private void nextQuestion() {
		 if(questionNumber <= 9) {
			 frageLabel.setText(allQuestions[questionNumber].getQuestionText());
			 updateUI();
		 } else {
			 restart();
		 }
	 }
	   
	 private void updateUI() {
		 scoreNumberLabel.setText(Integer.toString(score));
		 counterLabel.setText("" + (questionNumber +1) + "/10");
		 double progressWidth = (hBox.getWidth() / 10) ;
		 hBox.getChildren().add(new javafx.scene.shape.Rectangle(progressWidth, 20, Color.BLUE));
	 }
	 
	 private void restart() {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("Glückwunsch!");
		 alert.setHeaderText("Glückwunsch zum Beenden des Quiz!");
		 alert.setContentText("Neustart?");
		 alert.showAndWait();
		 questionNumber = 0;
		 score = 0;
		 nextQuestion();
		 hBox.getChildren().clear();
	 }
	 
	 
	 private void createAlertBox(String text) {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setContentText(text);
		 alert.show();
		 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>()
		 {
			 @Override
			 public void handle(ActionEvent event) {
				 alert.close();
			 }
		 }));
		 timeline.setCycleCount(1);
		 timeline.play();
	 }
	 
	    
	}
	