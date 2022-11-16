package GUI;

import GUI.ConfirmBox;
import GUI.AlertBox;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class mainGUI extends Application {

	Stage window;
	Scene login, homePage;
	Button submitBtn;
	
	//just to launch out GUI
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		//test username and password for the sake of demonstration, will be removed later
		String testUsrname = "ManjiHui777";
		String testPasswrd = "GUIisCool251";
		
		window = arg0;
		window.setTitle("Login Screen");
		
		//to insure that the user meant to try and close the program instead of closing out on accident
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		//the type of layout that will be used for the login screen. It'll have 10 pixels of padding in all directions
		//and have 8 pixels of space between each object
		GridPane loginGrid = new GridPane();
		loginGrid.setPadding(new Insets(20, 20, 20, 20));
		loginGrid.setVgap(10);
		loginGrid.setHgap(10);
		
		//Creating the text fields for login screen and their labels.
		Label nameLabel = new Label("Username: ");
		TextField nameInput = new TextField();
		Label passLabel = new Label("Password: ");
		TextField passInput = new TextField();
		
		//setting where our text fields and labels will be on the grid pane.
		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(nameInput, 1, 0);
		GridPane.setConstraints(passLabel, 0, 1);
		GridPane.setConstraints(passInput, 1, 1);
		
		//setting the prompts for our text fields and making sure the focus can't shift.
		nameInput.setPromptText("Username");
		nameInput.setFocusTraversable(false);
		passInput.setPromptText("Password");
		passInput.setFocusTraversable(false);
		
		//Creating the submit button for login screen and giving it functions.
		submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> {
			
			//if user inputs the wrong username and password, display alert and clear text fields
			if(!testUsrname.equals(nameInput.getText()) && !testPasswrd.equals(passInput.getText())) {
				AlertBox.display("Login Error", "Incorrect username and password entered. Please retry.", "Retry");
				nameInput.clear();
				passInput.clear();
			}
			
			//if user inputs the wrong password, display alert and clear text fields
			else if(!testPasswrd.equals(passInput.getText())) {
				AlertBox.display("Login Error", "Incorrect password entered. Please retry.", "Retry");
				nameInput.clear();
				passInput.clear();
			}
			
			//if user inputs the wrong username, display alert and clear text fields
			else if(!testUsrname.equals(nameInput.getText())) {
				AlertBox.display("Login Error", "Incorrect username entered. Please retry.", "Retry");
				nameInput.clear();
				passInput.clear();
			}
			
			//if user inputs correct login information, send them to home page of program
			else {
				window.close();
				window.setTitle("Home Page");
				window.setScene(homePage);
				window.show();
			}
		});
		
		//setting where our button will be on the grid pane.
		GridPane.setConstraints(submitBtn, 1, 2);
		
		//adding all objects to the login grid pane in order.
		loginGrid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, submitBtn);
		
		//Putting together the login screen
		login = new Scene(loginGrid, 400, 300);
		
		//Creating a border pane layout for our second scene
		BorderPane layout2 = new BorderPane();
		int width = ((int) Screen.getPrimary().getBounds().getWidth()) - 50;
		int height = ((int) Screen.getPrimary().getBounds().getHeight() - 100);
		homePage = new Scene(layout2, width, height);
		
		//Setting the login page as the first thing seen when program is run, and showing the window
		window.setScene(login);
		window.show();
	}
	
	//a program to verify close request from the user
	private void closeProgram() {
		boolean answer = ConfirmBox.display("Exit Page", "Are you sure you'd like to close this page?");
		if(answer)
			window.close();
	}

}
