import random
from datetime import datetime, timedelta
#Total of 1 million dollars = 1000000
#52 weeks of sales data
#Include two peak days (beginning of each semester)
#Inventory for at least 20 different menu items





def sales_history():
    start_date = datetime.now() - timedelta(days=365)
    hours = ["11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm"]
    
    weekly_sales_inserts = []
    daily_sales_inserts = []
    hourly_sales_inserts = []

    yearly_sales = 0
    
    for i in range(52):
        weekly_sales = 0
        for day in range(7):
            daily_sales = 0
            for hour in hours:
                hourly_sales = random.randint(83, 463)
                daily_sales += hourly_sales
                hourly_sales_inserts.append(f"INSERT INTO hourly_sales (date_time, sales_amount) VALUES ('{start_date + timedelta(weeks=i, days=day, hours=hours.index(hour))}', {hourly_sales});")
            daily_sales_inserts.append(f"INSERT INTO daily_sales (date, sales_amount) VALUES ('{start_date + timedelta(weeks=i, days=day)}', {daily_sales});")
            weekly_sales += daily_sales
        weekly_sales_inserts.append(f"INSERT INTO weekly_sales (week_start, total_sales) VALUES ('{start_date + timedelta(weeks=i)}', {weekly_sales});")
        yearly_sales += weekly_sales

    return weekly_sales_inserts, daily_sales_inserts, hourly_sales_inserts
def main():
    f, k, j = sales_history()
    print(f)
    print(k)
    print(j)
    return 0
    
if __name__ == "__main__":
    main()


def Inventory_items():
    menu_items=[]
    inventory_items=[]

    import random
    
    menu_items = [
        "Rev's Burger",
        "Double Stack Cheese Burger",
        "Classic Burger",
        "Bacon Chesseburger",
        "Three Tender Basket",
        "Four Steak Finger Basket",
        "Gig 'Em Patty Melt", 
        "Howdy Spicy Ranch",
        "Chicken Strip Snadwich",
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


    inventory_items = [

        "Rev's Burger": ["Burger Bread", "1 Patty", "American Cheese", "Gig-em Sauce", "Pickle"],
        "Double Stack Cheese Burger": ["2 Patty ", "2 American Cheese", "Gig 'Em Sauce", "Pickles"],
        "Classic Burger" : ["Burger Bread", "Lettuce ", "Tomato", "Pickles", "Onion"],
        "Bacon Chesseburger": ["Burger Bread", "2 American Cheese", "Gig 'Em Sauce", "Pickles"],
        "Three Tender Basket": ["3 Chicken Tenders", "Fries", "Texas Toast", "Gravy"],
        "Four Steak Finger Basket": ["4 Steak Finger", "Fries", "Texas Toast", "Gravy"],
        "Gig 'Em Patty Melt": ["Sandwich Bread", "1 Patty", "Texas Toast", "American Swiss Cheese", "Gig-em Sauce", "Grilled Onions"], 
        "Howdy Spicy Ranch Chicken Strip Sandwhich": ["Sandwich Bread", "3 Chicken Tenders", "Toasted Bun", "Spicy Ranch Sauce", "Pepper Jack Cheese"],
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
    ]
    
    inserts = []

    for item in menu_items:
        inserts.append(f"INSERT INTO InventoryItems (Menu Item) VALUES ('{item}');")
        for ingredient in inventory_items[item]:
            inserts.append(f"INSERT INTO Ingredients (Menu Item, Inventory Name) VALUES ('{item}', '{ingredient}');")


