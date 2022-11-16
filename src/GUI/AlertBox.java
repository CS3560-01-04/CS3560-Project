package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	//A method that displays some sort of alert window that the user must interact with first before continuing
	public static void display(String title, String message, String close) {
		Stage window = new Stage();
		
		//Creating the window and setting qualities such as title, minimum width and height, and modality
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(533);
		window.setMinHeight(300);
		
		//creating the message for the user and the button to use to close the alert
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button(close);
		closeButton.setOnAction(e -> window.close());
		
		//Creating the basic layout of the alert
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//setting the scene of the window, showing the scene to the user, and making the window not disappear until interacted with
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
