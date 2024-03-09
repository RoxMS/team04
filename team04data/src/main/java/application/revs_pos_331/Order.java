package application.revs_pos_331;

public class Order {
    //variables
    private int orderID;
    private String menuItem;
    private float price;

    //initializer
    public Order(int orderID, String menuItem, float price) {
        this.orderID = orderID;
        this.menuItem = menuItem;
        this.price = price;
    }

    //getters
    public int getOrderID() {
        return orderID;
    }
    public String getMenuItem() {
        return menuItem;
    }
    public float getPrice() {
        return price;
    }

    //setters
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
