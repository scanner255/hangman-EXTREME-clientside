import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class WordGuessingClientGUI extends Application {
	
	WordGuessingClient clientConnection;
	WordGuessingClientController clientController;
	
	@Override
	public void start(Stage primaryStage) {
		 try {
	            // Read file fxml and draw interface.
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/welcomeScene.fxml"));
	            Parent root = loader.load();
	            clientController = loader.getController();
	 
	            primaryStage.setTitle("Word Guessing Game!");
             Scene s1 = new Scene(root, 800,800);
             s1.getStylesheets().add("/Styles/welcomeStyle.css");
	            primaryStage.setScene(s1);
	            primaryStage.show();
	         
	        } catch(Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

