package application.revs_pos_331;

import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDateTime;

import java.sql.*;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalTime;          // to get current hour for excess report

public class SceneController {

    private Connection conn = null;

    public static ArrayList<Order> orders = new ArrayList<>();
    @FXML private TableView<Misc> misc_list;
    @FXML private TableColumn<Misc, String> misc_col;


    String orders_string = "";
    String sales_string = "";
    String inventory_string = "";
    String employees_string = "";
    String menu_string = "";
    String history_string = "";
    int count = 1;

    @FXML private Label orders_text = new Label("");
    @FXML private Label sales_text = new Label("");
    @FXML private Label inventory_text = new Label("");
    @FXML private Label employees_text = new Label("");
    @FXML private Label menu_text = new Label("");
    @FXML private Label history_text = new Label("");
    @FXML private Label excess_text = new Label("");
    @FXML private Label trends_text = new Label("");

    //warnings
    @FXML private Label login_warning = new Label("");
    @FXML private Label inventory_warning = new Label("");
    @FXML private Label employees_warning = new Label("");
    @FXML private Label menu_warning = new Label("");
    @FXML private Label orders_warning = new Label("");
    @FXML private Label history_warning = new Label("");
    @FXML private Label excess_warning = new Label("");
    @FXML private Label order_trends_warning = new Label("");


    //login text fields
    @FXML private TextField id = new TextField();
    @FXML private TextField password = new TextField();

    //order text fields
    @FXML private TextField del_ord = new TextField();
    @FXML private TextField upd_ord = new TextField();
    @FXML private TextField upd_food = new TextField();
    @FXML private TextField upd_total = new TextField();

    //inventory text fields
    @FXML private TextField del_inv = new TextField();
    @FXML private TextField upd_inv = new TextField();
    @FXML private TextField add_item = new TextField();
    @FXML private TextField upd_item = new TextField();
    @FXML private TextField add_quant = new TextField();
    @FXML private TextField upd_quant = new TextField();
    @FXML private TextField add_cap = new TextField();
    @FXML private TextField upd_cap = new TextField();

    //employees text fields
    @FXML private TextField del_emp = new TextField();
    @FXML private TextField upd_emp = new TextField();
    @FXML private TextField add_name = new TextField();
    @FXML private TextField upd_name = new TextField();
    @FXML private TextField add_status = new TextField();
    @FXML private TextField upd_status = new TextField();

    //menu text fields
    @FXML private TextField add_dish = new TextField();
    @FXML private TextField add_price = new TextField();
    @FXML private TextField del_menu = new TextField();
    @FXML private TextField upd_menu = new TextField();
    @FXML private TextField upd_dish = new TextField();
    @FXML private TextField upd_price = new TextField();

    // excess report text fields
    @FXML private TextField from_hr = new TextField();
    @FXML private TextField from_day = new TextField();
    @FXML private TextField from_month = new TextField();
    @FXML private TextField from_year = new TextField();

    // order trend report text fields
    @FXML private TextField st_trend_hr = new TextField();
    @FXML private TextField st_trend_day = new TextField();
    @FXML private TextField st_trend_month = new TextField();
    @FXML private TextField st_trend_year = new TextField();
    @FXML private TextField end_trend_hr = new TextField();
    @FXML private TextField end_trend_day = new TextField();
    @FXML private TextField end_trend_month = new TextField();
    @FXML private TextField end_trend_year = new TextField();

    //order history text fields
    @FXML private TextField del_id = new TextField();
    @FXML private TextField del_hr = new TextField();
    @FXML private TextField del_day = new TextField();
    @FXML private TextField del_mth = new TextField();
    @FXML private TextField del_wk = new TextField();
    @FXML private TextField del_yr = new TextField();
    @FXML private TextField upd_id = new TextField();
    @FXML private TextField upd_hr = new TextField();
    @FXML private TextField upd_day = new TextField();
    @FXML private TextField upd_mth = new TextField();
    @FXML private TextField upd_wk = new TextField();
    @FXML private TextField upd_yr = new TextField();
    @FXML private TextField upd_hist_item = new TextField();
    @FXML private TextField upd_sale = new TextField();

    public void login(ActionEvent e) throws IOException {
        connect();

        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT employeeID FROM employees";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                String employeeID = result.getString("employeeID");
                if(id.getText().equals(employeeID) && password.getText().equals("revs123")) {
                    sqlStatement = "SELECT status FROM employees WHERE employeeID=" + id.getText();
                    result = stmt.executeQuery(sqlStatement);
                    while (result.next()) {
                        String status = result.getString("status");
                        if (status.equals("manager")) {
                            managerScreen(e);
                        }
                        else if (status.equals("employee")) {
                            menuScreen(e);
                        }
                    }
                }
                else {
                    login_warning.setText("Invalid username or password.");
                }
            }
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }
    public void loginScreen(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 773, 470);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void menuScreen(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 979, 581);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
    public void managerScreen(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("manager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 783, 482);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Manager");
        stage.setScene(scene);
        stage.show();
    }

    public void loadManagerTables(MouseEvent e) {
        connect();

        loadSalesTable();
        loadInventoryTable();
        loadEmployeesTable();
        loadMenuTable();
        loadOrderHistoryTable();

        close();
    }
    public void loadSalesTable() {
        sales_string = "";
        //querying for sales table
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT month, year, SUM(sale) as totalSale FROM orders GROUP BY month, year ORDER BY year ASC, month ASC";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                int month = result.getInt("month");
                int year = result.getInt("year");
                float sale = result.getFloat("totalSale");
                sales_string += month;
                for(int i = 1; i <= 39-(month+"").length(); i++) {
                    sales_string += " ";
                }
                sales_string += year;
                for(int i = 1; i <= 31-(year+"").length(); i++) {
                    sales_string += " ";
                }
                sales_string += sale + "\n";
            }
            sales_text.setText(sales_string);
        }
        catch(Exception error) {
            error.printStackTrace();
            System.err.println(error.getClass().getName()+": "+error.getMessage());
        }
    }
    public void loadInventoryTable() {
        inventory_string = "";
        //querying for inventory table
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT inventoryID, ingredient, amount, capacity FROM inventory";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                int inventoryID = result.getInt("inventoryID");
                String ingredient = result.getString("ingredient");
                int amount = result.getInt("amount");
                int capacity = result.getInt("capacity");
                inventory_string += " " + inventoryID;
                for(int i = 1; i <= 3-(inventoryID+"").length(); i++) {
                    inventory_string += " ";
                }
                inventory_string += "   " + ingredient;
                for(int i = 1; i <= 35-(ingredient+"").length(); i++) {
                    inventory_string += " ";
                }
                inventory_string += "        " + amount;
                for(int i = 1; i <= 3-(amount+"").length(); i++) {
                    inventory_string += " ";
                }
                inventory_string += "            " + capacity + "\n";
            }
            inventory_text.setText(inventory_string);
        }
        catch(Exception error) {
            error.printStackTrace();
            System.err.println(error.getClass().getName()+": "+error.getMessage());
        }
    }
    public void loadEmployeesTable() {
        employees_string = "";
        //querying for employees table
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT employeeID, name, status FROM employees";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                int employeeID = result.getInt("employeeID");
                String name = result.getString("name");
                String status = result.getString("status");
                employees_string += " " + employeeID;
                for(int i = 1; i <= 3-(employeeID+"").length(); i++) {
                    employees_string += " ";
                }
                employees_string += "   " + name;
                for(int i = 1; i <= 46-name.length(); i++) {
                    employees_string += " ";
                }
                employees_string += "     " + status + "\n";
            }
            employees_text.setText(employees_string);
        }
        catch(Exception error) {
            error.printStackTrace();
            System.err.println(error.getClass().getName()+": "+error.getMessage());
        }
    }
    public void loadMenuTable() {
        menu_string = "";
        //querying for menu table
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT menu_itemID, menu_item, price FROM menu";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                int menu_itemID = result.getInt("menu_itemID");
                String menu_item = result.getString("menu_item");
                float price = result.getFloat("price");
                menu_string += " " + menu_itemID;
                for(int i = 1; i <= 3-(menu_itemID+"").length(); i++) {
                    menu_string += " ";
                }
                menu_string += "   " + menu_item;
                for(int i = 1; i <= 51-menu_item.length(); i++) {
                    menu_string += " ";
                }
                menu_string += "     " + price + "\n";
            }
            menu_text.setText(menu_string);
        }
        catch(Exception error) {
            error.printStackTrace();
            System.err.println(error.getClass().getName()+": "+error.getMessage());
        }
    }
    public void loadOrderHistoryTable() {
        history_string = "";
        //querying for order history table
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM orders WHERE year = (SELECT MAX(year) FROM orders) AND month = (SELECT MAX(month) FROM orders WHERE year = (SELECT MAX(year) FROM orders)) AND day = (SELECT MAX(day) FROM orders WHERE year = (SELECT MAX(year) FROM orders) AND month = (SELECT MAX(month) FROM orders WHERE year = (SELECT MAX(year) FROM orders))) AND hour = (SELECT MAX(hour) FROM orders WHERE year = (SELECT MAX(year) FROM orders) AND month = (SELECT MAX(month) FROM orders WHERE year = (SELECT MAX(year) FROM orders)) AND day = (SELECT MAX(day) FROM orders WHERE year = (SELECT MAX(year) FROM orders) AND month = (SELECT MAX(month) FROM orders WHERE year = (SELECT MAX(year) FROM orders)))) ORDER BY orderid DESC LIMIT 30";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                int orderID = result.getInt("orderID");
                String hour = result.getString("hour");
                int day = result.getInt("day");
                int month = result.getInt("month");
                int week = result.getInt("week");
                int year = result.getInt("year");
                String menu_item = result.getString("menu_item");
                float sale = result.getFloat("sale");
                history_string += " " + orderID;
                for(int i = 1; i <= 3-(orderID+"").length(); i++) {
                    history_string += " ";
                }
                history_string += " " + hour;
                for(int i = 1; i <= 4-(hour+"").length(); i++) {
                    history_string += "   ";
                }
                history_string += " " + day;
                for(int i = 1; i <= 3-(day+"").length(); i++) {
                    history_string += "   ";
                }
                history_string += " " + month;
                for(int i = 1; i <= 3-(month+"").length(); i++) {
                    history_string += "   ";
                }
                history_string += " " + week;
                for(int i = 1; i <= 3-(week+"").length(); i++) {
                    history_string += "   ";
                }
                history_string += " " + year;
                for(int i = 1; i <= 3-(year+"").length(); i++) {
                    history_string += "   ";
                }
                history_string += "   " + menu_item;
                for(int i = 1; i <= 51-menu_item.length(); i++) {
                    history_string += " ";
                }
                history_string += "   " + sale + "\n";
            }
            history_text.setText(history_string);
        }
        catch(Exception error) {
            error.printStackTrace();
            System.err.println(error.getClass().getName()+": "+error.getMessage());
        }
    }
    public void listMiscMenuItems(MouseEvent e) {
        try {
            misc_col.setCellValueFactory(new PropertyValueFactory<Misc, String>("menuitem"));
            misc_list.setVisible(true);

            List<Misc> list = new ArrayList<Misc>();
            this.connect();
            String sqlStatement = "SELECT menu_item FROM menu WHERE menu_itemid > 29";
            PreparedStatement stmt = this.conn.prepareStatement(sqlStatement);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                String menuItem = result.getString("menu_item");

                list.add(new Misc(menuItem));
            }

            misc_list.getItems().setAll(list);

        } catch (Exception error) {
            orders_warning.setText("Unable to load items.");
        }
    }

    public void addMiscItem(MouseEvent e) {
        String name = misc_list.getSelectionModel().getSelectedItem().getMenuitem();

        connect();
        //querying the database for price
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT price FROM menu WHERE menu_item='" + name + "' LIMIT 1";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                float price = result.getFloat("price");
                orders_string += "  " + count + "   " + name;
                for(int i = 1; i <= 41-name.length(); i++) {
                    orders_string += " ";
                }
                orders_string += "$" + price + "\n";
                orders_text.setText(orders_string);
                orders.add(new Order(count, name, price));
                count += 1;
            }
        } catch(Exception error) {
            orders_warning.setText("Unable to add item.");
        }
    }

    public void addOrderItem(MouseEvent e) {
        //getting name of menu item
        String name = ((Node)e.getSource()).getId();
        name = name.replaceAll("_", " ");

        connect();

        //querying the database for price
        try {
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT price FROM menu WHERE menu_item='" + name + "' LIMIT 1";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                float price = result.getFloat("price");
                orders_string += "  " + count + "   " + name;
                for(int i = 1; i <= 41-name.length(); i++) {
                    orders_string += " ";
                }
                orders_string += "$" + price + "\n";
                orders_text.setText(orders_string);
                orders.add(new Order(count, name, price));
                count += 1;
            }
        } catch(Exception error) {
            orders_warning.setText("Unable to add item.");
        }
    }
    public void deleteOrderItem(MouseEvent e) throws IOException {
        //deleting menu item from orders array
        int orderID = Integer.parseInt(del_ord.getText());

        int del_index = -1;
        orders_string = "";
        for(int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                del_index = i;
            }
            else {
                orders_string += "  " + orders.get(i).getOrderID() + "   " + orders.get(i).getMenuItem();
                for(int j = 1; j <= 41-orders.get(i).getMenuItem().length(); j++) {
                    orders_string += " ";
                }
                orders_string += "$" + orders.get(i).getPrice() + "\n";
            }
        }
        orders_text.setText(orders_string);
        if (del_index < 0) {
            orders_warning.setText("Invalid order item ID.");
        }
        else {
            orders.remove(del_index);
            del_ord.setText("");
        }
    }
    public void updateOrderItem(MouseEvent e) throws IOException {
        //updating order item
        int orderID = Integer.parseInt(upd_ord.getText());
        String menu_item = upd_food.getText();
        float price = Float.parseFloat(upd_total.getText());

        if (menu_item.isEmpty()) {
            orders_warning.setText("Menu item must have a name.");
            return;
        }
        if (price <= 0) {
            orders_warning.setText("Price cannot be negative or zero.");
            return;
        }

        boolean updated = false;
        orders_string = "";
        for(Order item : orders) {
            if (item.getOrderID() == orderID) {
                item.setMenuItem(menu_item);
                item.setPrice(price);
                updated = true;
            }
            //changing employee table GUI
            orders_string += "  " + item.getOrderID() + "   " + item.getMenuItem();
            for(int i = 1; i <= 41-item.getMenuItem().length(); i++) {
                orders_string += " ";
            }
            orders_string += "$" + item.getPrice() + "\n";
        }
        orders_text.setText(orders_string);
        if (!updated) {
            orders_warning.setText("Invalid order item ID.");
        }
        else {
            upd_ord.setText("");
            upd_food.setText("");
            upd_total.setText("");
        }
    }

    public void clearTextFields() {
        //inventory text fields
        add_item.setText("");
        add_quant.setText("");
        add_cap.setText("");
        del_inv.setText("");
        upd_inv.setText("");
        upd_item.setText("");
        upd_quant.setText("");
        upd_cap.setText("");

        //employee text fields
        add_name.setText("");
        add_status.setText("");
        del_emp.setText("");
        upd_name.setText("");
        upd_status.setText("");
        upd_emp.setText("");

        //menu text fields
        add_dish.setText("");
        add_price.setText("");
        del_menu.setText("");
        upd_menu.setText("");
        upd_dish.setText("");
        upd_price.setText("");

        // excess report text fields
        from_hr.setText("");
        from_month.setText("");
        from_day.setText("");
        from_year.setText("");

        // order trend report text fields
        st_trend_hr.setText("");
        st_trend_day.setText("");
        st_trend_month.setText("");
        st_trend_year.setText("");
        end_trend_hr.setText("");
        end_trend_day.setText("");
        end_trend_month.setText("");
        end_trend_year.setText("");

        //order history fields
        del_id.setText("");
        del_hr.setText("");
        del_day.setText("");
        del_mth.setText("");
        del_wk.setText("");
        del_yr.setText("");
        upd_id.setText("");
        upd_hr.setText("");
        upd_day.setText("");
        upd_mth.setText("");
        upd_wk.setText("");
        upd_yr.setText("");
        upd_item.setText("");
        upd_sale.setText("");
    }

    /**
     * This allows the manager to add items to the inventory.
     * Takes care of error handling, ensuring that there are no empty text fields or negative numbers for amount
     * Includes an SQL statement that gets a list of all the items currently in inventory after adding item
     * Clears the text field once item has been added to inventory
     @author Olivia Lee
     */
    public void addInventoryItem(MouseEvent e) throws IOException {
        try {
            connect();

            String ingredient = add_item.getText();
            int amount = Integer.parseInt(add_quant.getText());
            int capacity = Integer.parseInt(add_cap.getText());

            if (ingredient.isEmpty()) {
                inventory_warning.setText("You must enter an ingredient.");
                return;
            }
            if (amount > capacity) {
                inventory_warning.setText("Amount cannot be less than capacity.");
                return;
            }
            if (amount < 0 || capacity < 0) {
                inventory_warning.setText("Amount and capacity must both be positive numbers.");
                return;
            }

            Statement stmt = conn.createStatement();
            String sqlStatement = "INSERT INTO inventory(ingredient, amount, capacity) VALUES('" + ingredient + "', " + amount + ", " + capacity + ")";
            stmt.executeUpdate(sqlStatement);

            loadInventoryTable();

            clearTextFields();
            inventory_warning.setText("");
        }
        catch (SQLException error) {
            inventory_warning.setText("Invalid value types.");
        }
        close();
    }

    /**
     * This allows the manager to remove items from the inventory.
     * Manager has to enter the item id in the text field to remove it
     * Takes care of error handling, ensuring that there are no empty text fields or negative numbers for amount
     * Includes an SQL command to ensure that item is removed from database
     * Clears the text field once item has been removed from inventory
     @author Olivia Lee
     */
    public void deleteInventoryItem(MouseEvent e) throws IOException {
        try {
            connect();

            int inventoryID = Integer.parseInt(del_inv.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT inventoryID FROM inventory WHERE inventoryID=" + inventoryID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                inventory_warning.setText("Invalid inventory item ID.");
                return;
            }

            sqlStatement = "DELETE FROM inventory WHERE inventoryID=" + inventoryID;
            stmt.executeUpdate(sqlStatement);

            loadInventoryTable();

            clearTextFields();
            inventory_warning.setText("");
        }
        catch (SQLException error) {
            inventory_warning.setText("Invalid inventory item ID.");
        }
        close();
    }

    /**
     * This allows the manager to add extra items to the inventory when stock is running low.
     * Manager has to enter the item id, amount, and capacity of each the item to be updated
     * Takes care of error handling, ensuring that there are no empty text fields or negative numbers for amount
     * Includes an SQL command to ensure that change occurs in database as well
     * Clears the text field once item has been updated in inventory
     @author Olivia Lee
     */
    public void updateInventoryItem(MouseEvent e) throws IOException {
        try {
            connect();

            int inventoryID = Integer.parseInt(upd_inv.getText());
            String ingredient = upd_item.getText();
            int amount = Integer.parseInt(upd_quant.getText());
            int capacity = Integer.parseInt(upd_cap.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT inventoryID FROM inventory WHERE inventoryID=" + inventoryID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                inventory_warning.setText("Invalid inventory item ID.");
                return;
            }

            if (ingredient.isEmpty()) {
                inventory_warning.setText("You must enter an ingredient.");
                return;
            }
            if (amount > capacity) {
                inventory_warning.setText("Amount cannot be less than capacity.");
                return;
            }
            if (amount < 0 || capacity < 0) {
                inventory_warning.setText("Amount and capacity must both be positive numbers.");
                return;
            }

            stmt = conn.createStatement();
            sqlStatement = "UPDATE inventory SET ingredient='" + ingredient + "', amount=" + amount + ", capacity=" + capacity + " WHERE inventoryID=" + inventoryID;
            stmt.executeUpdate(sqlStatement);

            loadInventoryTable();

            clearTextFields();
            inventory_warning.setText("");
        }
        catch (SQLException error) {
            inventory_warning.setText("Invalid value types.");
        }
        close();
    }

    public void addEmployeeItem(MouseEvent e) throws IOException {
        try {
            connect();

            //inserting employee item into database
            String name = add_name.getText();
            String status = add_status.getText();

            if (name.isEmpty()) {
                employees_warning.setText("You must enter a name.");
                return;
            }
            if (!status.equals("manager") && !status.equals("employee")) {
                employees_warning.setText("Invalid status.");
                return;
            }

            Statement stmt = conn.createStatement();
            String sqlStatement = "INSERT INTO employees(name, status) VALUES('" + name + "', '" + status + "')";
            stmt.executeUpdate(sqlStatement);

            loadEmployeesTable();

            clearTextFields();
            employees_warning.setText("");
        }
        catch (SQLException error) {
            employees_warning.setText("Invalid value types.");
        }
        close();
    }
    public void deleteEmployeeItem(MouseEvent e) throws IOException {
        try {
            connect();

            //deleting inventory item in database
            int employeeID = Integer.parseInt(del_emp.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT employeeID FROM employees WHERE employeeID=" + employeeID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                employees_warning.setText("Invalid employee ID.");
                return;
            }

            sqlStatement = "DELETE FROM employees WHERE employeeID=" + employeeID;
            stmt.executeUpdate(sqlStatement);

            loadEmployeesTable();

            clearTextFields();
            employees_warning.setText("");
        }
        catch (SQLException error) {
            employees_warning.setText("Invalid employee ID.");
        }
        close();
    }
    public void updateEmployeeItem(MouseEvent e) throws IOException {
        try {
            connect();

            //updating employee item from database
            int employeeID = Integer.parseInt(upd_emp.getText());
            String name = upd_name.getText();
            String status = upd_status.getText();

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT employeeID FROM employees WHERE employeeID=" + employeeID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                employees_warning.setText("Invalid inventory item ID.");
                return;
            }

            if (name.isEmpty()) {
                employees_warning.setText("You must enter a name.");
                return;
            }
            if (!status.equals("manager") && !status.equals("employee")) {
                employees_warning.setText("Invalid status.");
                return;
            }

            stmt = conn.createStatement();
            sqlStatement = "UPDATE employees SET name='" + name + "', status='" + status + "' WHERE employeeID=" + employeeID;
            stmt.executeUpdate(sqlStatement);

            loadEmployeesTable();

            clearTextFields();
            employees_warning.setText("");
        }
        catch (SQLException error) {
            employees_warning.setText("Invalid value types or employee ID.");
        }
        close();
    }

    public void addMenuItem(MouseEvent e) throws IOException {
        try {
            connect();

            //inserting menu item into database
            String menu_item = add_dish.getText();
            float price = Float.parseFloat(add_price.getText());

            if (menu_item.isEmpty()) {
                orders_warning.setText("You must enter a menu item.");
                return;
            }
            if (price <= 0) {
                menu_warning.setText("Price cannot be negative or zero.");
                return;
            }

            Statement stmt = conn.createStatement();
            String sqlStatement = "INSERT INTO menu(menu_item, price) VALUES('" + menu_item + "', " + price + ")";
            stmt.executeUpdate(sqlStatement);

            loadMenuTable();

            clearTextFields();
            menu_warning.setText("");
        }
        catch (SQLException error) {
            menu_warning.setText("Invalid value types.");
        }
        close();
    }
    public void deleteMenuItem(MouseEvent e) throws IOException {
        try {
            connect();

            //deleting menu item in database
            int menu_itemID = Integer.parseInt(del_menu.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT menu_itemID FROM menu WHERE menu_itemID=" + menu_itemID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                menu_warning.setText("Invalid menu item ID.");
                return;
            }

            if(menu_itemID <= 28) {
                menu_warning.setText("You cannot delete this item, this is a standard menu item.");
                return;
            }

            sqlStatement = "DELETE FROM menu WHERE menu_itemID=" + menu_itemID;
            stmt.executeUpdate(sqlStatement);

            loadMenuTable();

            clearTextFields();
            menu_warning.setText("");
        }
        catch (SQLException error) {
            menu_warning.setText("Invalid menu item ID.");
        }
        close();
    }
    public void updateMenuItem(MouseEvent e) throws IOException {
        try {
            connect();

            //updating employee item from database
            int menu_itemID = Integer.parseInt(upd_menu.getText());
            String menu_item = upd_dish.getText();
            float price = Float.parseFloat(upd_price.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT menu_item FROM menu WHERE menu_itemID=" + menu_itemID;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                menu_warning.setText("Invalid menu item ID.");
                return;
            }

            if (menu_item.isEmpty()) {
                orders_warning.setText("You must enter a menu item.");
                return;
            }
            if (price <= 0) {
                menu_warning.setText("Price cannot be negative or zero.");
                return;
            }
            if(menu_itemID <= 28 && !menu_item.equals(result.getString("menu_item"))) {
                menu_warning.setText("You cannot change this item's name, this is a standard menu item.");
                return;
            }

            stmt = conn.createStatement();
            sqlStatement = "UPDATE menu SET menu_item='" + menu_item + "', price=" + price + " WHERE menu_itemID=" + menu_itemID;
            stmt.executeUpdate(sqlStatement);

            loadMenuTable();

            clearTextFields();
            menu_warning.setText("");
        }
        catch (SQLException error) {
            menu_warning.setText("Invalid value types.");
        }
        close();
    }

    /**
     * This allows the manager to delete an order from order history
     * Manager has to enter the order id, hour, day, week, month, and year
     * Takes care of error handling, ensuring that an order from a different date is not accidentally deleted
     * Includes an SQL command to delete the order from order history
     * Clears the text field once item has been deleted
     @author Olivia Lee
     */
    public void deleteHistoryItem(MouseEvent e) throws IOException {
        try {
            connect();

            int orderID = Integer.parseInt(del_id.getText());
            String hour = del_hr.getText();
            int day = Integer.parseInt(del_day.getText());
            int month = Integer.parseInt(del_mth.getText());
            int week = Integer.parseInt(del_wk.getText());
            int year = Integer.parseInt(del_yr.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT menu_item FROM orders WHERE orderID=" + orderID + " AND hour='" + hour + "' AND day=" + day + " AND month=" + month + " AND week=" + week + " AND year=" + year;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                history_warning.setText("There is no order item with the input date and order ID.");
                return;
            }

            sqlStatement = "DELETE FROM orders WHERE orderID=" + orderID + " AND hour='" + hour + "' AND day=" + day + " AND month=" + month + " AND week=" + week + " AND year=" + year;
            stmt.executeUpdate(sqlStatement);

            loadOrderHistoryTable();

            clearTextFields();
            history_warning.setText("");
        }
        catch (SQLException error) {
            history_warning.setText("There is no order with the input date and order ID.");
        }
        close();
    }

    /**
     * This allows the manager to update an order from order history
     * Manager has to enter the order id, hour, day, week, month, year, and sale
     * Takes care of error handling, ensuring that an order from a different date is not accidentally updated
     * Includes an SQL command to update the order from order history
     * Clears the text field once item has been deleted
     @author Olivia Lee
     */
    public void updateHistoryItem(MouseEvent e) throws IOException {
        try {
            connect();

            int orderID = Integer.parseInt(upd_id.getText());
            String hour = upd_hr.getText();
            int day = Integer.parseInt(upd_day.getText());
            int month = Integer.parseInt(upd_mth.getText());
            int week = Integer.parseInt(upd_wk.getText());
            int year = Integer.parseInt(upd_yr.getText());
            String menu_item = upd_hist_item.getText();
            float sale = Float.parseFloat(upd_sale.getText());

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT menu_item FROM orders WHERE orderID=" + orderID + " AND hour='" + hour + "' AND day=" + day + " AND month=" + month + " AND week=" + week + " AND year=" + year;
            ResultSet result = stmt.executeQuery(sqlStatement);
            if (!result.next()) {
                history_warning.setText("There is no menu item with the input date and order ID.");
                return;
            }

            if (sale <= 0) {
                history_warning.setText("Sale cannot be negative or zero.");
                return;
            }

            stmt = conn.createStatement();
            sqlStatement = "UPDATE orders SET menu_item='" + menu_item + "', sale=" + sale + " WHERE orderID=" + orderID + " AND hour='" + hour + "' AND day=" + day + " AND month=" + month + " AND week=" + week + " AND year=" + year;
            stmt.executeUpdate(sqlStatement);

            loadOrderHistoryTable();

            clearTextFields();
            history_warning.setText("");
        }
        catch (SQLException error) {
            history_warning.setText("Invalid value types or input date and order ID.");
        }
        close();
    }

    public void tender(MouseEvent e) {
        try {
            if (orders.isEmpty()) {
                orders_warning.setText("Must have at least one order before tender.");
                return;
            }
            this.connect();
            LocalDateTime currentTime = LocalDateTime.now();
            int year = currentTime.getYear();
            int month = currentTime.getMonthValue();
            int day = currentTime.getDayOfMonth();

            // Determine the hour in 24-hour format
            int military = currentTime.getHour();
            String period = (military >= 12) ? "PM" : "AM";
            String hour;
            if (military > 12) {
                hour = String.format("%02d", military - 12); // Convert to 12-hour format
            } else if (military == 0) {
                hour = "12"; // Midnight
            } else {
                hour = String.format("%02d", military); // Keep in 24-hour format
            }

            // Construct the complete hour string
            hour = hour + ":00 " + period;

            int week = 105;
            Statement stmt = this.conn.createStatement();
            int orderID = 1;

            try {
                System.out.println("made it");
                String sqlQuery = "SELECT MAX(orderID) AS max_order FROM orders WHERE week = 0 AND day = 27";

                for (ResultSet result = stmt.executeQuery(sqlQuery); result.next(); orderID = result.getInt("max_order")) {
                }
            } catch (SQLException var17) {
                var17.printStackTrace();
            }


            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                String menuItem = order.getMenuItem();
                float price = order.getPrice();

                // Check inventory before adding sale
                if (!checkInventory(menuItem)) {
                    // If inventory is below zero, display warning message
                    orders_warning.setText("Inventory for item " + menuItem + " is below zero.");
                    continue; // Skip processing this order
                }

                // Print and execute SQL statement
                System.out.println(menuItem + ", " + price);
                // String sqlStatement = "INSERT INTO orders VALUES ('" + hour + "', " + day + ", " + week + ", " + month + ", " + year + ", '" + menuItem + "', " + price + ", " + orderID + ")";
                String sqlStatement = "INSERT INTO orders VALUES (" + orderID + ", '" + hour + "', " + day + ", " + week + ", " + month + ", " + year + ", '" + menuItem + "', " + price + ")";
                stmt.executeUpdate(sqlStatement);
            }

            // Clear the orders list and reset count
            orders.clear();
            this.count = 1;

            orders_text.setText("");
            orders_warning.setText("");
        }
        catch (SQLException error) {
            error.printStackTrace();
        } finally {
            this.close();
        }
    }

    private boolean checkInventory(String menuItem) throws SQLException {
        String sqlStatement = "SELECT i.inventoryid, i.amount AS inventory_amount, t.count AS required_count " +
                "FROM ingredients t " +
                "JOIN inventory i ON t.ingredient = i.ingredient " +
                "WHERE t.menu_item = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
            stmt.setString(1, menuItem);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int inventoryId = result.getInt("inventoryid");
                int inventoryAmount = result.getInt("inventory_amount");
                int requiredCount = result.getInt("required_count");
                // Calculate the available inventory after deducting the required count
                int availableInventory = inventoryAmount - requiredCount;
                if (availableInventory >= 0) {
                    // Update the inventory amount in the database
                    String updateStatement = "UPDATE inventory SET amount = ? WHERE inventoryid = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateStatement)) {
                        updateStmt.setInt(1, availableInventory);
                        updateStmt.setInt(2, inventoryId);
                        updateStmt.executeUpdate();
                    }
                    return true;
                } else {
                    // If available inventory is less than zero, return false
                    return false;
                }
            }
        }
        // If the ingredient is not found, or there was an error, return false
        return false;
    }

    public void loadExcessReport(MouseEvent e) {
        StringBuilder excessStringBuilder = new StringBuilder();

        try {
            connect(); // Ensure this method properly initializes and sets 'conn'
            Statement stmt = conn.createStatement();
            String hour = from_hr.getText();
            int day = Integer.parseInt(from_day.getText());
            int month = Integer.parseInt(from_month.getText());
            int year = Integer.parseInt(from_year.getText());

            int stop_hour = LocalTime.now().getHour();
            int twelveHourFormat = stop_hour % 12 == 0 ? 12 : stop_hour % 12; // Convert 24-hour format to 12-hour format
            boolean isPM = stop_hour >= 12; // Check if it's PM
            String stop_string; // Declare stop_string here
            if(isPM) {
                stop_string = Integer.toString(twelveHourFormat) + "pm";
            } else {
                stop_string = Integer.toString(twelveHourFormat) + "am";
            }
            System.out.println("stop string is " + stop_string);
            // Ensure SQL uses correct format and comparisons based on your database's time storage format
            String sqlStatement = "SELECT DISTINCT i.ingredient, inv.amount, inv.capacity, o.hour, o.day, o.month, o.year FROM ingredients " +
                    "i JOIN orders o ON o.menu_item = i.menu_item JOIN inventory inv ON i.ingredient = inv.ingredient WHERE o.hour >= '" + hour + "' " +
                    "AND o.hour <= '12pm' AND o.day = " + day + " AND o.month = " + month + " AND o.year = " + year + " AND inv.amount >= 0.9 * inv.capacity";
            ResultSet loadExcess = stmt.executeQuery(sqlStatement);

            System.out.println("while loop");
            while (loadExcess.next()) {
                System.out.println("giving values");
                String ingredient = loadExcess.getString("ingredient");
                int amount = loadExcess.getInt("amount");
                int capacity = loadExcess.getInt("capacity");
                String hourDisplay = loadExcess.getString("hour");
                int dayDisplay = loadExcess.getInt("day");
                int monthDisplay = loadExcess.getInt("month");
                int yearDisplay = loadExcess.getInt("year");

                System.out.println("printing stuff out");
                excessStringBuilder.append(String.format("%-25s %-7s %-7s %10d %10d %10d %10d%n", ingredient, hourDisplay, dayDisplay, monthDisplay, yearDisplay, amount, capacity));
                excess_text.setText(excessStringBuilder.toString());
                System.out.println("done printing");
            }

            System.out.println("error handling");
            //error checking input good
            if (!hour.equals("11am") && !hour.equals("12pm") && !hour.equals("1pm") && !hour.equals("2pm") && !hour.equals("3pm") && !hour.equals("4pm") && !hour.equals("5pm") && !hour.equals("6pm") && !hour.equals("7pm") && !hour.equals("8pm")) {
                excess_warning.setText("The hours must be between 11am and 8pm with a <number><am/pm> format.");
                return;
            }

            if (day > 31 || day < 1)
            {
                excess_warning.setText("The days must be a number between 1 and 31.");
                return;
            }
            if (month > 12 || month < 1)
            {
                excess_warning.setText("The months must be a number between 1 and 12.");
                return;
            }

            if ((year + "").length() != 4){
                excess_warning.setText("The year must be four digits long.");
                return;
            }

            if( (year > 2025) || (year < 2023)) {
                excess_warning.setText("Year has to be within 2023 - 2025.");
                return;
            }

            clearTextFields();
            excess_warning.setText("");
        }
        catch (Exception error) {
            error.printStackTrace();
            excess_warning.setText("Invalid value types");
//            System.out.println("invalid value type");

        }
    }

    public void loadOrderTrendReport(MouseEvent e)
    {
        StringBuilder trendStringBuilder = new StringBuilder();
        System.out.println("load button clicked");

        try {
            String hour1 = st_trend_hr.getText();
            String hour2 = end_trend_hr.getText();
            int day1 = Integer.parseInt(st_trend_day.getText());
            int day2 = Integer.parseInt(st_trend_day.getText());
            int month1 = Integer.parseInt(st_trend_month.getText());
            int month2 = Integer.parseInt(st_trend_month.getText());
            int year1 = Integer.parseInt(st_trend_year.getText());
            int year2 = Integer.parseInt(st_trend_year.getText());

            if (hour1.isEmpty() || hour2.isEmpty()) {
                order_trends_warning.setText("You must enter an hour.");
                return;
            }

//            if (day1 ==  || hour2.isEmpty()) {
//                order_trends_warning.setText("You must enter an hour.");
//                return;
//            }

            connect();
            Statement stmt = conn.createStatement();
//            String sqlStatement = "SELECT o1.menu_item AS item1, o2.menu_item AS item2, COUNT(*) AS times_ordered_together FROM orders " +
//                    "AS o1 JOIN orders AS o2 ON o1.orderID = o2.orderID AND o1.menu_item < o2.menu_item WHERE o1.day BETWEEN '2' AND '3' " +
//                    "AND o1.month BETWEEN '2' AND '3' AND o1.year BETWEEN '2024' AND '2024' GROUP BY o1.menu_item, o2.menu_item ORDER BY " +
//                    "times_ordered_together DESC";
            String sqlStatement = "SELECT o1.menu_item AS item1, o2.menu_item AS item2, COUNT(*) AS times_ordered_together FROM orders AS o1 JOIN orders AS o2 ON o1.orderID = o2.orderID AND o1.menu_item < o2.menu_item WHERE o1.hour BETWEEN '"+ hour1 + "' AND '" + hour2 + "' AND o1.day BETWEEN '"+ day1 + "' AND '"+ day2 + "'AND o1.month BETWEEN '" + month1 + "' AND '" + month2 + "' AND o1.year BETWEEN '" + year1 + "' AND '" + year2 + "' GROUP BY o1.menu_item, o2.menu_item ORDER BY times_ordered_together DESC";

            System.out.println("sending sql command");
            ResultSet loadOrderTrend = stmt.executeQuery(sqlStatement);
            System.out.println("Entering while loop");
            while(loadOrderTrend.next())
            {
                String menu1 = loadOrderTrend.getString("item1");
                String menu2 = loadOrderTrend.getString("item2");
                int frequency = loadOrderTrend.getInt("times_ordered_together");

                System.out.println("printing stuff out");
                trendStringBuilder.append(String.format("%-25s %-25s %10d%n", menu1, menu2, frequency));
                trends_text.setText(trendStringBuilder.toString());
                System.out.println("done printing");
            }

            if (!hour1.equals("11am") && !hour1.equals("12pm") && !hour1.equals("1pm") && !hour1.equals("2pm") && !hour1.equals("3pm") && !hour1.equals("4pm") && !hour1.equals("5pm") && !hour1.equals("6pm") && !hour1.equals("7pm") && !hour1.equals("8pm")) {
                order_trends_warning.setText("The hours must be between 11am and 8pm with a <number><am/pm> format.");
                return;
            }

            if (!hour2.equals("11am") && !hour2.equals("12pm") && !hour2.equals("1pm") && !hour2.equals("2pm") && !hour2.equals("3pm") && !hour2.equals("4pm") && !hour2.equals("5pm") && !hour2.equals("6pm") && !hour2.equals("7pm") && !hour2.equals("8pm")) {
                order_trends_warning.setText("The hours must be between 11am and 8pm with a <number><am/pm> format.");
                return;
            }

            if (hour1.compareTo(hour2) > 0) {
                order_trends_warning.setText("The second hour cannot be earlier than the first hour.");
                return;
            }

            if (day1 > 31 || day1 < 1)
            {
                order_trends_warning.setText("The days must be a number between 1 and 31.");
                return;
            }
            if (month1 > 12 || month1 < 1 || month2 > 12 || month2 < 1)
            {
                order_trends_warning.setText("The months must be a number between 1 and 12.");
                return;
            }

            if(year1 > year2)
            {
                order_trends_warning.setText("Second year must come after first year");
                return;
            }
            if ((year1 + "").length() != 4 || (year2 + "").length() != 4) {
                order_trends_warning.setText("The year must be four digits long.");
                return;
            }

            if( (year1 > 2025)|| (year2 > 2025) || (year1 < 2023)|| (year2 < 2023)) {
                order_trends_warning.setText("Year has to be within 2023 - 2025.");
                return;
            }

            if (year1 == year2 && month1 > month2) {
                order_trends_warning.setText("The second month cannot be earlier than the first month in the same year.");
                return;
            }

            if (year1 == year2 && month1 == month2 && day1 > day2) {
                order_trends_warning.setText("The second day cannot be earlier than the first in the same month and year.");
                return;
            }

            clearTextFields();
            order_trends_warning.setText("");
        }
        catch (Exception error) {
            order_trends_warning.setText("Invalid value types");
        }
    }

    public void connect() {
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
    }
    public void close() {
        //closing the database
        try {
            conn.close();
        } catch(Exception error2) {
            System.out.println("Connection NOT Closed.");
        }
    }
}
