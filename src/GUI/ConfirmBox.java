package GUI;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ConfirmBox {
	
	static boolean answer;
	
	//a method to display an interactive confirmation window for the user to verify what they want
	public static boolean display(String title, String message) {
		
		//Creating the window the user will see and setting features of the window, along with creating a label to display
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(200);
		Label label = new Label();
		label.setText(message);
		
		//Making sure the user can't close the confirm box without confirming
		window.setOnCloseRequest(e -> {
			e.consume();
			refuseClosure();
		});
		
		//The two buttons the user will see and be capable of interacting with
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		//giving the two buttons functions
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		//Creating the layout of the confirm box and setting up the window, including setting the scene and showing the window
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		//returning the users decision;
		return answer;
	}
	
	//a method to tell the user they must confirm whether or not they meant what they did
	private static void refuseClosure() {
		AlertBox.display("Can't close", "You can not close the window without confirming", "Return");
	}
}
