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
	TableView<Drug> drugTable;
	TableView<Supplier> supplierTable;
	TextField drugIDInput;
	TextField drugNameInput;
	TextField drugDescriptionInput;
	TextField drugPriceInput;
	TextField drugQuantityInput;
	TextField drugSupplierInput;
	TextField drugExpyearInput;
	TextField drugExpmonthInput;
	TextField drugExpdayInput;
	TextField SupplierIDInput;
	TextField SupplierNameInput;
	TextField SupplierMerchandiseInput;
	TextField SupplierQuantityInput;
	TextField SupplierPriceInput;
	
	//just to launch out GUI
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		//test username and password for the sake of demonstration, will be removed later
		String testUsrname = "1";
		String testPasswrd = "1";
		
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
		TableColumn<Drug, String> drugIDColumn = new TableColumn<>("ID");
		drugIDColumn.setMinWidth(65);
		drugIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//Name column
		TableColumn<Drug, String> drugNameColumn = new TableColumn<>("Name");
		drugNameColumn.setMinWidth(150);
		drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//description column
		TableColumn<Drug, String> drugDescriptionColumn = new TableColumn<>("Description");
		drugDescriptionColumn.setMinWidth(200);
		drugDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		//quantity column
		TableColumn<Drug, Integer> drugQuantityColumn = new TableColumn<>("Quantity");
		drugQuantityColumn.setMinWidth(50);
		drugQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		//price column
		TableColumn<Drug, Double> drugPriceColumn = new TableColumn<>("Price");
		drugPriceColumn.setMinWidth(50);
		drugPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
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
		
		//Supplier ID column
		TableColumn<Supplier, Integer> SupplierIDColumn = new TableColumn<>("Supplier ID");
		SupplierIDColumn.setMinWidth(65);
		SupplierIDColumn.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
		
		//Supplier Name column
		TableColumn<Supplier, String> SupplierNameColumn = new TableColumn<>("Supplier Name");
		SupplierNameColumn.setMinWidth(200);
		SupplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
		
		//Supplier Name column
		TableColumn<Supplier, String> SupplierMerchandiseColumn = new TableColumn<>("Supplier Merchandise");
		SupplierMerchandiseColumn.setMinWidth(200);
		SupplierMerchandiseColumn.setCellValueFactory(new PropertyValueFactory<>("merchandise"));
		
		//Supplier Quantity column
		TableColumn<Supplier, Integer> SupplierQuantityColumn = new TableColumn<>("Supplier Quantity");
		SupplierQuantityColumn.setMinWidth(100);
		SupplierQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		
		//Supplier Price column
		TableColumn<Supplier, Integer> SupplierPriceColumn = new TableColumn<>("Supplier Price");
		SupplierPriceColumn.setMinWidth(100);
		SupplierPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
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
		
		//Supplier ID Input
		SupplierIDInput = new TextField();
		SupplierIDInput.setPromptText("ID of Supplier");
		SupplierIDInput.setMaxWidth(200);
		
		//Supplier Name Input
		SupplierNameInput = new TextField();
		SupplierNameInput.setPromptText("Name of Supplier");
		SupplierNameInput.setMaxWidth(200);
		
		//Supplier Name Input
		SupplierMerchandiseInput = new TextField();
		SupplierMerchandiseInput.setPromptText("Merchandise of Supplier");
		SupplierMerchandiseInput.setMaxWidth(200);
		
		//Supplier Quantity Input
		SupplierQuantityInput = new TextField();
		SupplierQuantityInput.setPromptText("Quantity of Supplier");
		SupplierQuantityInput.setMaxWidth(200);
		
		//Supplier Price Input
		SupplierPriceInput = new TextField();
		SupplierPriceInput.setPromptText("Price of Supplier");
		SupplierPriceInput.setMaxWidth(200);
		
		//Buttons to add or delete drugs
		Button drugAddButton = new Button("Add");
		drugAddButton.setOnAction(e -> drugAddButtonClicked());
		Button drugDeleteButton = new Button("Delete");
		drugDeleteButton.setOnAction(e -> {
			boolean delete = ConfirmBox.display("Confirm Deletion", "Are you sure you want to delete this product?");
			if(delete)
				drugDeleteButtonClicked();
		});
		
		//Buttons to add or delete suppliers
		Button supplierAddButton = new Button("Add");
		supplierAddButton.setOnAction(e -> supplierAddButtonClicked());
		Button supplierDeleteButton = new Button("Delete");
		supplierDeleteButton.setOnAction(e -> {
			boolean delete = ConfirmBox.display("Confirm Deletion", "Are you sure you want to delete this supplier?");
			if(delete)
				supplierDeleteClicked();
		});
		
		Label drugHBoxLabel = new Label("Add or Delete Drugs: ");
		HBox drugHBox = new HBox();
		drugHBox.setPadding(new Insets(10, 10, 10, 10));
		drugHBox.setSpacing(10);
		drugHBox.getChildren().addAll(drugHBoxLabel, drugIDInput, drugNameInput, drugDescriptionInput, drugPriceInput, drugQuantityInput,
				drugSupplierInput, drugExpyearInput, drugExpmonthInput, drugExpdayInput, drugAddButton, drugDeleteButton);
		
		drugTable = new TableView<>();
		drugTable.setItems(getDrug());
		drugTable.getColumns().addAll(drugIDColumn, drugNameColumn, drugDescriptionColumn, drugPriceColumn, drugQuantityColumn,
				supplierColumn, expyearColumn, expmonthColumn, expdayColumn);
		
		Label drugVBoxLabel = new Label("Drug Table");
		VBox drugVBox = new VBox();
		drugVBox.setPadding(new Insets(10, 10, 10, 10));
		drugVBox.setSpacing(10);
		drugVBox.getChildren().addAll(drugVBoxLabel, drugTable);
		
		Label supplierHBoxLabel = new Label("Add or Delete Suppiers: ");
		HBox supplierHBox = new HBox();
		supplierHBox.setPadding(new Insets(10, 10, 10, 10));
		supplierHBox.setSpacing(10);
		supplierHBox.getChildren().addAll(supplierHBoxLabel, SupplierIDInput, SupplierNameInput, SupplierMerchandiseInput, SupplierPriceInput, SupplierQuantityInput,
				supplierAddButton, supplierDeleteButton);
		
		supplierTable = new TableView<>();
		supplierTable.setItems(getSupplier());
		supplierTable.getColumns().addAll(SupplierIDColumn, SupplierNameColumn, SupplierMerchandiseColumn, SupplierPriceColumn, SupplierQuantityColumn);
		
		Label supplierVBoxLabel = new Label("Supplier Table");
		VBox supplierVBox = new VBox();
		supplierVBox.setPadding(new Insets(10, 10, 10, 10));
		supplierVBox.setSpacing(10);
		supplierVBox.getChildren().addAll(supplierVBoxLabel, supplierTable);
		
		VBox combinedVBox = new VBox();
		combinedVBox.setPadding(new Insets(10, 10, 10, 10));
		combinedVBox.setSpacing(10);
		combinedVBox.getChildren().addAll(drugVBox, supplierVBox);
		
		VBox combinedHBox = new VBox();
		combinedHBox.setPadding(new Insets(10, 10, 10, 10));
		combinedHBox.setSpacing(10);
		combinedHBox.getChildren().addAll(drugHBox, supplierHBox);
		
		
		//Creating a border pane layout for our second scene
		BorderPane layout2 = new BorderPane();
		int width = ((int) Screen.getPrimary().getBounds().getWidth()) - 50;
		int height = ((int) Screen.getPrimary().getBounds().getHeight() - 100);
		layout2.setCenter(combinedVBox);
		layout2.setBottom(combinedHBox);
		homePage = new Scene(layout2, width, height);
		
		//Setting the login page as the first thing seen when program is run, and showing the window
		window.setScene(login);
		window.show();
	}
	
	//add button clicked
	public void drugAddButtonClicked() {
		
		if((Validator.validation("Integer", drugIDInput.getText())) &&
		(Validator.validation("Double", drugPriceInput.getText())) &&
		(Validator.validation("Integer", drugQuantityInput.getText())) &&
		(Validator.validation("Integer", drugExpyearInput.getText())) &&
		(Validator.validation("Integer", drugExpmonthInput.getText())) &&
		(Validator.validation("Integer", drugExpdayInput.getText()))) {
			Drug drug = new Drug();
			drug.setId(Integer.parseInt(drugIDInput.getText()));
			drug.setName(drugNameInput.getText());
			drug.setDescription(drugDescriptionInput.getText());
			drug.setPrice(Double.parseDouble(drugPriceInput.getText()));
			drug.setQuantity(Integer.parseInt(drugQuantityInput.getText()));
			drug.setSupplier(drugSupplierInput.getText());
			drug.setExpyear(Integer.parseInt(drugExpyearInput.getText()));
			drug.setExpmonth(Integer.parseInt(drugExpmonthInput.getText()));
			drug.setExpday(Integer.parseInt(drugExpdayInput.getText()));
			drugTable.getItems().add(drug);
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
		
		else {
			AlertBox.display("Error in input", "Sorry but one of the values you inputed was of the wrong type, please try again.", "Try Again");
		}
		
	}
	
	//add button clicked
	public void supplierAddButtonClicked() {
		
		if((Validator.validation("Integer", SupplierIDInput.getText())) &&
		(Validator.validation("Integer", SupplierQuantityInput.getText())) &&
		(Validator.validation("Integer", SupplierPriceInput.getText()))) {
			Supplier supplier = new Supplier();
			supplier.setSupplierID(Integer.parseInt(SupplierIDInput.getText()));
			supplier.setSupplierName(SupplierNameInput.getText());
			supplier.setMerchandise(SupplierMerchandiseInput.getText());
			supplier.setQuantity(Integer.parseInt(SupplierQuantityInput.getText()));
			supplier.setPrice(Integer.parseInt(SupplierPriceInput.getText()));
			supplierTable.getItems().add(supplier);
			SupplierIDInput.clear();
			SupplierNameInput.clear();
			SupplierMerchandiseInput.clear();
			SupplierQuantityInput.clear();
			SupplierPriceInput.clear();
		}
		
		else {
			AlertBox.display("Error in input", "Sorry but one of the values you inputed was of the wrong type, please try again.", "Try Again");
		}
		
	}
	
	//delete button clicked
	public void drugDeleteButtonClicked() {
		ObservableList<Drug> drugSelected, allDrugs;
		allDrugs = drugTable.getItems();
		drugSelected = drugTable.getSelectionModel().getSelectedItems();
		
		drugSelected.forEach(allDrugs::remove);
	}
	
	//delete button clicked
	public void supplierDeleteClicked() {
		ObservableList<Supplier> supplierSelected, allSuppliers;
		allSuppliers = supplierTable.getItems();
		supplierSelected = supplierTable.getSelectionModel().getSelectedItems();
		
		supplierSelected.forEach(allSuppliers::remove);
	}
	
	public ObservableList<Drug> getDrug(){
		ObservableList<Drug> drugs = FXCollections.observableArrayList();
		drugs.add(new Drug(4598, "Albuterol Sulfate", "Bronchospasm treatment; Beta2 Agonists",
				200, 50.68, "CVS", 2026, 4, 15));
		drugs.add(new Drug(1556, "Adderall", "central nervous system stimulant",
				450, 187.59, "CVS", 2026, 5, 20));
		drugs.add(new Drug(7855, "Indocin", "nonsteroidal anti-inflammatory drug",
				70, 363.60, "CVS", 2026, 6, 30));
		drugs.add(new Drug(1453, "Prozac", "Antidepressant; major depressive disorder treatment",
				95, 620.37, "CVS", 2026, 8, 19));
		drugs.add(new Drug(5468, "Tylenol", "over-the-counter pain relief; Analgesics",
				250, 11.99, "CVS", 2026, 7, 17));
		return drugs;
	}
	
	public ObservableList<Supplier> getSupplier(){
		ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
		suppliers.add(new Supplier(4598, "CVS", "Bronchospasm treatment; Beta2 Agonists",
				200, 50));
		suppliers.add(new Supplier(1556, "CVS", "central nervous system stimulant",
				450, 187));
		suppliers.add(new Supplier(7855, "CVS", "nonsteroidal anti-inflammatory drug",
				70, 363));
		suppliers.add(new Supplier(1453, "CVS", "Antidepressant; major depressive disorder treatment",
				95, 620));
		suppliers.add(new Supplier(5468, "CVS", "over-the-counter pain relief; Analgesics",
				250, 11));
		return suppliers;
	}
	
	
	
	
	//a program to verify close request from the user
	private void closeProgram() {
		boolean answer = ConfirmBox.display("Exit Page", "Are you sure you'd like to close this page?");
		if(answer)
			window.close();
	}

}
