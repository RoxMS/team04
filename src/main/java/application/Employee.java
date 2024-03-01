package application;

public class Employee {
    //variables
    private int employeeID;
    private String name;
    private String status;

    //initializer
    public Employee(int employeeID, String name, String status) {
        this.employeeID = employeeID;
        this.name = name;
        this.status = status;
    }

    //getters
    public int getEmployeeID() {
        return employeeID;
    }
    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }

    //setters
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}