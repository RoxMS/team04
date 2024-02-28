package application;

public class InvItem { //each row
    //Initialize Column variables
    private String item = null;
    private float quantity = 0.0f;
    private Float capacity = 0.0f;

    //initialize each column to the parameter values in the constructor
    public InvItem(String item, float quantity, float capacity) {
        this.item = item;
        this.quantity = quantity;
        this.capacity = capacity;
    }

    //Getter for item column value
    public String getItem() {
        return item;
    }
    //Getter for quantity column value
    public float getQuanity() {
        return quantity;
    }

    //Getter for capacity column value
    public float getCapacity() {
        return capacity;
    }

}


@FXML private TableView<InvItem> invitemTable; //Declaration of TableView control to display InvItem type items
@FXML private TableColumn<InvItem, String> itemColumn; //Declaration of TableView column to display String type items
@FXML private TableColumn<InvItem, Float> quantityColumn; //Declaration of TableView column to display float type items
@FXML private TableColumn<InvItem, Float> capacityColumn; //Declaration of TableView column to display float type items

TableColumn<InvItem, String> itemColumn = new TableColumn<>("Item"); //Instantiate TableColumn with header text "Item"
TableColumn<InvItem, Float> quantityColumn = new TableColumn<>("Quanity"); //Instantiate TableColumn with header text "Quantity"
TableColumn<InvItem, Float> capacityColumn = new TableColumn<>("Capacity"); //Instantiate TableColumn with header text "Capacity"

itemColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("item")); //extracts data from item property of class
quantityColumn.setCellValueFactory(new PropertyValueFactory<InvItem, Float>("quantity")); //extracts data from quanitity property of class
capacityColumn.setCellValueFactory(new PropertyValueFactory<InvItem, Float>("capacity")); //extracts data from capacity property of class
invitemTable.setPlaceholder(new Label(" ")); //Place holder for when table is empty


try {//try statement to retrieve data from the "Inventory" table in the database and add to invitemTable Tableview
    Statement stmt = conn.createStatement();
    String sqlStatement = " SELECT ingredient, amount, capacity FROM inventory"; //SQL query statement to grab data from database
    ResultSet result = stmt.executeQuery(sqlStatement);
    while (result.next()) {
        String ingredient = result.getString("ingredient");
        float quanitity = result.getFloat("amount");
        float capacity = result.getFloat("capacity");
        invitemTable.getItems().add(new InvItem(ingredient, quantity, capacity) );    
    }


}

