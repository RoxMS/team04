package application.revs_pos_331;

import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDateTime;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;

public class SceneController {

    private Connection conn = null;

    public static ArrayList<Order> orders = new ArrayList<>();
    @FXML private TableView<Misc> misc_list = new TableView<Misc>();
    @FXML private TableColumn misc_col = new TableColumn("Menu Item");


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

    //warnings
    @FXML private Label login_warning = new Label("");
    @FXML private Label inventory_warning = new Label("");
    @FXML private Label employees_warning = new Label("");
    @FXML private Label menu_warning = new Label("");
    @FXML private Label orders_warning = new Label("");
    @FXML private Label history_warning = new Label("");


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
    @FXML private TextField del_menu = new TextField();
    @FXML private TextField upd_menu = new TextField();
    @FXML private TextField add_dish = new TextField();
    @FXML private TextField upd_dish = new TextField();
    @FXML private TextField add_price = new TextField();
    @FXML private TextField upd_price = new TextField();

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
            misc_col.setCellValueFactory(new PropertyValueFactory<>("menu_item"));
            misc_list.getColumns().addAll(misc_col);

            this.connect();
            String sqlStatement = "SELECT menu_item FROM menu WHERE menu_itemid > 29";
            PreparedStatement stmt = this.conn.prepareStatement(sqlStatement);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int menuItemId = result.getInt("menu_itemid");
                String menuItem = result.getString("menu_item");
                float price = result.getFloat("price");

                misc_list.getItems().add(new Misc(menuItem));
            }

        } catch (Exception error) {
            orders_warning.setText("Unable to load items.");
        }
    }

    public void addMiscItem(MouseEvent e) {
        String name = misc_list.getSelectionModel().getSelectedItem().getMenu_Item();

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
                String sqlStatement = "INSERT INTO orders VALUES ('" + hour + "', " + day + ", " + week + ", " + month + ", " + year + ", '" + menuItem + "', " + price + ", " + orderID + ")";
                stmt.executeUpdate(sqlStatement);
            }

            // Clear the orders list and reset count
            orders.clear();
            this.count = 1;

        } catch (SQLException error) {
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

