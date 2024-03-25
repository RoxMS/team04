import random
from datetime import date, timedelta

# Open a file in write mode to store the SQL statements
with open('output.sql', 'w') as sql_file:
    # Write CREATE TABLE statement for 'orders' table
    sql_file.write("CREATE TABLE orders (orderID int, hour varchar(255), day int, week int, month int, year int, menu_item varchar(255), sale float);\n")

    # Define menu items and prices
    menu_items = [
    "Revs Burger",
    "Double Stack Cheese Burger",
    "Classic Burger",
    "Bacon Chesseburger",
    "Three Tender Basket",
    "Four Steak Finger Basket",
    "Gig Em Patty Melt", 
    "Howdy Spicy Ranch Chicken Strip Sandwich",
    "Classic Crispy or Grilled Chicken Tender Sandwich",
    "Grilled Cheese",
    "Aggie Shake",
    "Double Scoop Ice Cream Cup",
    "Chocolate Chip Chunk Cookie",
    "Chocolate Fudge Brownie",
    "Salad Bar",
    "Gig Em Sauce",
    "Buffalo",
    "Ranch",
    "BBQ Sauce",
    "Honey Mustard",
    "Spicy Ranch",
    "Fountain Drink",
    "Drip Coffee",
    "Cold Brew",
    "Seasoned Fries",
    "Tater Tots",
    "Onion Rings",
    "Kettle Chips"
    ]

    menu_prices = {
    "Revs Burger" : 5.59,
    "Double Stack Cheese Burger" : 8.79,
    "Classic Burger" : 5.49,
    "Bacon Chesseburger" : 6.99,
    "Three Tender Basket" : 6.79,
    "Four Steak Finger Basket" : 7.29,
    "Gig Em Patty Melt" : 6.29,
    "Howdy Spicy Ranch Chicken Strip Sandwich" : 6.99,
    "Classic Crispy or Grilled Chicken Tender Sandwich" : 5.79,
    "Grilled Cheese" : 3.49,
    "Aggie Shake" : 3.99,
    "Double Scoop Ice Cream Cup" : 2.99,
    "Chocolate Chip Chunk Cookie" : 1.99,
    "Chocolate Fudge Brownie" : 1.99,
    "Salad Bar" : 8.99,
    "Gig Em Sauce" : 0.69,
    "Buffalo" : 0.69,
    "Ranch" : 0.69,
    "BBQ Sauce" : 0.69,
    "Honey Mustard" : 0.69,
    "Spicy Ranch" : 0.69,
    "Fountain Drink" : 2.25,
    "Drip Coffee" : 2.29,
    "Cold Brew" : 3.65,
    "Seasoned Fries" : 2.99,
    "Tater Tots" : 2.99,
    "Onion Rings" : 2.99,
    "Kettle Chips" : 2.99
    }

    current_date = date.today() - timedelta(days=730)
    hours = ["11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm"]

    # Loop over 104 weeks
    for week in range(104):
        # Loop over 7 work days in a week
        for day in range(7):
            orderID = 1
            # Loop over 10 hours per work day
            for hour in hours:
                insert_statement = "INSERT INTO orders VALUES "
                # Loop over menu items
                for i in range(len(menu_items)):
                    # Generate random number of orders
                    random_num = random.randint(0, 5)
                    # Generate orders for a specific menu item
                    for num in range(random_num):
                        insert_statement += f"({orderID}, '{hour}', {current_date.day}, {week+1}, {current_date.month}, {current_date.year}, '{menu_items[i]}', {menu_prices[menu_items[i]]}), "
                        orderID += 1
                # Write the insert statement to the SQL file
                sql_file.write(insert_statement[:-2] + ";\n")
                # Move to the next hour
                current_date += timedelta(hours=1)
            # Move to the next day
            current_date += timedelta(days=1)
    inventory_items = {
    "Revs Burger": ["2 Burger Bread", "Patty", "American Cheese", "Lettuce", "Tomato", "Gig em Sauce", "Pickle"],
    "Double Stack Cheese Burger": ["Burger Bread", "2 Patty", "American Cheese", "2 American Cheese", "Lettuce", "Tomato", "Gig Em Sauce", "Pickles"],
    "Classic Burger" : ["2 Burger Bread", "Patty", "American Cheese", "Lettuce", "Tomato", "Pickle"],
    "Bacon Chesseburger": ["2 Burger Bread", "Patty", "Bacon", "American Cheese", "Lettuce", "Tomato", "Gig Em Sauce", "Pickle"],
    "Three Tender Basket": ["3 Chicken Tender", "Fries", "Sandwich Bread", "Gravy"],
    "Four Steak Finger Basket": ["4 Steak Finger", "Fries", "Sandwich Bread", "Gravy"],
    "Gig Em Patty Melt": ["2 Sandwich Bread", "1 Patty", "American Cheese", "Gig-em Sauce", "Onion"], 
    "Howdy Spicy Ranch Chicken Strip Sandwich": ["Sandwich Bread", "3 Chicken Tender", "Spicy Ranch", "American Cheese"],
    "Classic Crispy or Grilled Chicken Tender Sandwich": ["Sandwich Bread", "3 Chicken Tender", "Lettuce", "Tomato", "Pickle", "Onion"],
    "Grilled Cheese": ["Sandwich Bread", "American Cheese"],
    "Aggie Shake" : ["Chocolate Mix", "Vanilla Mix", "Straberry Mix"],
    "Double Scoop Ice Cream Cup" : ["Ice Cream", "Chocolate Syrup"],
    "Chocolate Chip Chunk Cookie": ["Chocolate Chip Cookie"],
    "Chocolate Fudge Brownie": ["Chocolate Fudge Brownie"],
    "Salad Bar": ["Lettuce", "Tomato", "Carrot", "Salad Dressing", "Crouton"],
    "Gig Em Sauce" :["Gig Em Sauce"],
    "Buffalo" :["Buffalo"] ,
    "Ranch":["Ranch"],
    "BBQ Sauce":["BBQ Sauce"],
    "Honey Mustard":["Honey Mustard"],
    "Spicy Ranch":["Spicy Ranch"],
    "Fountain Drink":["Fountain Drink Flavorings","Plastic Cup", "Lid", "Straw"],
    "Drip Coffee":["Coffee", "Plastic Cup", "Lid"],
    "Cold Brew":["Coffee","Plastic Cup", "Lid"],
    "Seasoned Fries":["Fries"],
    "Tater Tots":["Tater Tots"],
    "Onion Rings":["Onion Rings"],
    "Kettle Chips": ["Kettle Chips"],
    "Napkins": ["Paper Napkins"],
    "Flatware": ["Plastic Forks", "Plastic Knives", "Plastic Spoons"],
    "To-go boxes": ["Cardboard To-go Boxes"],
    "Bags": ["Plastic Bags"]
}
    ingredients = [
    "Burger Bread",
    "Patty",
    "Bacon",
    "American Cheese",
    "Lettuce",
    "Tomato",
    "Gig Em Sauce",
    "Pickle",
    "Chicken Tender",
    "Fries",
    "Sandwich Bread",
    "Gravy",
    "Steak Finger",
    "Onion",
    "Spicy Ranch",
    "Chocolate Mix",
    "Vanilla Mix",
    "Straberry Mix",
    "Ice Cream",
    "Chocolate Syrup",
    "Chocolate Chip Cookie",
    "Chocolate Fudge Brownie",
    "Carrot", 
    "Salad Dressing", 
    "Crouton",
    "Buffalo",
    "Ranch",
    "BBQ Sauce",
    "Honey Mustard",
    "Fountain Drink Flavoring",
    "Plastic Cup",
    "Lid", 
    "Straw",
    "Coffee",
    "Tater Tots",
    "Onion Rings",
    "Kettle Chips",
    "Paper Napkins",
    "Plastic Forks",
    "Plastic Knives",
    "Plastic Spoons",
    "Cardboard To-go Boxes",
    "Plastic Bags"
]

    menu_category = {
    "Revs Burger" : "Burgers",
    "Double Stack Cheese Burger" : "Burgers",
    "Classic Burger" : "Burgers",
    "Bacon Chesseburger" : "Burgers",
    "Three Tender Basket" : "Baskets",
    "Four Steak Finger Basket" : "Baskets",
    "Gig Em Patty Melt" : "Sandwiches",
    "Howdy Spicy Ranch Chicken Strip Sandwich" : "Sandwiches",
    "Classic Crispy or Grilled Chicken Tender Sandwich" : "Sandwiches",
    "Grilled Cheese" : "Sandwiches",
    "Aggie Shake" : "Dessert",
    "Double Scoop Ice Cream Cup" : "Dessert",
    "Chocolate Chip Chunk Cookie" : "Dessert",
    "Chocolate Fudge Brownie" : "Dessert",
    "Salad Bar" : 8.99,
    "Gig Em Sauce" : "Sauces",
    "Buffalo" : "Sauces",
    "Ranch" : "Sauces",
    "BBQ Sauce" : "Sauces",
    "Honey Mustard" : "Sauces",
    "Spicy Ranch" : "Sauces",
    "Fountain Drink" : "Drinks",
    "Drip Coffee" : "Drinks",
    "Cold Brew" : "Drinks",
    "Seasoned Fries" : "Sides",
    "Tater Tots" : "Sides",
    "Onion Rings" : "Sides",
    "Kettle Chips" : "Sides"
    }

    # Create 'ingredient' table
    sql_file.write("CREATE TABLE ingredients (menu_item varchar(255), ingredient varchar(255), count int);\n")
    # Write CREATE TABLE statement for 'menu' table
    sql_file.write("CREATE TABLE menu (menu_itemID int NOT NULL AUTO_INCREMENT, menu_item varchar(255), price float, category varchar(255), PRIMARY KEY (menu_itemID));\n")
    # Write CREATE TABLE statement for 'inventory' table
    sql_file.write("CREATE TABLE inventory (inventoryID SERIAL PRIMARY KEY, ingredient varchar(255), amount int, capacity int);\n")

    # Generate and write INSERT INTO statements for 'menu' table
    insert_statement = "INSERT INTO menu (menu_item, price, category) VALUES "
    for item, price in menu_prices.items():
        insert_statement += f"('{item}', {price}, '{menu_category[item]}'), "
    sql_file.write(insert_statement[:-2] + ";\n")

    # Generate and write INSERT INTO statements for 'ingredient' table
    insert_statement = "INSERT INTO ingredients (menu_item, ingredient, count) VALUES "
    for item, ingredients_list in inventory_items.items():
        for ingredient in ingredients_list:
            count = ingredient.split()[0]
            ingredient_name = ''.join(ingredient.split()[1:])
            insert_statement += f"('{item}', '{ingredient_name}', {count}), "
    sql_file.write(insert_statement[:-2] + ";\n")

    # Generate and write INSERT INTO statements for 'inventory' table
    insert_statement = "INSERT INTO inventory (ingredient, amount, capacity) VALUES "
    for ingredient in ingredients:
        insert_statement += f"('{ingredient}', '{30}', '{30}'), "
    sql_file.write(insert_statement[:-2] + ";\n")
