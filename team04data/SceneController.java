package application;
import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	
	private Connection conn = null;
	
	//orders table
    @FXML private TableView<Order> orderTable;
    @FXML private TableColumn<Order, String> menuItemColumn;
    @FXML private TableColumn<Order, Float> priceColumn;
    //employees table
    @FXML private TableView<Employees> employeeTable;
    @FXML private TableColumn<Employees, String> nameColumn;
    @FXML private TableColumn<Employees, Integer> idColumn;
    @FXML private TableColumn<Employees, String> statusColumn;
    //sales table
    @FXML private TableView<Sale> saleTable;
    @FXML private TableColumn<Sale, Float> monthColumn;
    @FXML private TableColumn<Sale, Float> yearColumn;
    @FXML private TableColumn<Sale, Float> total_salesColumn;
    //inventory table
    @FXML private TableView<InvItem> invitemTable;
    @FXML private TableColumn<InvItem, String> itemColumn;
    @FXML private TableColumn<InvItem, Float> quantityColumn;
    @FXML private TableColumn<InvItem, Float> capacityColumn;
    
    public void menuScreen(ActionEvent e) throws IOException {
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root,773,470);
		stage.setScene(scene);
		stage.show();
		
		//setting up table
        TableColumn<Order, String> menuItemColumn = new TableColumn<>("Order");
        TableColumn<Order, Float> priceColumn = new TableColumn<>("Total");
        menuItemColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("menuItem"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("price"));
        orderTable.setPlaceholder(new Label("You must have an order before pressing Tender."));
	}
    
    public void managerScreen(ActionEvent e) throws IOException {
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("manager.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root,773,470);
		stage.setScene(scene);
		stage.show();
		
		//setting up employees table
		TableColumn<Employees, String> employeeTable = new TableColumn<>("Employees");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Employees, String>("Name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("ID"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Employees, String>("Status"));
		
		//setting up sales table
		TableColumn<Sale, Float> monthColumn = new TableColumn<>("Month");
		TableColumn<Sale, Float> yearColumn = new TableColumn<>("Year");
		TableColumn<Sale, Float> total_salesColumn = new TableColumn<>("Total Sales");
		monthColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("month"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("year"));
		total_salesColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("total_sales"));
		
		//setting up inventory table
		TableColumn<InvItem, String> itemColumn = new TableColumn<>("Item");
		TableColumn<InvItem, Float> quantityColumn = new TableColumn<>("Quanity");
		TableColumn<InvItem, Float> capacityColumn = new TableColumn<>("Capacity");
		itemColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("item"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<InvItem, Float>("quantity"));
		capacityColumn.setCellValueFactory(new PropertyValueFactory<InvItem, Float>("capacity"));

	}
    
    
    public void addMenuItem(MouseEvent e) {
    	//getting name of menu item
    	String name = ((Node)e.getSource()).getId();
		name = name.replaceAll("_", " ");
		name = name.replaceAll("1", "'");
		System.out.println(name);
		
		
		//setting up database
		String database_name = "csce331_903_04_db";
        String database_user = "csce331_903_04_user";
        String database_password = "team04";
        String database_url = String.format("jdbc:postgresql://csce-315-db.engr.tamu.edu/%s", database_name);
        try {
          conn = DriverManager.getConnection(database_url, database_user, database_password);
        } catch (Exception error) {
        	error.printStackTrace();
          System.err.println(error.getClass().getName()+": "+error.getMessage());
          System.exit(0);
        }
        
        //querying the database for price
        try {
			Statement stmt = conn.createStatement();
		    String sqlStatement = "SELECT sale FROM orders WHERE menu_item='" + name + "' LIMIT 1";
		    ResultSet result = stmt.executeQuery(sqlStatement);
		    while (result.next()) {
		    	float price = result.getFloat("sale");
		    	System.out.println(price);
			    orderTable.getItems().add(new Order(name, price));
		    }
		    
        }
	    catch(Exception error) {
	    	System.out.println("error");
	    }
        try {
	        conn.close();
	        System.out.println("Connection Closed.");
	    } catch(Exception error2) {
	        System.out.println("Connection NOT Closed.");
	      }//end try catch
    }
    
    
	public void tender(MouseEvent e) throws IOException {
		//adds order to database
		try {
			Statement stmt = conn.createStatement();
		    String sqlStatement = "";
		    stmt.executeQuery(sqlStatement);
		}
	    catch(Exception error) {}
	}
}


