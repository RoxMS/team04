/**
 * Represents an order in the application.
 * 
 * @author Nicole Hernandez
 */
public class Order {
    // Variables
    private int orderID = 0;
    private String menuItem = null;
    private float price = 0.0f;
    
    /**
     * Constructs an Order object with the given details.
     * 
     * @param orderID   The ID of the order.
     * @param menuItem  The name of the menu item.
     * @param price     The price of the menu item.
     */
    public Order(int orderID, String menuItem, float price) {
        this.orderID = orderID;
        this.menuItem = menuItem;
        this.price = price;
    }
    
    /**
     * Retrieves the ID of the order.
     * 
     * @return The ID of the order.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Retrieves the name of the menu item.
     * 
     * @return The name of the menu item.
     */
    public String getMenuItem() {
        return menuItem;
    }

    /**
     * Retrieves the price of the menu item.
     * 
     * @return The price of the menu item.
     */
    public float getPrice() {
        return price;
    }
    
    /**
     * Sets the ID of the order.
     * 
     * @param orderID The ID of the order to set.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Sets the name of the menu item.
     * 
     * @param menuItem The name of the menu item to set.
     */
    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * Sets the price of the menu item.
     * 
     * @param price The price of the menu item to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
