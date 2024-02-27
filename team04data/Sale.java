package application;

public class Sale { //each row
    //columns
    private float month = 0.0f;
    private float year = 0.0f;
    private float total_sales = 0.0f;

    //initialize each column
    public Sale(float month, float year, float total_sales) {
        this.month = month;
        this.year = year;
        this.total_sales = total_sales;
    }

    //getter  column values
    public float getMonth() {
        return month;
    }
    //getter  column values
    public float getYear() {
        return year;
    }

    //getter  column values
    public float getTotalSales() {
        return total_sales;
    }

}
