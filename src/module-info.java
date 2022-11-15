module projectStuff {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens GUI to javafx.graphics, javafx.fxml;
}
