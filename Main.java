package application;

import application.ConfirmBox;
import application.AlertBox;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Main extends Application {

	Stage window;
	Scene login, productPage, supplierPage, customerPage, addProduct, addSupplier, addCustomer,
		viewProduct, viewSupplier, viewCustomer;
	Button submitBtn;
	TableView<Drug> table = new TableView<>();
	TableView<Supplier> table2 = new TableView<>();
	TableView<Customer> table3 = new TableView<>();
	TextField drugIDInput;
	TextField drugNameInput;
	TextField drugDescriptionInput;
	TextField drugPriceInput;
	TextField drugQuantityInput;
	TextField drugSupplierInput;
	TextField drugExpyearInput;
	TextField drugExpmonthInput;
	TextField drugExpdayInput;
	private ObservableList<Drug> drugs;
	
	
	
	//just to launch out GUI
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage arg0) throws Exception {
		
		//test username and password for the sake of demonstration, will be removed later
		String testUsrname = "Brandon";
		String testPasswrd = "bruhmoment";
		
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
		PasswordField passInput = new PasswordField();
		
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
				window.setTitle("Products");
				window.setScene(productPage);
				window.show();
				lowStock();
			}
		});
		
		//setting where our button will be on the grid pane.
		GridPane.setConstraints(submitBtn, 1, 2);
		
		//adding all objects to the login grid pane in order.
		loginGrid.getChildren().addAll(usernameLabel, usernameInput, passLabel, passInput, submitBtn);
		
		//Putting together the login screen
		login = new Scene(loginGrid, 400, 300);
		
		/*
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 * PRODUCT TABLE
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 */
		
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

		ChoiceBox<String> tableDropDown = new ChoiceBox<String>();
		tableDropDown.getItems().addAll("Products", "Suppliers", "Customers");
		tableDropDown.setValue("Products");
		
		tableDropDown.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			switch (tableDropDown.getValue())
	        {
	            case "Products":
	            	window.setTitle("Products");
	            	window.setScene(productPage);
	            	window.show();
	                break;
	            case "Suppliers":
	            	tableDropDown.setValue("Products");
	            	window.setTitle("Suppliers");
	            	window.setScene(supplierPage);
	            	window.show();
	                break;
	            case "Customers":
	            	tableDropDown.setValue("Products");
	            	window.setTitle("Customers");
	            	window.setScene(customerPage);
	            	window.show();
	                break;
	        }
		});
		
		ChoiceBox<String> searchDropDown = new ChoiceBox<String>();
		searchDropDown.getItems().addAll("Product ID", "Product Name", "Description");
		searchDropDown.setValue("ProductID");
		
		TextField searchInput = new TextField();
		searchInput.setPromptText("Search for product");
		searchInput.setFocusTraversable(false);
		searchInput.setMinWidth(Screen.getPrimary().getBounds().getWidth() - 500);
		
		ObservableList<Drug> drugs = FXCollections.observableArrayList();
		Main pro = new Main();
		int rowCount = 0;
		int[] intValues = new int[5];
		String[] sValues = new String[5];
		double price = 0.0;
		
		for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)); i++)
		{
			rowCount++;
			intValues[0] = Integer.parseInt(pro.fetchData("product_id", rowCount, 0));
			sValues[0] = pro.fetchData("product_name", rowCount, 0);
			sValues[1] = pro.fetchData("descript", rowCount, 0);
			intValues[1] = Integer.parseInt(pro.fetchData("quantity", rowCount, 0));
			price = Double.parseDouble(pro.fetchData("price", rowCount, 0));
			sValues[2] = pro.fetchData("supplier", rowCount, 0);
			intValues[2] = Integer.parseInt(pro.fetchData("exp_year", rowCount, 0));
			intValues[3] = Integer.parseInt(pro.fetchData("exp_month", rowCount, 0));
			intValues[4] = Integer.parseInt(pro.fetchData("exp_day", rowCount, 0));	
			
			drugs.add(new Drug(intValues[0], sValues[0], sValues[1],
					intValues[1], price, sValues[2], intValues[2], 
					intValues[3], intValues[4]));
		}
		
		FilteredList<Drug> filteredList = new FilteredList<>(drugs);//Pass the data to a filtered list
		table.setEditable(true);
		table.setItems(filteredList);//Set the table's items using the filtered list
        table.getColumns().addAll(IDColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn,
				supplierColumn, expyearColumn, expmonthColumn, expdayColumn);
        
		searchInput.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredList.setPredicate(Drug -> {
            	
            	if(newValue == null || newValue.isEmpty())
            		return true;
            	
            	String lowerCase = newValue.toLowerCase();
            	
            	switch (searchDropDown.getValue())//Switch on choiceBox value
                {
                    case "ProductID":
                    	if(String.valueOf(Drug.getId()).indexOf(newValue) !=-1)
                    		return true;
                        break;
                    case "Product Name":
                    	if(Drug.getName().toLowerCase().indexOf(lowerCase) !=-1)
                    		return true;
                        break;
                    case "Description":
                        if(Drug.getDescription().toLowerCase().indexOf(lowerCase) !=-1)
                        	return true;
                        break;
                }
				return false;
            	});
            });
		
		ChoiceBox<String> supplierDropDown = new ChoiceBox<String>();
		supplierDropDown.setValue(pro.fetchData("supplier_name", 1, -1));
		for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, -1)); i++)
			supplierDropDown.getItems().add(pro.fetchData("supplier_name", i, -1));
		
		Stage addStage = new Stage();
		
		//Buttons to add or delete drugs
		Button addButton = new Button("Confirm");
		addButton.setOnAction(e -> {
			if((Validator.validation("Integer", drugIDInput.getText())) &&
					(Validator.validation("Double", drugPriceInput.getText())) &&
					(Validator.validation("Integer", drugQuantityInput.getText())) &&
					(Validator.validation("Integer", drugExpyearInput.getText())) &&
					(Validator.validation("Integer", drugExpmonthInput.getText())) &&
					(Validator.validation("Integer", drugExpdayInput.getText()))) {
						drugs.add(new Drug(Integer.parseInt(drugIDInput.getText()), drugNameInput.getText(),
								drugDescriptionInput.getText(), Integer.parseInt(drugQuantityInput.getText()), 
								Double.parseDouble(drugPriceInput.getText()), supplierDropDown.getValue(),
								Integer.parseInt(drugExpyearInput.getText()), Integer.parseInt(drugExpmonthInput.getText()),
								Integer.parseInt(drugExpdayInput.getText())));
						
						Main add = new Main();
						add.addData(Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)) + 1, Integer.parseInt(drugIDInput.getText()), 
								drugNameInput.getText(), drugDescriptionInput.getText(),
								Double.parseDouble(drugPriceInput.getText()), Integer.parseInt(drugQuantityInput.getText()),
								supplierDropDown.getValue(), Integer.parseInt(drugExpyearInput.getText()),
								Integer.parseInt(drugExpmonthInput.getText()), Integer.parseInt(drugExpdayInput.getText()));
						
						drugIDInput.clear();
						drugNameInput.clear();
						drugDescriptionInput.clear();
						drugPriceInput.clear();
						drugQuantityInput.clear();
						drugSupplierInput.clear();
						drugExpyearInput.clear();
						drugExpmonthInput.clear();
						drugExpdayInput.clear();
						
						addStage.close();
					}
					
					else {
						AlertBox.display("Error in input", "Sorry but one of the values you inputed was of the wrong type, please try again.", "Try Again");
					}
				});
				Button deleteButton = new Button("Delete");
				deleteButton.setOnAction(e -> {
					boolean delete = ConfirmBox.display("Confirm Deletion", "Are you sure you want to delete this product?");
					if(delete)
					{
						Main remove = new Main();
						remove.deleteData(Integer.parseInt(pro.fetchData("row_num", -2, table.getSelectionModel().getSelectedItem().getId())));
						table.getSelectionModel().getSelectedItems().forEach(drugs::remove);	
					}
				});
				
				Button cancelButton = new Button("Cancel");
				cancelButton.setOnAction(e -> {addStage.close();});
				
				GridPane addGrid = new GridPane();
				addGrid.setPadding(new Insets(20, 20, 20, 20));
				addGrid.setVgap(10);
				addGrid.setHgap(10);
				
				Label idLabel = new Label("ID:");
				Label nameLabel = new Label("Name:");
				Label descLabel = new Label("Description:");
				Label priceLabel = new Label("Price:");
				Label quantityLabel = new Label("Quantity:");
				Label supplierLabel = new Label("Supplier:");
				Label yearLabel = new Label("Exp Year:");
				Label monthLabel = new Label("Exp Month:");
				Label dayLabel = new Label("Exp Day:");

				GridPane.setConstraints(idLabel, 0, 0);
				GridPane.setConstraints(drugIDInput, 1, 0);
				GridPane.setConstraints(nameLabel, 0, 1);
				GridPane.setConstraints(drugNameInput, 1, 1);
				GridPane.setConstraints(descLabel, 0, 2);
				GridPane.setConstraints(drugDescriptionInput, 1, 2);
				GridPane.setConstraints(priceLabel, 0, 3);
				GridPane.setConstraints(drugPriceInput, 1, 3);
				GridPane.setConstraints(quantityLabel, 0, 4);
				GridPane.setConstraints(drugQuantityInput, 1, 4);
				GridPane.setConstraints(supplierLabel, 0, 5);
				GridPane.setConstraints(supplierDropDown, 1, 5);
				GridPane.setConstraints(yearLabel, 0, 6);
				GridPane.setConstraints(drugExpyearInput, 1, 6);
				GridPane.setConstraints(monthLabel, 0, 7);
				GridPane.setConstraints(drugExpmonthInput, 1, 7);
				GridPane.setConstraints(dayLabel, 0, 8);
				GridPane.setConstraints(drugExpdayInput, 1, 8);
				GridPane.setConstraints(addButton, 1, 9);
				GridPane.setConstraints(cancelButton, 1, 10);
				
				addGrid.getChildren().addAll(idLabel, nameLabel, descLabel, priceLabel,
												quantityLabel, supplierLabel, yearLabel,
												monthLabel, dayLabel, drugIDInput, drugNameInput, 
												drugDescriptionInput, drugPriceInput, drugQuantityInput,
												supplierDropDown, drugExpyearInput, drugExpmonthInput, 
												drugExpdayInput, addButton, cancelButton);
				
				addProduct = new Scene(addGrid, 300, 400);
		        Button addDrug = new Button("Add New Product");
		        addDrug.setOnAction(e -> {	
		        	addStage.setTitle("Add New Product");
		            addStage.setScene(addProduct);
		            addStage.show();
		        });
				
		        Stage viewStage = new Stage();
				
				Button cancelViewButton = new Button("Cancel");
				cancelViewButton.setOnAction(e -> {viewStage.close();});
				
				GridPane viewGrid = new GridPane();
				viewGrid.setPadding(new Insets(20, 20, 20, 20));
				viewGrid.setVgap(20);
				viewGrid.setHgap(30);
				
				Label idDrugLabel = new Label("");
				Label nameDrugLabel = new Label("");
				Label descDrugLabel = new Label("");
				Label priceDrugLabel = new Label("");
				Label quantityDrugLabel = new Label("");
				Label supplierDrugLabel = new Label("");
				Label yearDrugLabel = new Label("");
				Label monthDrugLabel = new Label("");
				Label dayDrugLabel = new Label("");
				
				descDrugLabel.setWrapText(true);
				
				GridPane.setConstraints(idLabel, 0, 0);
				GridPane.setConstraints(idDrugLabel, 1, 0);
				GridPane.setConstraints(nameLabel, 0, 1);
				GridPane.setConstraints(nameDrugLabel, 1, 1);
				GridPane.setConstraints(descLabel, 0, 2);
				GridPane.setConstraints(descDrugLabel, 1, 2);
				GridPane.setConstraints(priceLabel, 0, 3);
				GridPane.setConstraints(priceDrugLabel, 1, 3);
				GridPane.setConstraints(quantityLabel, 0, 4);
				GridPane.setConstraints(quantityDrugLabel, 1, 4);
				GridPane.setConstraints(supplierLabel, 0, 5);
				GridPane.setConstraints(supplierDrugLabel, 1, 5);
				GridPane.setConstraints(yearLabel, 0, 6);
				GridPane.setConstraints(yearDrugLabel, 1, 6);
				GridPane.setConstraints(monthLabel, 0, 7);
				GridPane.setConstraints(monthDrugLabel, 1, 7);
				GridPane.setConstraints(dayLabel, 0, 8);
				GridPane.setConstraints(dayDrugLabel, 1, 8);
				GridPane.setConstraints(cancelViewButton, 0, 9);
				
				viewGrid.getChildren().addAll(idLabel, nameLabel, descLabel, 
						priceLabel, quantityLabel, supplierLabel, yearLabel,
						monthLabel, dayLabel, idDrugLabel, nameDrugLabel, descDrugLabel,
						priceDrugLabel, quantityDrugLabel, supplierDrugLabel, yearDrugLabel,
						monthDrugLabel, dayDrugLabel, cancelViewButton);
						
				
				viewProduct = new Scene(viewGrid, 550, 400);
				
		table.setRowFactory( tv -> {
		    TableRow<Drug> drugRow = new TableRow<>();
		    drugRow.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! drugRow.isEmpty()) ) {
		            Drug rowData = drugRow.getItem();
		            
		            idDrugLabel.setText(String.valueOf(rowData.getId()));
					nameDrugLabel.setText(rowData.getName());
					descDrugLabel.setText(rowData.getDescription());
					priceDrugLabel.setText(String.valueOf(rowData.getPrice()));
					quantityDrugLabel.setText(String.valueOf(rowData.getQuantity()));
					supplierDrugLabel.setText(rowData.getSupplier());
					yearDrugLabel.setText(String.valueOf(rowData.getExpyear()));
					monthDrugLabel.setText(String.valueOf(rowData.getExpmonth()));
					dayDrugLabel.setText(String.valueOf(rowData.getExpday()));

		            viewStage.setTitle("View Product");
		            viewStage.setScene(viewProduct);
		            viewStage.show();
		        }
		    });
		    return drugRow;
		});
		        
		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> {
			drugs.removeAll(drugs);
			int rowCount2 = 0;
			double price2 = 0.0;
			
			for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)); i++)
			{
				rowCount2++;
				if(rowCount2 == Integer.parseInt(pro.fetchData("row_num", rowCount2, 0)))
				{
					intValues[0] = Integer.parseInt(pro.fetchData("product_id", rowCount2, 0));
					sValues[0] = pro.fetchData("product_name", rowCount2, 0);
					sValues[1] = pro.fetchData("descript", rowCount2, 0);
					intValues[1] = Integer.parseInt(pro.fetchData("quantity", rowCount2, 0));
					price2 = Double.parseDouble(pro.fetchData("price", rowCount2, 0));
					sValues[2] = pro.fetchData("supplier", rowCount2, 0);
					intValues[2] = Integer.parseInt(pro.fetchData("exp_year", rowCount2, 0));
					intValues[3] = Integer.parseInt(pro.fetchData("exp_month", rowCount2, 0));
					intValues[4] = Integer.parseInt(pro.fetchData("exp_day", rowCount2, 0));
				}
				
				drugs.add(new Drug(intValues[0], sValues[0], sValues[1],
						intValues[1], price2, sValues[2], intValues[2], 
						intValues[3], intValues[4]));
			}
			lowStock();
		});
		
		/*
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 * SUPPLIER TABLE
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 */
		
		//ID column
		TableColumn<Supplier, Integer> supIDColumn = new TableColumn<>("ID");
		supIDColumn.setMinWidth(65);
		supIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//Name column
		TableColumn<Supplier, String> supNameColumn = new TableColumn<>("Name");
		supNameColumn.setMinWidth(150);
		supNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//Email column
		TableColumn<Supplier, String> supEmailColumn = new TableColumn<>("Email");
		supEmailColumn.setMinWidth(150);
		supEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		//Phone column
		TableColumn<Supplier, String> supPhoneColumn = new TableColumn<>("Phone");
		supPhoneColumn.setMinWidth(150);
		supPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		//ID Input
		TextField supIDInput = new TextField();
		supIDInput.setPromptText("ID number of Supplier");
		supIDInput.setMaxWidth(200);
		
		//name Input
		TextField supNameInput = new TextField();
		supNameInput.setPromptText("Name of Supplier");
		supNameInput.setMaxWidth(200);
		
		//description Input
		TextField supEmailInput = new TextField();
		supEmailInput.setPromptText("Email of Supplier");
		supEmailInput.setMaxWidth(200);
		
		//price Input
		TextField supPhoneInput = new TextField();
		supPhoneInput.setPromptText("Phone # of Suppleir");
		supPhoneInput.setMaxWidth(200);
		
		ChoiceBox<String> searchDropDown2 = new ChoiceBox<String>();
		searchDropDown2.getItems().addAll("Supplier ID", "Supplier Name", "Supplier Email", "Supplier Phone");
		searchDropDown2.setValue("Supplier ID");
		
		TextField searchInput2 = new TextField();
		searchInput2.setPromptText("Search for product");
		searchInput2.setFocusTraversable(false);
		searchInput2.setMinWidth(Screen.getPrimary().getBounds().getWidth() - 500);
		
		ChoiceBox<String> tableDropDown2 = new ChoiceBox<String>();
		tableDropDown2.getItems().addAll("Products", "Suppliers", "Customers");
		tableDropDown2.setValue("Suppliers");
		
		tableDropDown2.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			switch (tableDropDown2.getValue())
	        {
	            case "Products":
	            	tableDropDown2.setValue("Suppliers");
	            	window.setTitle("Products");
	            	window.setScene(productPage);
	            	window.show();
	                break;
	            case "Suppliers":
	            	window.setTitle("Suppliers");
	            	window.setScene(supplierPage);
	            	window.show();
	                break;
	            case "Customers":
	            	tableDropDown2.setValue("Suppliers");
	            	window.setTitle("Customers");
	            	window.setScene(customerPage);
	            	window.show();
	                break;
	        }
		});
		
		ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
		int rowSupCount = 0;
		for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, -1)); i++)
		{
			rowSupCount++;
			intValues[0] = Integer.parseInt(pro.fetchData("supplier_id", rowSupCount, -1));
			sValues[0] = pro.fetchData("supplier_name", rowSupCount, -1);
			sValues[1] = pro.fetchData("supplier_email", rowSupCount, -1);
			sValues[2] = pro.fetchData("supplier_phonenum", rowSupCount, -1);
			
			suppliers.add(new Supplier(intValues[0], sValues[0], sValues[1], sValues[2]));
		}
		
		FilteredList<Supplier> filteredList2 = new FilteredList<>(suppliers);//Pass the data to a filtered list
		table2.setEditable(true);
		table2.setItems(filteredList2);//Set the table's items using the filtered list
        table2.getColumns().addAll(supIDColumn, supNameColumn, supEmailColumn, supPhoneColumn);
        
		searchInput2.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredList2.setPredicate(Supplier -> {
            	
            	if(newValue == null || newValue.isEmpty())
            		return true;
            	
            	String lowerCase = newValue.toLowerCase();
            	
            	switch (searchDropDown2.getValue())//Switch on choiceBox value
                {
                    case "Supplier ID":
                    	if(String.valueOf(Supplier.getId()).indexOf(newValue) !=-1)
                    		return true;
                        break;
                    case "Supplier Name":
                    	if(Supplier.getName().toLowerCase().indexOf(lowerCase) !=-1)
                    		return true;
                        break;
                    case "Supplier Email":
                        if(Supplier.getEmail().toLowerCase().indexOf(lowerCase) !=-1)
                        	return true;
                        break;
                    case "Supplier Phone":
                    	if(Supplier.getPhone().indexOf(newValue) !=-1)
                        	return true;
                        break;
                }
				return false;
            	});
            });
        
		Stage addSupStage = new Stage();
		
		//Buttons to add or delete drugs
		Button addSupButton = new Button("Confirm");
		addSupButton.setOnAction(e -> {
			if((Validator.validation("Integer", supIDInput.getText()))){
						suppliers.add(new Supplier(Integer.parseInt(supIDInput.getText()), supNameInput.getText(),
								supEmailInput.getText(), supPhoneInput.getText()));
						
						Main add = new Main();
						add.addSupData(Integer.parseInt(pro.fetchData("COUNT(*)", -1, -1)) + 1, Integer.parseInt(supIDInput.getText()),
								supNameInput.getText(), supEmailInput.getText(), supPhoneInput.getText());
						
						supIDInput.clear();
						supNameInput.clear();
						supEmailInput.clear();
						supPhoneInput.clear();
						
						addSupStage.close();
					}
					
					else {
						AlertBox.display("Error in input", "Sorry but one of the values you inputed was of the wrong type, please try again.", "Try Again");
					}
				});
				Button deleteSupButton = new Button("Delete");
				deleteSupButton.setOnAction(e -> {
					boolean delete = ConfirmBox.display("Confirm Deletion", "Are you sure you want to delete this product?");
					if(delete)
					{
						Main remove = new Main();
						remove.deleteSupData(Integer.parseInt(pro.fetchData("row_num", -3, table2.getSelectionModel().getSelectedItem().getId())));
						table2.getSelectionModel().getSelectedItems().forEach(suppliers::remove);	
					}
				});
				
				Button cancelSupButton = new Button("Cancel");
				cancelSupButton.setOnAction(e -> {addSupStage.close();});
				
				GridPane addSupGrid = new GridPane();
				addSupGrid.setPadding(new Insets(20, 20, 20, 20));
				addSupGrid.setVgap(10);
				addSupGrid.setHgap(10);
				
				Label idSupLabel = new Label("ID:");
				Label nameSupLabel = new Label("Name:");
				Label emailLabel = new Label("Email:");
				Label phoneLabel = new Label("Phone:");


				GridPane.setConstraints(idSupLabel, 0, 0);
				GridPane.setConstraints(supIDInput, 1, 0);
				GridPane.setConstraints(nameSupLabel, 0, 1);
				GridPane.setConstraints(supNameInput, 1, 1);
				GridPane.setConstraints(emailLabel, 0, 2);
				GridPane.setConstraints(supEmailInput, 1, 2);
				GridPane.setConstraints(phoneLabel, 0, 3);
				GridPane.setConstraints(supPhoneInput, 1, 3);
				GridPane.setConstraints(addSupButton, 1, 4);
				GridPane.setConstraints(cancelSupButton, 1, 5);
				
				addSupGrid.getChildren().addAll(idSupLabel, nameSupLabel, emailLabel, phoneLabel,
												supIDInput, supNameInput, supEmailInput, 
												supPhoneInput, addSupButton, cancelSupButton);

				Scene addSupScene = new Scene(addSupGrid, 300, 400);
		        Button addSup = new Button("Add New Supplier");
		        addSup.setOnAction(e -> {	
		        	addSupStage.setTitle("Add New Supplier");
		            addSupStage.setScene(addSupScene);
		            addSupStage.show();
		        });
		        
		        Stage viewSupStage = new Stage();
				
				Button cancelViewSupButton = new Button("Cancel");
				cancelViewSupButton.setOnAction(e -> {viewSupStage.close();});
				
				GridPane viewSupGrid = new GridPane();
				viewSupGrid.setPadding(new Insets(20, 20, 20, 20));
				viewSupGrid.setVgap(20);
				viewSupGrid.setHgap(30);
				
				Label supIdLabel = new Label("");
				Label supNameLabel = new Label("");
				Label supEmailLabel = new Label("");
				Label supPhoneLabel = new Label("");
				
				GridPane.setConstraints(idSupLabel, 0, 0);
				GridPane.setConstraints(supIdLabel, 1, 0);
				GridPane.setConstraints(nameSupLabel, 0, 1);
				GridPane.setConstraints(supNameLabel, 1, 1);
				GridPane.setConstraints(emailLabel, 0, 2);
				GridPane.setConstraints(supEmailLabel, 1, 2);
				GridPane.setConstraints(phoneLabel, 0, 3);
				GridPane.setConstraints(supPhoneLabel, 1, 3);
				GridPane.setConstraints(cancelViewSupButton, 0, 4);
				
				viewSupGrid.getChildren().addAll(idSupLabel, nameSupLabel, emailLabel, 
						phoneLabel, supIdLabel, supNameLabel, supEmailLabel,
						supPhoneLabel, cancelViewSupButton);
						
				
				viewSupplier = new Scene(viewSupGrid, 550, 200);
				
		table2.setRowFactory( tv -> {
		    TableRow<Supplier> SupRow = new TableRow<>();
		    SupRow.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! SupRow.isEmpty()) ) {
		            Supplier rowSupData = SupRow.getItem();
		            
		            supIdLabel.setText(String.valueOf(rowSupData.getId()));
					supNameLabel.setText(rowSupData.getName());
					supEmailLabel.setText(rowSupData.getEmail());
					supPhoneLabel.setText(String.valueOf(rowSupData.getPhone()));

		            viewSupStage.setTitle("Edit Product");
		            viewSupStage.setScene(viewSupplier);
		            viewSupStage.show();
		        }
		    });
		    return SupRow;
		});
		        
		Button refreshSup = new Button("Refresh");
		refreshSup.setOnAction(e -> {
			suppliers.removeAll(suppliers);
			
			int rowCount3 = 0;
			for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, -1)); i++)
			{
				rowCount3++;
				intValues[0] = Integer.parseInt(pro.fetchData("supplier_id", rowCount3, -1));
				sValues[0] = pro.fetchData("supplier_name", rowCount3, -1);
				sValues[1] = pro.fetchData("supplier_email", rowCount3, -1);
				sValues[2] = pro.fetchData("supplier_phonenum", rowCount3, -1);
				
				suppliers.add(new Supplier(intValues[0], sValues[0], sValues[1], sValues[2]));
			}
		});
		
		
		/*
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 * CUSTOMER TABLE
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 */
		
		
		//id column
		TableColumn<Customer, String> cusIDColumn = new TableColumn<>("ID");
		cusIDColumn.setMinWidth(65);
		cusIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//fname column
		TableColumn<Customer, String> fnameColumn = new TableColumn<>("FName");
		fnameColumn.setMinWidth(150);
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
		
		//lname column
		TableColumn<Customer, String> lnameColumn = new TableColumn<>("LName");
		lnameColumn.setMinWidth(200);
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
		
		//email column
		TableColumn<Customer, String> cusEmailColumn = new TableColumn<>("Email");
		cusEmailColumn.setMinWidth(50);
		cusEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		//phone column
		TableColumn<Customer, String> cusPhoneColumn = new TableColumn<>("Phone");
		cusPhoneColumn.setMinWidth(50);
		cusPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		//prescription column
		TableColumn<Customer, String> prescriptionColumn = new TableColumn<>("Prescription");
		prescriptionColumn.setMinWidth(150);
		prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("prescription"));
		
		//ID Input
		TextField CustomerIDInput = new TextField();
		CustomerIDInput.setPromptText("ID number of Customer");
		CustomerIDInput.setMaxWidth(200);
		
		//fname Input
		TextField CustomerFnameInput = new TextField();
		CustomerFnameInput.setPromptText("Fname of Customer");
		CustomerFnameInput.setMaxWidth(200);
		
		//Lname Input
		TextField CustomerLnameInput = new TextField();
		CustomerLnameInput.setPromptText("Lname of Customer");
		CustomerLnameInput.setMaxWidth(200);
		
		//Email Input
		TextField CustomerEmailInput = new TextField();
		CustomerEmailInput.setPromptText("Email of Customer");
		CustomerEmailInput.setMaxWidth(200);
		
		//Phone Input
		TextField CustomerPhoneInput = new TextField();
		CustomerPhoneInput.setPromptText("Phone of Customer");
		CustomerPhoneInput.setMaxWidth(200);
		
		//Prescription Input
		TextField CustomerPrescriptionInput = new TextField();
		CustomerPrescriptionInput.setPromptText("Prescription of Customer");
		CustomerPrescriptionInput.setMaxWidth(200);
		

		ChoiceBox<String> tableDropDown3 = new ChoiceBox<String>();
		tableDropDown3.getItems().addAll("Products", "Suppliers", "Customers");
		tableDropDown3.setValue("Customers");
		
		tableDropDown3.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			switch (tableDropDown3.getValue())
	        {
	            case "Products":
	            	tableDropDown3.setValue("Customers");
	            	window.setTitle("Products");
	            	window.setScene(productPage);
	            	window.show();
	                break;
	            case "Suppliers":
	            	tableDropDown3.setValue("Customers");
	            	window.setTitle("Suppliers");
	            	window.setScene(supplierPage);
	            	window.show();
	                break;
	            case "Customers":
	            	window.setTitle("Customers");
	            	window.setScene(customerPage);
	            	window.show();
	                break;
	        }
		});
		
		ChoiceBox<String> searchDropDown3 = new ChoiceBox<String>();
		searchDropDown3.getItems().addAll("Customer ID", "Name", "Email", "Phone", "Prescription");
		searchDropDown3.setValue("Customer ID");
		
		TextField searchInput3 = new TextField();
		searchInput3.setPromptText("Search for Customer");
		searchInput3.setFocusTraversable(false);
		searchInput3.setMinWidth(Screen.getPrimary().getBounds().getWidth() - 500);
		
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		int rowCount4 = 0;
		
		for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, -2)); i++)
		{
			rowCount4++;
			intValues[0] = Integer.parseInt(pro.fetchData("customer_id", rowCount4, -2));
			sValues[0] = pro.fetchData("first_name", rowCount4, -2);
			sValues[1] = pro.fetchData("last_name", rowCount4, -2);
			sValues[2] = pro.fetchData("customer_email", rowCount4, -2);
			sValues[3] = pro.fetchData("customer_phonenum", rowCount4, -2);
			sValues[4] = pro.fetchData("prescription", rowCount4, -2);
			
			customers.add(new Customer(intValues[0], sValues[0], sValues[1],
					sValues[2], sValues[3], sValues[4]));
		}
		
		
		FilteredList<Customer> filteredList3 = new FilteredList<>(customers);//Pass the data to a filtered list
		table3.setEditable(true);
		table3.setItems(filteredList3);//Set the table3's items using the filtered list
        table3.getColumns().addAll(cusIDColumn, fnameColumn, lnameColumn,
        		cusEmailColumn, cusPhoneColumn, prescriptionColumn);
        
		searchInput3.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredList3.setPredicate(Customer -> {
            	
            	if(newValue == null || newValue.isEmpty())
            		return true;
            	
            	String lowerCase = newValue.toLowerCase();
            	
            	switch (searchDropDown3.getValue())//Switch on choiceBox value
                {
                    case "Customer ID":
                    	if(String.valueOf(Customer.getId()).indexOf(newValue) !=-1)
                    		return true;
                        break;
                    case "Name":
                    	if(Customer.getFname().toLowerCase().indexOf(lowerCase) !=-1 || Customer.getLname().toLowerCase().indexOf(lowerCase) !=-1)
                    		return true;
                        break;
                    case "Email":
                        if(Customer.getEmail().toLowerCase().indexOf(lowerCase) !=-1)
                        	return true;
                    case "Phone":
                        if(Customer.getPhone().indexOf(lowerCase) !=-1)
                        	return true;
                    case "Prescription":
                        if(Customer.getPrescription().toLowerCase().indexOf(lowerCase) !=-1)
                        	return true;
                        break;
                }
				return false;
            	});
            });
        
		Stage addCusStage = new Stage();
		
		//Buttons to add or delete Customers
		Button addCusButton = new Button("Confirm");
		addCusButton.setOnAction(e -> {
			if((Validator.validation("Integer", CustomerIDInput.getText()))) {
						customers.add(new Customer(Integer.parseInt(CustomerIDInput.getText()), CustomerFnameInput.getText(),
								CustomerLnameInput.getText(), CustomerEmailInput.getText(), CustomerPhoneInput.getText(),
								CustomerPrescriptionInput.getText()));
						
						Main add = new Main();
						add.addCusData(Integer.parseInt(pro.fetchData("COUNT(*)", -1, -2)) + 1, Integer.parseInt(CustomerIDInput.getText()), CustomerFnameInput.getText(),
						CustomerLnameInput.getText(), CustomerEmailInput.getText(), CustomerPhoneInput.getText(), CustomerPrescriptionInput.getText());
						
						CustomerIDInput.clear();
						CustomerFnameInput.clear();
						CustomerLnameInput.clear();
						CustomerEmailInput.clear();
						CustomerPhoneInput.clear();
						
						addCusStage.close();
					}
					
					else {
						AlertBox.display("Error in input", "Sorry but one of the values you inputed was of the wrong type, please try again.", "Try Again");
					}
				});
				Button deleteCusButton = new Button("Delete");
				deleteCusButton.setOnAction(e -> {
					boolean delete = ConfirmBox.display("Confirm Deletion", "Are you sure you want to delete this customer?");
					if(delete)
					{
						Main remove = new Main();
						remove.deleteCusData(Integer.parseInt(pro.fetchData("row_num", -4, table3.getSelectionModel().getSelectedItem().getId())));
						table3.getSelectionModel().getSelectedItems().forEach(customers::remove);	
					}
				});
				
				Button cancelCusButton = new Button("Cancel");
				cancelCusButton.setOnAction(e -> {addCusStage.close();});
				
				GridPane addCusGrid = new GridPane();
				addCusGrid.setPadding(new Insets(20, 20, 20, 20));
				addCusGrid.setVgap(10);
				addCusGrid.setHgap(10);
				
				Label cusIdLabel = new Label("ID:");
				Label FnameLabel = new Label("Fname:");
				Label LnameLabel = new Label("Lname:");
				Label cusEmailLabel = new Label("Email:");
				Label cusPhoneLabel = new Label("Phone:");
				Label PrescriptionLabel = new Label("Prescription:");

				GridPane.setConstraints(cusIdLabel, 0, 0);
				GridPane.setConstraints(CustomerIDInput, 1, 0);
				GridPane.setConstraints(FnameLabel, 0, 1);
				GridPane.setConstraints(CustomerFnameInput, 1, 1);
				GridPane.setConstraints(LnameLabel, 0, 2);
				GridPane.setConstraints(CustomerLnameInput, 1, 2);
				GridPane.setConstraints(cusEmailLabel, 0, 3);
				GridPane.setConstraints(CustomerEmailInput, 1, 3);
				GridPane.setConstraints(cusPhoneLabel, 0, 4);
				GridPane.setConstraints(CustomerPhoneInput, 1, 4);
				GridPane.setConstraints(PrescriptionLabel, 0, 5);
				GridPane.setConstraints(CustomerPrescriptionInput, 1, 5);
				GridPane.setConstraints(addCusButton, 1, 6);
				GridPane.setConstraints(cancelCusButton, 1, 7);
				
				addCusGrid.getChildren().addAll(cusIdLabel, FnameLabel, LnameLabel, cusEmailLabel,
												cusPhoneLabel, PrescriptionLabel, CustomerIDInput, 
												CustomerFnameInput, CustomerLnameInput, CustomerEmailInput, 
												CustomerPhoneInput, CustomerPrescriptionInput, addCusButton, 
												cancelCusButton);

				addCustomer = new Scene(addCusGrid, 300, 400);
		        Button addCus = new Button("Add New Customer");
		        addCus.setOnAction(e -> {	
		        	addCusStage.setTitle("Add New Customer");
		            addCusStage.setScene(addCustomer);
		            addCusStage.show();
		        });
		
		        Stage viewCusStage = new Stage();
				
				Button cancelViewCusButton = new Button("Cancel");
				cancelViewCusButton.setOnAction(e -> {viewCusStage.close();});
				
				GridPane viewCusGrid = new GridPane();
				viewCusGrid.setPadding(new Insets(20, 20, 20, 20));
				viewCusGrid.setVgap(20);
				viewCusGrid.setHgap(30);
				
				Label idCusLabel = new Label("");
				Label fnameLabel = new Label("");
				Label lnameLabel = new Label("");
				Label emailCusLabel = new Label("");
				Label phoneCusLabel = new Label("");
				Label prescriptionLabel = new Label("");
				
				GridPane.setConstraints(idCusLabel, 1, 0);
				GridPane.setConstraints(cusIdLabel, 0, 0);
				GridPane.setConstraints(fnameLabel, 1, 1);
				GridPane.setConstraints(FnameLabel, 0, 1);
				GridPane.setConstraints(lnameLabel, 1, 2);
				GridPane.setConstraints(LnameLabel, 0, 2);
				GridPane.setConstraints(emailCusLabel, 1, 3);
				GridPane.setConstraints(cusEmailLabel, 0, 3);
				GridPane.setConstraints(phoneCusLabel, 1, 4);
				GridPane.setConstraints(cusPhoneLabel, 0, 4);
				GridPane.setConstraints(prescriptionLabel, 1, 5);
				GridPane.setConstraints(PrescriptionLabel, 0, 5);
				GridPane.setConstraints(cancelViewCusButton, 0, 6);
				
				viewCusGrid.getChildren().addAll(idCusLabel, fnameLabel, lnameLabel, 
						emailCusLabel, phoneCusLabel, prescriptionLabel, cusIdLabel, 
						FnameLabel, LnameLabel, cusEmailLabel, cusPhoneLabel, 
						PrescriptionLabel, cancelViewCusButton);
						
				
				viewCustomer = new Scene(viewCusGrid, 450, 400);
				
		table3.setRowFactory( tv -> {
		    TableRow<Customer> cusRow = new TableRow<>();
		    cusRow.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! cusRow.isEmpty()) ) {
		            Customer rowCusData = cusRow.getItem();
		            
		            idCusLabel.setText(String.valueOf(rowCusData.getId()));
					fnameLabel.setText(rowCusData.getFname());
					lnameLabel.setText(rowCusData.getLname());
					emailCusLabel.setText(String.valueOf(rowCusData.getEmail()));
					phoneCusLabel.setText(String.valueOf(rowCusData.getPhone()));
					prescriptionLabel.setText(rowCusData.getPrescription());
					
		            viewCusStage.setTitle("View Customer");
		            viewCusStage.setScene(viewCustomer);
		            viewCusStage.show();
		        }
		    });
		    return cusRow;
		});
		        
		Button refreshCustomers = new Button("Refresh");
		refreshCustomers.setOnAction(e -> {
			customers.removeAll(customers);
			int rowCount5 = 0;
			
			for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, -2)); i++)
			{
				rowCount5++;
				intValues[0] = Integer.parseInt(pro.fetchData("customer_id", rowCount5, -2));
				sValues[0] = pro.fetchData("first_name", rowCount5, -2);
				sValues[1] = pro.fetchData("last_name", rowCount5, -2);
				sValues[2] = pro.fetchData("customer_email", rowCount5, -2);
				sValues[3] = pro.fetchData("customer_phonenum", rowCount5, -2);
				sValues[4] = pro.fetchData("prescription", rowCount5, -2);
				
				customers.add(new Customer(intValues[0], sValues[0], sValues[1],
						sValues[2], sValues[3], sValues[4]));
			}
		});

		
		/*
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 * LAYOUT SCENE BUILDER
		 * -------------------------------------------------------------------------------------------------------------------------------------------
		 */
		
		HBox topSearch = new HBox(5);
		topSearch.setPadding(new Insets(5, 5, 5, 5));
		topSearch.getChildren().addAll(tableDropDown, searchDropDown, searchInput, refresh);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(addDrug, deleteButton);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		//Creating a border pane layout for our second scene
		BorderPane layout2 = new BorderPane();
		int width = ((int) Screen.getPrimary().getBounds().getWidth()) - 50;
		int height = ((int) Screen.getPrimary().getBounds().getHeight() - 100);
		layout2.setTop(topSearch);
		layout2.setCenter(vBox);
		layout2.setBottom(hBox);
		productPage = new Scene(layout2, width, height);
		
		//scene for Supplier Table
		HBox topSearch2 = new HBox(5);
		topSearch2.setPadding(new Insets(5, 5, 5, 5));
		topSearch2.getChildren().addAll(tableDropDown2, searchDropDown2, searchInput2, refreshSup);
		
		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10, 10, 10, 10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(addSup, deleteSupButton);
		
		VBox vBox2 = new VBox();
		vBox2.getChildren().addAll(table2);
		
		BorderPane layout3 = new BorderPane();
		layout3.setTop(topSearch2);
		layout3.setCenter(vBox2);
		layout3.setBottom(hBox2);
		supplierPage = new Scene(layout3, width, height);
		
		
		//scene for Customer Table
		HBox topSearch3 = new HBox(5);
		topSearch3.setPadding(new Insets(5, 5, 5, 5));
		topSearch3.getChildren().addAll(tableDropDown3, searchDropDown3, searchInput3, refreshCustomers);
		
		HBox hBox3 = new HBox();
		hBox3.setPadding(new Insets(10, 10, 10, 10));
		hBox3.setSpacing(10);
		hBox3.getChildren().addAll(addCus, deleteCusButton);
		
		VBox vBox3 = new VBox();
		vBox3.getChildren().addAll(table3);
		
		BorderPane layout4 = new BorderPane();
		layout4.setTop(topSearch3);
		layout4.setCenter(vBox3);
		layout4.setBottom(hBox3);
		customerPage = new Scene(layout4, width, height);
		
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
	
	void lowStock()
	{
		Main pro = new Main();
		String lowProduct = "";
		int productCount = 0;
		
		for(int i = 1; i <= Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)); i++)
			if(Integer.parseInt(pro.fetchData("quantity", i, 0)) <= 10)
			{
				lowProduct += pro.fetchData("product_name", i, 0) + " (Product ID: " + 
											pro.fetchData("product_id", i, 0) + ") is low on stock. Contact " + 
											pro.fetchData("supplier", i, 0) + " for resupply.\n";
				productCount++;
			}
		
		if(productCount > 0)
		{
			Stage window = new Stage();
			
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("LOW STOCK");
			window.setMinWidth(533);
			window.setMinHeight(300);
			
			Label label = new Label();
			label.setText(lowProduct);
			Button closeButton = new Button("ok");
			closeButton.setOnAction(e -> window.close());
			
			VBox layout = new VBox(20);
			layout.getChildren().addAll(label, closeButton);
			layout.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}
	}
	
	String fetchData(String column, int row, int id)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("USE cs3560;");
			ResultSet rs = stmt.executeQuery("SELECT * FROM product ORDER BY row_num");
			//if row = -1, count amount of rows
			if(column.equals("COUNT(*)") && row == -1 && id == 0) 			//id = 0 -> Collect data from product
				rs = stmt.executeQuery("SELECT COUNT(*) FROM product;");
			else if(column.equals("COUNT(*)") && row == -1 && id == -1)		//id = 1 -> Collect data from supplier
				rs = stmt.executeQuery("SELECT COUNT(*) FROM supplier;");
			else if(column.equals("COUNT(*)") && row == -1 && id == -2)		//id = 2 -> collect data from customer
				rs = stmt.executeQuery("SELECT COUNT(*) FROM customer;");
			
			else if(column.equals("row_num") && row == -2)
				rs = stmt.executeQuery("SELECT row_num FROM product WHERE product_id = " + id); //fetch product_id
			else if(column.equals("row_num") && row == -3)
				rs = stmt.executeQuery("SELECT row_num FROM supplier WHERE supplier_id = " + id); //fetch supplier_id
			else if(column.equals("row_num") && row == -4)
				rs = stmt.executeQuery("SELECT row_num FROM customer WHERE customer_id = " + id); //fetch customer_id
			
			else if(id == -1)	
				rs = stmt.executeQuery("SELECT " + column + " FROM supplier WHERE row_num = " + row); //fetch certain supplier index
			else if(id == -2)
				rs = stmt.executeQuery("SELECT " + column + " FROM customer WHERE row_num = " + row); //fetch certain customer index
			else
				rs = stmt.executeQuery("SELECT " + column + " FROM product WHERE row_num = " + row); //fetch certain product index
			
			while(rs.next())
			{
				String index = rs.getString(column);
				return index;
			}
		} 
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	void addData(int row, int id, String name, String desc, double price, int quantity, String supplier, int year, int month, int day)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO product VALUES(" + row +", " + id + ", '" + name + "', '" + desc +"', "
					+ price + ", " + quantity + ", '" + supplier + "', " + year + ", " + month + ", " + day + ");");
					
		}
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void deleteData(int row)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM product WHERE row_num = " + row);
			Main remove = new Main();
			for(int i = row; i < Integer.parseInt(remove.fetchData("COUNT(*)", -1, 0) + 1); i++)
				stmt.executeUpdate("UPDATE product SET row_num = '" + (i) +"' WHERE row_num = '" + (i+1) + "';");
		} 
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void deleteSupData(int row)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM supplier WHERE row_num = " + row);
			Main remove = new Main();
			for(int i = row; i < Integer.parseInt(remove.fetchData("COUNT(*)", -1, -1) + 1); i++)
				stmt.executeUpdate("UPDATE supplier SET row_num = '" + (i) +"' WHERE row_num = '" + (i+1) + "';");
		} 
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void deleteCusData(int row)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM customer WHERE row_num = " + row);
			Main remove = new Main();
			for(int i = row; i < Integer.parseInt(remove.fetchData("COUNT(*)", -1, -2) + 1); i++)
				stmt.executeUpdate("UPDATE customer SET row_num = '" + (i) +"' WHERE row_num = '" + (i+1) + "';");
		} 
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void addSupData(int row, int id, String name, String email, String phone)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO supplier VALUES(" + row +", " + id + ", '" + name + "', '" + email + "', '" + phone + "');");
					
		}
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void addCusData(int row, int id, String fname, String lname, String email, String phone, String prescription)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO customer VALUES(" + row +", " + id + ", '" + fname + "', '" + lname + "', '" + email + "', '" + phone + "','" + prescription + "');");
					
		}
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}