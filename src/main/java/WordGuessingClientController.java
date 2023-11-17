import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class WordGuessingClientController implements Initializable{
	
	@FXML
	private VBox clientPortInputVBox;
	
	@FXML
	private TextField clientPortInput;
	
	@FXML
	private Button clientPortInputBtn;
	
	@FXML
	private BorderPane gameBorderPane;
	
	@FXML
	private Button fruitCategoryChoiceBtn;
	
	@FXML
	private Button animalCategoryChoiceBtn;
	
	@FXML
	private Button countryCategoryChoiceBtn;
	
	@FXML
	private TextField userInput;
	
	@FXML
	private TextField output;
	
	@FXML
	private TextField NumGuessesText;
	
	@FXML 
	private TextField CategoriesCompleted;
	
	@FXML
	private Button guessBtn;
	
	@FXML
	private TextField failedAttempts;
	
	@FXML
	private HBox categoryChooseBox;
	
	@FXML
	private HBox restartOrExitHBox;
	
	@FXML
	private TextField result;
	
	@FXML
	private Button restartBtn;
	
	@FXML
	private Button exitBtn;
	
	
	
	
	public String categoryChoice = "";
	public String letterGuess = "";
	
	WordGuessingClient clientConnection;
	public static GameObject wg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void getPort(ActionEvent e) throws IOException {
		int port = Integer.parseInt(clientPortInput.getText());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameScene.fxml"));
		gameBorderPane = loader.load();
		
		WordGuessingClientController clientController = loader.getController();
		
		gameBorderPane.getStylesheets().add("/Styles/gameStyle.css");
		
		clientPortInputVBox.getScene().setRoot(gameBorderPane);
		
		clientController.connectToServerOnPort(port);
	}
	
	public void connectToServerOnPort(int port) {
		clientConnection = new WordGuessingClient(data -> {
			
			Platform.runLater(()->{
				output.setText(data.toString());
			});
			
		}, guess -> {
			
			Platform.runLater(()->{
				NumGuessesText.setText(guess.toString());
			});
		}, category -> {
			
			Platform.runLater(()->{
				CategoriesCompleted.setText(category.toString());
				animalCategoryChoiceBtn.setDisable(false);
				fruitCategoryChoiceBtn.setDisable(false);
				countryCategoryChoiceBtn.setDisable(false);
				guessBtn.setDisable(true);
				
			});
		}, attempt -> {
			
			Platform.runLater(()->{
				failedAttempts.setText(attempt.toString());
				animalCategoryChoiceBtn.setDisable(false);
				fruitCategoryChoiceBtn.setDisable(false);
				countryCategoryChoiceBtn.setDisable(false);
				guessBtn.setDisable(true);
			});
		},  delete -> {
			
			Platform.runLater(()->{
				if (delete.toString().equals("animal")) {
					categoryChooseBox.getChildren().remove(animalCategoryChoiceBtn);
				} else if (delete.toString().equals("fruit")) {
					categoryChooseBox.getChildren().remove(fruitCategoryChoiceBtn);
				} else if (delete.toString().equals("country")) {
					categoryChooseBox.getChildren().remove(countryCategoryChoiceBtn);
				}
			});
		}, restart -> {
			
			Platform.runLater(()->{
				if (restart.toString().equals("won")) {
					result.setText("Congrats! You won!");
					
				} else if (restart.toString().equals("lost")) {
					result.setText("Sorry you lost. :(");
				}
			});
		}, port);
		
		clientConnection.start();
		wg = clientConnection.gameData;
	}
	
	public void categoryChoiceAnimal(ActionEvent e) throws Exception{
		animalCategoryChoiceBtn.setDisable(true);
		fruitCategoryChoiceBtn.setDisable(true);
		countryCategoryChoiceBtn.setDisable(true);
		guessBtn.setDisable(false);
		
		wg.setCategoryChoice("animal");
		clientConnection.send(wg);
	}
	
	public void categoryChoiceFruit(ActionEvent e) throws Exception{
		animalCategoryChoiceBtn.setDisable(true);
		fruitCategoryChoiceBtn.setDisable(true);
		countryCategoryChoiceBtn.setDisable(true);
		guessBtn.setDisable(false);
		
		wg.setCategoryChoice("fruit");
		clientConnection.send(wg);
	}
	
	public void categoryChoiceCountry(ActionEvent e) throws Exception{
		animalCategoryChoiceBtn.setDisable(true);
		fruitCategoryChoiceBtn.setDisable(true);
		countryCategoryChoiceBtn.setDisable(true);
		guessBtn.setDisable(false);
		
		wg.setCategoryChoice("country");
		clientConnection.send(wg);
	}
	
	public void guess(ActionEvent e) throws Exception {
		wg.setCurrGuess(userInput.getText());
		userInput.clear();
		clientConnection.send(wg);
	}
	
	public void newCategory(ActionEvent e) throws Exception {
		animalCategoryChoiceBtn.setDisable(false);
		fruitCategoryChoiceBtn.setDisable(false);
		countryCategoryChoiceBtn.setDisable(false);
	}

	public void restart() {
		resetBtns();
		userInput.clear();
		output.setText("Output:");
		NumGuessesText.setText("Num Guesses: ");
		CategoriesCompleted.setText("Categories Completed:");
		failedAttempts.setText("Failed Attempts:");
		result.setText("Result:");
		wg = new GameObject();
	}
	
	public void exit() {
		Platform.exit();
	}
	
	public void resetBtns() {
		animalCategoryChoiceBtn.setDisable(false);
		fruitCategoryChoiceBtn.setDisable(false);
		countryCategoryChoiceBtn.setDisable(false);
		guessBtn.setDisable(true);
		
		for (String i : wg.getCategoriesCompleted()) {
			if (i.equals("animal")) {
				categoryChooseBox.getChildren().add(animalCategoryChoiceBtn);
			} else if (i.equals("fruit")) {
				categoryChooseBox.getChildren().add(fruitCategoryChoiceBtn);
			} else if (i.equals("country")) {
				categoryChooseBox.getChildren().remove(countryCategoryChoiceBtn);
			}
		}
	}

}
