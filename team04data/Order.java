package application;

public class Order {
	private String menuItem = null;
	private float price = 0.0f;
	
	public Order(String menuItem, float price) {
		this.menuItem = menuItem;
		this.price = price;
	}
	
	public String getMenuItem() {
		return menuItem;
	}
	public float getPrice() {
		return price;
	}
}
