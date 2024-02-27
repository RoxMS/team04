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
