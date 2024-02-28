// DELETE MENU ITEM

public void deleteMenuItem(MouseEvent e) throws IOException {
    try {
        connect();

        //deleting menu item in database
        int menu_itemID = Integer.parseInt(del_menu.getText());

        Statement stmt = conn.createStatement();
        String sqlStatement = "DELETE FROM menu WHERE menu_itemID=" + menu_itemID;
        stmt.executeUpdate(sqlStatement);

        menu_string = "";
        int del_index = 0;
        for(int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getMenu_ItemID() == menu_itemID) {
                del_index = i;
            }
            else {
                menu_string += " " + menu.get(i).getMenu_ItemID();
                for(int j = 1; j <= 3-(menu.get(i).getMenu_ItemID()+"").length(); j++) {
                    menu_string += " ";
                }
                menu_string += "   " + menu.get(i).getMenu_Item();
                for(int j = 1; j <= 46-menu.get(i).getName().length(); j++) {
                    menu_string += " ";
                }
                menu_string += "     " + menu.get(i).getPrice() + "\n";
            }
            menu_text.setText(menu_string);
        }
    }
    catch (SQLException error) {
        // handle SQL exception
        error.printStackTrace();
        
    close();
}