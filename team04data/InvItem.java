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
