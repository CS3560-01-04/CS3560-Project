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
	Scene login, homePage;
	Button submitBtn;
	TableView<Drug> table = new TableView<>();
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
		String[] sValues = new String[3];
		double price = 0.0;
		
		for(int i = 0; i < Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)); i++)
		{
			rowCount++;
			if(rowCount == Integer.parseInt(pro.fetchData("row_num", rowCount, 0)))
			{
				intValues[0] = Integer.parseInt(pro.fetchData("product_id", rowCount, 0));
				sValues[0] = pro.fetchData("product_name", rowCount, 0);
				sValues[1] = pro.fetchData("descript", rowCount, 0);
				intValues[1] = Integer.parseInt(pro.fetchData("quantity", rowCount, 0));
				price = Double.parseDouble(pro.fetchData("price", rowCount, 0));
				sValues[2] = pro.fetchData("supplier", rowCount, 0);
				intValues[2] = Integer.parseInt(pro.fetchData("exp_year", rowCount, 0));
				intValues[3] = Integer.parseInt(pro.fetchData("exp_month", rowCount, 0));
				intValues[4] = Integer.parseInt(pro.fetchData("exp_day", rowCount, 0));
			}
			
			drugs.add(new Drug(intValues[0], sValues[0], sValues[1],
					intValues[1], price, sValues[2], intValues[2], 
					intValues[3], intValues[4]));
		}
		
		
		FilteredList<Drug> filteredList = new FilteredList<>(drugs);//Pass the data to a filtered list
		SortedList<Drug> sortedList = new SortedList<Drug>(filteredList);
		table.setEditable(true);
		//table.setItems(drugs);
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
            
		//Buttons to add or delete drugs
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> {
			if((Validator.validation("Integer", drugIDInput.getText())) &&
					(Validator.validation("Double", drugPriceInput.getText())) &&
					(Validator.validation("Integer", drugQuantityInput.getText())) &&
					(Validator.validation("Integer", drugExpyearInput.getText())) &&
					(Validator.validation("Integer", drugExpmonthInput.getText())) &&
					(Validator.validation("Integer", drugExpdayInput.getText()))) {
						drugs.add(new Drug(Integer.parseInt(drugIDInput.getText()), drugNameInput.getText(),
								drugDescriptionInput.getText(), Integer.parseInt(drugQuantityInput.getText()), 
								Double.parseDouble(drugPriceInput.getText()), drugSupplierInput.getText(),
								Integer.parseInt(drugExpyearInput.getText()), Integer.parseInt(drugExpmonthInput.getText()),
								Integer.parseInt(drugExpdayInput.getText())));
						
						Main add = new Main();
						add.addData(Integer.parseInt(pro.fetchData("COUNT(*)", -1, 0)) + 1, Integer.parseInt(drugIDInput.getText()), drugNameInput.getText(), drugDescriptionInput.getText(),
								Double.parseDouble(drugPriceInput.getText()), Integer.parseInt(drugQuantityInput.getText()),
								drugSupplierInput.getText(), Integer.parseInt(drugExpyearInput.getText()),
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
		});
		
		HBox topSearch = new HBox(5);
		topSearch.setPadding(new Insets(5, 5, 5, 5));
		topSearch.getChildren().addAll(searchDropDown, searchInput, refresh);
				
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(drugIDInput, drugNameInput, drugDescriptionInput, drugPriceInput, drugQuantityInput,
				drugSupplierInput, drugExpyearInput, drugExpmonthInput, drugExpdayInput, addButton, deleteButton);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		//Creating a border pane layout for our second scene
		BorderPane layout2 = new BorderPane();
		int width = ((int) Screen.getPrimary().getBounds().getWidth()) - 50;
		int height = ((int) Screen.getPrimary().getBounds().getHeight() - 100);
		layout2.setTop(topSearch);
		layout2.setCenter(vBox);
		layout2.setBottom(hBox);
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
			
			
			if(column.equals("COUNT(*)") && row == -1) //if row = -1, count amount of rows
				rs = stmt.executeQuery("SELECT COUNT(*) FROM product;");
			else if(column.equals("row_num") && row == -2)
				rs = stmt.executeQuery("SELECT row_num FROM product WHERE product_id = " + id);
			else
				rs = stmt.executeQuery("SELECT " + column + " FROM product WHERE row_num = " + row);
			
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
}