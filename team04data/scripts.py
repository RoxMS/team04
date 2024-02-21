import random
from datetime import date, timedelta

# Redirecting output to a file
with open("output.sql", "w") as sql_file:
    sql_file.write("CREATE TABLE orders (hour varchar(255), day int, week int, month int, year int, item varchar(255), sale int, orderID int);\n")

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
        "Revs Burger": 5.59,
        "Double Stack Cheese Burger": 8.79,
        "Classic Burger": 5.49,
        "Bacon Chesseburger": 6.99,
        "Three Tender Basket": 6.79,
        "Four Steak Finger Basket": 7.29,
        "Gig Em Patty Melt": 6.29,
        "Howdy Spicy Ranch Chicken Strip Sandwich": 6.99,
        "Classic Crispy or Grilled Chicken Tender Sandwich": 5.79,
        "Grilled Cheese": 3.49,
        "Aggie Shake": 3.99,
        "Double Scoop Ice Cream Cup": 2.99,
        "Chocolate Chip Chunk Cookie": 1.99,
        "Chocolate Fudge Brownie": 1.99,
        "Salad Bar": 8.99,
        "Gig Em Sauce": 0.69,
        "Buffalo": 0.69,
        "Ranch": 0.69,
        "BBQ Sauce": 0.69,
        "Honey Mustard": 0.69,
        "Spicy Ranch": 0.69,
        "Fountain Drink": 2.25,
        "Drip Coffee": 2.29,
        "Cold Brew": 3.65,
        "Seasoned Fries": 2.99,
        "Tater Tots": 2.99,
        "Onion Rings": 2.99,
        "Kettle Chips": 2.99
    }

    current_date = date.today() - timedelta(days=365)
    hours = ["11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm"]

    # 52 weeks in a year
    for week in range(52):

        # 7 work days in a week
        for day in range(7):
            orderID = 1

            # 10 hours per work day
            for hour in hours:

                # menu item orders per hour
                for i in range(len(menu_items)):
                    if (current_date.day == 31 and current_date.month == 8) or (current_date.day == 31 and current_date.month == 1):
                        random_num = random.randint(5, 6)
                    else:
                        random_num = random.randint(0, 5)

                    # number of orders of a specific menu item
                    for num in range(random_num):
                        sql_file.write(f"INSERT INTO orders VALUES ('{hour}', {current_date.day}, {week}, {current_date.month}, {current_date.year}, '{menu_items[i]}', {menu_prices[menu_items[i]]}, {orderID});\n")
                        orderID += 1

            # changes date at the end of each day
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


sql_file.write("CREATE TABLE ingredients (item varchar(255), ingredient varchar(255), count int);\n")
sql_file.write("CREATE TABLE inventory (ingredient varchar(255), amount int, capacity int);\n")

for item in menu_items:
        for ingredient in inventory_items[item]:
            if ingredient[0] == "2":
                sql_file.write(f"INSERT INTO ingredients VALUES ('{item}', '{ingredient[2:]}', '{2}');\n")
            elif ingredient[0] == "3":
                sql_file.write(f"INSERT INTO ingredients VALUES ('{item}', '{ingredient[2:]}', '{3}');\n")
            elif ingredient[0] == "4":
                sql_file.write(f"INSERT INTO ingredients VALUES ('{item}', '{ingredient[2:]}', '{4}');\n")
            else:
                sql_file.write(f"INSERT INTO ingredients VALUES ('{item}', '{ingredient}', '{1}');\n")

for ingredient in ingredients:
        sql_file.write(f"INSERT INTO inventory VALUES ('{ingredient}', '{30}', '{30}');\n")
