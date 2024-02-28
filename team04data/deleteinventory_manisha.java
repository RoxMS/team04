public void deleteInventoryItem(MouseEvent e) throws IOException {
    try {
        connect();

        //deleting inventory item in database
        int inventoryID = Integer.parseInt(del_inv.getText());

        Statement stmt = conn.createStatement();
        String sqlStatement = "DELETE FROM inventory WHERE inventoryID=" + inventoryID;
        stmt.executeUpdate(sqlStatement);

        int del_index = 0;
        inventory_string = "";
        for(int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getInventoryID() == inventoryID) {
                del_index = i;
            }
            else {
                //changing inventory table GUI
                inventory_string += " " + inventory.get(i).getInventoryID();
                for(int j = 1; j <= 3-(inventory.get(i).getInventoryID()+"").length(); j++) {
                    inventory_string += " ";
                }
                inventory_string += "   " + inventory.get(i).getIngredient();
                for(int j = 1; j <= 35-(inventory.get(i).getIngredient()+"").length(); j++) {
                    inventory_string += " ";
                }
                inventory_string += "        " + inventory.get(i).getAmount();
                for(int j = 1; i <= 3-(inventory.get(i).getAmount()+"").length(); j++) {
                    inventory_string += " ";
                }
                inventory_string += "            " + inventory.get(i).getCapacity() + "\n";
            }
        }
        inventory_text.setText(inventory_string);
    }
    catch (SQLException error) {
        // handle SQL exception
        error.printStackTrace();
    }
    close();
}