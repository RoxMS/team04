package application;

public class Sale { //Represents each row
    //Initialize Column variables
    private float month = 0.0f;
    private float year = 0.0f;
    private float total_sales = 0.0f;

    //initialize each column to the parameter values in the constructor
    public Sale(float month, float year, float total_sales) {
        this.month = month;
        this.year = year;
        this.total_sales = total_sales;
    }

    //Getter for month column value
    public float getMonth() {
        return month;
    }
    //Getter for year column value
    public float getYear() {
        return year;
    }

    //Getter for total sales column value
    public float getTotalSales() {
        return total_sales;
    }

}

@FXML private TableView<Sale> saleTable; //Declaration of TableView control to display Sale type items
@FXML private TableColumn<Sale, Float> monthColumn; //Declaration of TableView column to display float type items
@FXML private TableColumn<Sale, Float> yearColumn; //Declaration of TableView column to display float type items
@FXML private TableColumn<Sale, Float> total_salesColumn; //Declaration of TableView column to display float type items


TableColumn<Sale, Float> monthColumn = new TableColumn<>("Month");//Instantiate TableColumn with header text "Month"
TableColumn<Sale, Float> yearColumn = new TableColumn<>("Year");//Instantiate TableColumn with header text "Year"
TableColumn<Sale, Float> total_salesColumn = new TableColumn<>("Total Sales");//Instantiate TableColumn with header text "Total Sales"

monthColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("month")); //extracts data from month property of class
yearColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("year"));  //extracts data from year property of class
total_salesColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("total_sales"));//extracts data from total_sales property of class
saleTable.setPlaceholder(new Label(" "));//Place holder for when table is empty

try {//try statement to retrieve data from the "Orders" table in the database and add to saleTable Tableview
    Statement stmt = conn.createStatement();
    String sqlStatement = "SELECT month, year, SUM(sale) as totalSale FROM orders GROUP BY month, year ORDER BY year ASC, month ASC;"; //SQL query statement to grab data from database
    ResultSet result = stmt.executeQuery(sqlStatement);
    while (result.next()) {
        String ingredient = result.getString("month");
        float quanitity = result.getFloat("year");
        float capacity = result.getFloat("totalSale");
        saleTable.getItems().add(new Sale(month, year, total_sales) );
    }
}
