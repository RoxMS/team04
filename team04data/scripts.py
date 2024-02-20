import random
from datetime import date, timedelta
#Total of 1 million dollars = 1000000
#52 weeks of sales data
#Include two peak days (beginning of each semester)
#Inventory for at least 20 different menu items





menu_items = [
    "Rev's Burger",
    "Double Stack Cheese Burger",
    "Classic Burger",
    "Bacon Chesseburger",
    "Three Tender Basket",
    "Four Steak Finger Basket",
    "Gig 'Em Patty Melt", 
    "Howdy Spicy Ranch Chicken Strip Sandwich",
    "Classic Crispy or Grilled Chicken Tender Sandwich",
    "Grilled Cheese",
    "Aggie Shake",
    "Double Scoop Ice Cream Cup",
    "Chocolate Chip Chunk Cookie",
    "Chocolate Fudge Brownie",
    "Salad Bar",
    "Gig 'Em Sauce",
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
    "Onion Ring",
    "Kettle Chips"
]

menu_prices = {
    "Rev's Burger" : 5.59,
    "Double Stack Cheese Burger" : 8.79,
    "Classic Burger" : 5.49,
    "Bacon Chesseburger" : 6.99,
    "Three Tender Basket" : 6.79,
    "Four Steak Finger Basket" : 7.29,
    "Gig 'Em Patty Melt" : 6.29,
    "Howdy Spicy Ranch Chicken Strip Sandwich" : 6.99,
    "Classic Crispy or Grilled Chicken Tender Sandwich" : 5.79,
    "Grilled Cheese" : 3.49,
    "Aggie Shake" : 3.99,
    "Double Scoop Ice Cream Cup" : 2.99,
    "Chocolate Chip Chunk Cookie" : 1.99,
    "Chocolate Fudge Brownie" : 1.99,
    "Salad Bar" : 8.99,
    "Gig 'Em Sauce" : 0.69,
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
    "Onion Ring" : 2.99,
    "Kettle Chips" : 2.99
}

current_date = date.today() - timedelta(days=365)
hours = ["11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm"]


# 52 weeks in a year
for week in range(52):

    # 7 work days in a week
    for day in range(7):

        # 10 hours per work day
        for hour in hours:
            orderID = 0

            # menu item orders per hour
            for i in range(len(menu_items)):
                random_num = random.randint(0, 5)
                
                # number of orders of a specific menu item
                for num in range(random_num):
                    print(f"INSERT INTO orders (hour, day, month, year, item, sale) VALUES ('{hour}', {current_date.day}, {current_date.month}, {current_date.year}, '{menu_items[i]}', {menu_prices[menu_items[i]]});")
        
        # changes date at the end of each day
        current_date += timedelta(days=1)


                

    
    


inventory_items = {

    "Rev's Burger": ["Burger Bread", "1 Patty", "American Cheese", "Gig-em Sauce", "Pickle"],
    "Double Stack Cheese Burger": ["2 Patty ", "2 American Cheese", "Gig 'Em Sauce", "Pickles"],
    "Classic Burger" : ["Burger Bread", "Lettuce ", "Tomato", "Pickles", "Onion"],
    "Bacon Chesseburger": ["Burger Bread", "2 American Cheese", "Gig 'Em Sauce", "Pickles"],
    "Three Tender Basket": ["3 Chicken Tenders", "Fries", "Texas Toast", "Gravy"],
    "Four Steak Finger Basket": ["4 Steak Finger", "Fries", "Texas Toast", "Gravy"],
    "Gig 'Em Patty Melt": ["Sandwich Bread", "1 Patty", "Texas Toast", "American Swiss Cheese", "Gig-em Sauce", "Grilled Onions"], 
    "Howdy Spicy Ranch Chicken Strip Sandwich": ["Sandwich Bread", "3 Chicken Tenders", "Toasted Bun", "Spicy Ranch Sauce", "Pepper Jack Cheese"],
    "Classic Crispy or Grilled Chicken Tender Sandwich": ["Sandwich Bread", "3 Chicken Tenders", "Lettuce", "Tomato", "Pickles", "Onion"],
    "Grilled Cheese": ["American Grilled Cheese", "Texas Toast"],
    "Aggie Shake" : ["Milk", "Sugar", "Chocolate", "Vanilla", "Straberry", "Capuccino"],
    "Double Scoop Ice Cream Cup" : ["Ice Cream", "Chocolate Syrup"],
    "Chocolate Chip Chunk Cookie": ["Chocolate Chip Cookie"],
    "Chocolate Fudge Brownie": ["Chocolate Fudge Brownie"],
    "Salad Bar": ["Lettuce", "Tomatoes", "Cucumbers", "Carrots", "Salad Dressings", "Cheese", "Croutons"],
    "Gig 'Em Sauce" :["Gig 'Em Sauce"],
    "Buffalo" :["Gig 'Em Sauce"] ,
    "Ranch":["Gig 'Em Sauce"],
    "BBQ Sauce":["BBQ Sauce"],
    "Honey Mustard":["Honey Mustard"],
    "Spicy Ranch":["Spicy Ranch"],
    "Fountain Drink":["Fountain Drink Flavorings","Plastic Cups", "Lids", "Straws"],
    "Drip Coffee":["Coffee Brewed", "Coffee Cup", "Coffee Lids"],
    "Cold Brew":["Coffee Brewed","Plastic Cups", "Plastic Cup Lids", "Straws"],
    "Seasoned Fries":["Potatoes", "Oil", "Salt", "Pepper"],
    "Tater Tots":["Potatoes", "Oil", "Salt", "Pepper"],
    "Onion Ring":["Onions", "Oil", "Salt", "Pepper"],
    "Kettle Chips": ["Onions", "Oil", "Salt", "Pepper"],
    "Cups": ["Plastic Cups", "Coffe Cups"],
    "Lids": ["Coffee Cup Lids", "Plastic Cup Lids"],
    "Straws": ["Plastic Straws"],
    "Napkins": ["Paper Napkins"],
    "Flatware": ["Plastic Forks", "Plastic Knives", "Plastic Spoons"],
    "To-go boxes": ["Cardboard To-go Boxes"],
    "Bags": ["Plastic Bags"]
}

inserts = []

for item in menu_items:
    print(f"INSERT INTO InventoryItems (Menu Item) VALUES ('{item}');")
    for ingredient in inventory_items[item]:
        print(f"INSERT INTO Ingredients (Menu Item, Inventory Name) VALUES ('{item}', '{ingredient}');")
