package GUI;

import GUI.ConfirmBox;
import GUI.AlertBox;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class mainGUI extends Application {

	Stage window;
	Scene login, homePage;
	Button submitBtn;
	TableView<Drug> table;
	TextField drugIDInput;
	TextField drugNameInput;
	TextField drugDescriptionInput;
	TextField drugPriceInput;
	TextField drugQuantityInput;
	TextField drugSupplierInput;
	TextField drugExpyearInput;
	TextField drugExpmonthInput;
	TextField drugExpdayInput;
	
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
		Label usernameLabel = new Label("Username: ");
		TextField usernameInput = new TextField();
		Label passLabel = new Label("Password: ");
		TextField passInput = new TextField();
		
		//setting where our text fields and labels will be on the grid pane.
		GridPane.setConstraints(usernameLabel, 0, 0);
		GridPane.setConstraints(usernameInput, 1, 0);
		GridPane.setConstraints(passLabel, 0, 1);
		GridPane.setConstraints(passInput, 1, 1);
		
		//setting the prompts for our text fields and making sure the focus can't shift.
		usernameInput.setPromptText("Username");
		usernameInput.setFocusTraversable(false);
		passInput.setPromptText("Password");
		passInput.setFocusTraversable(false);
		
		//Creating the submit button for login screen and giving it functions.
		submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> {
			
			//if user inputs the wrong username and password, display alert and clear text fields
			if(!testUsrname.equals(usernameInput.getText()) && !testPasswrd.equals(passInput.getText())) {
				AlertBox.display("Login Error", "Incorrect username and password entered. Please retry.", "Retry");
				usernameInput.clear();
				passInput.clear();
			}
			
			//if user inputs the wrong password, display alert and clear text fields
			else if(!testPasswrd.equals(passInput.getText())) {
				AlertBox.display("Login Error", "Incorrect password entered. Please retry.", "Retry");
				usernameInput.clear();
				passInput.clear();
			}
			
			//if user inputs the wrong username, display alert and clear text fields
			else if(!testUsrname.equals(usernameInput.getText())) {
				AlertBox.display("Login Error", "Incorrect username entered. Please retry.", "Retry");
				usernameInput.clear();
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
		loginGrid.getChildren().addAll(usernameLabel, usernameInput, passLabel, passInput, submitBtn);
		
		//Putting together the login screen
		login = new Scene(loginGrid, 400, 300);
		
		//ID column
		TableColumn<Drug, String> IDColumn = new TableColumn<>("ID");
		IDColumn.setMinWidth(65);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//Name column
		TableColumn<Drug, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//description column
		TableColumn<Drug, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(200);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		//quantity column
		TableColumn<Drug, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(50);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		//price column
		TableColumn<Drug, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(50);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//supplier column
		TableColumn<Drug, String> supplierColumn = new TableColumn<>("Supplier");
		supplierColumn.setMinWidth(150);
		supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
		
		//exp year column
		TableColumn<Drug, Integer> expyearColumn = new TableColumn<>("Exp year");
		expyearColumn.setMinWidth(50);
		expyearColumn.setCellValueFactory(new PropertyValueFactory<>("expyear"));
		
		//exp month column
		TableColumn<Drug, Integer> expmonthColumn = new TableColumn<>("Exp month");
		expmonthColumn.setMinWidth(50);
		expmonthColumn.setCellValueFactory(new PropertyValueFactory<>("expmonth"));
		
		//exp day column
		TableColumn<Drug, Integer> expdayColumn = new TableColumn<>("Exp day");
		expdayColumn.setMinWidth(50);
		expdayColumn.setCellValueFactory(new PropertyValueFactory<>("expday"));
		
		//ID Input
		drugIDInput = new TextField();
		drugIDInput.setPromptText("ID number of drug");
		drugIDInput.setMaxWidth(200);
		
		//name Input
		drugNameInput = new TextField();
		drugNameInput.setPromptText("Name of drug");
		drugNameInput.setMaxWidth(200);
		
		//description Input
		drugDescriptionInput = new TextField();
		drugDescriptionInput.setPromptText("Description of drug");
		drugDescriptionInput.setMaxWidth(200);
		
		//price Input
		drugPriceInput = new TextField();
		drugPriceInput.setPromptText("Price of drug");
		drugPriceInput.setMaxWidth(200);
		
		//quantity Input
		drugQuantityInput = new TextField();
		drugQuantityInput.setPromptText("Quantity of drug");
		drugQuantityInput.setMaxWidth(200);
		
		//supplier Input
		drugSupplierInput = new TextField();
		drugSupplierInput.setPromptText("Supplier of drug");
		drugSupplierInput.setMaxWidth(200);
		
		//exp year Input
		drugExpyearInput = new TextField();
		drugExpyearInput.setPromptText("Exp year of drug");
		drugExpyearInput.setMaxWidth(200);
		
		//exp month Input
		drugExpmonthInput = new TextField();
		drugExpmonthInput.setPromptText("Exp month of drug");
		drugExpmonthInput.setMaxWidth(200);
		
		//exp day Input
		drugExpdayInput = new TextField();
		drugExpdayInput.setPromptText("Exp day of drug");
		drugExpdayInput.setMaxWidth(200);
		
		//Buttons to add or delete drugs
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(drugIDInput, drugNameInput, drugDescriptionInput, drugPriceInput, drugQuantityInput,
				drugSupplierInput, drugExpyearInput, drugExpmonthInput, drugExpdayInput, addButton, deleteButton);
		
		table = new TableView<>();
		table.setItems(getProduct());
		table.getColumns().addAll(IDColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn,
				supplierColumn, expyearColumn, expmonthColumn, expdayColumn);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		//Creating a border pane layout for our second scene
		BorderPane layout2 = new BorderPane();
		int width = ((int) Screen.getPrimary().getBounds().getWidth()) - 50;
		int height = ((int) Screen.getPrimary().getBounds().getHeight() - 100);
		layout2.setCenter(vBox);
		layout2.setBottom(hBox);
		homePage = new Scene(layout2, width, height);
		
		//Setting the login page as the first thing seen when program is run, and showing the window
		window.setScene(login);
		window.show();
	}
	
	//add button clicked
	public void addButtonClicked() {
		Drug drug = new Drug();
		drug.setId(drugIDInput.getText());
		drug.setName(drugNameInput.getText());
		drug.setDescription(drugDescriptionInput.getText());
		drug.setPrice(Double.parseDouble(drugPriceInput.getText()));
		drug.setQuantity(Integer.parseInt(drugQuantityInput.getText()));
		drug.setSupplier(drugSupplierInput.getText());
		drug.setExpyear(Integer.parseInt(drugExpyearInput.getText()));
		drug.setExpmonth(Integer.parseInt(drugExpmonthInput.getText()));
		drug.setExpday(Integer.parseInt(drugExpdayInput.getText()));
		table.getItems().add(drug);
		
		drugIDInput.clear();
		drugNameInput.clear();
		drugDescriptionInput.clear();
		drugPriceInput.clear();
		drugQuantityInput.clear();
		drugSupplierInput.clear();
		drugExpyearInput.clear();
		drugExpmonthInput.clear();
		drugExpdayInput.clear();
	}
	
	//delete button clicked
	public void deleteButtonClicked() {
		ObservableList<Drug> drugSelected, allDrugs;
		allDrugs = table.getItems();
		drugSelected = table.getSelectionModel().getSelectedItems();
		
		drugSelected.forEach(allDrugs::remove);
	}
	
	public ObservableList<Drug> getProduct(){
		ObservableList<Drug> drugs = FXCollections.observableArrayList();
		drugs.add(new Drug("ID_4598", "Albuterol Sulfate", "Bronchospasm treatment; Beta2 Agonists",
				200, 50.68, "CVS", 2026, 4, 15));
		drugs.add(new Drug("ID_1556", "Adderall", "central nervous system stimulant",
				450, 187.59, "CVS", 2026, 5, 20));
		drugs.add(new Drug("ID_7855", "Indocin", "nonsteroidal anti-inflammatory drug",
				70, 363.60, "CVS", 2026, 6, 30));
		drugs.add(new Drug("ID_1453", "Prozac", "Antidepressant; major depressive disorder treatment",
				95, 620.37, "CVS", 2026, 8, 19));
		drugs.add(new Drug("ID_5468", "Tylenol", "over-the-counter pain relief; Analgesics",
				250, 11.99, "CVS", 2026, 7, 17));
		return drugs;
	}
	
	
	
	
	//a program to verify close request from the user
	private void closeProgram() {
		boolean answer = ConfirmBox.display("Exit Page", "Are you sure you'd like to close this page?");
		if(answer)
			window.close();
	}

}
