'''"52 weeks of sales history": select the count of orders grouped by week
e.g. "week 1 has 123 orders"'''
SELECT
     week, COUNT(DISTINCT order_id) AS total_orders -- selects the week column from the restaurant_orders table and calculates the count of distinct order_id values for each week. 
FROM 
    orders --gets it from the orders table
GROUP BY week; --group each count by a week


'''"Realistic sales history": select count of orders, sum of order total grouped by hour
e.g. "12pm has 12345 orders totaling $86753"'''
SELECT 
    hour,
    COUNT(DISTINCT order_id) AS total_orders, --count of the total orders based on their distinct ids and save it under total_orders for each hours
    SUM(sale) AS total_sales --add up the total sales for each hours
FROM 
    orders --get from the orders table
GROUP BY 
    hour; --group it by hours

'''"2 peak days": select top 10 sums of order total grouped by day in descending order by order total
e.g. "30 August has $123456 of sales"'''
SELECT day, month, year, SUM(sales)
FROM orders
GROUP BY day, month, year
ORDER BY year, month, day
LIMIT 10;

'''"Inventory items for 20 menu items": select count of inventory items from inventory and menu grouped by menu item
e.g. "chicken fingers uses 12 items"'''

SELECT menu_item, COUNT(*) AS inventory_items_count    --  Counting the occurrences of each menu item in the Inventory table
FROM Ingredients
JOIN Inventory ON Ingredients.menu_item = Inventory.Ingredient  -- Joining Ingredients and Inventory tables on menu_item
GROUP BY menu_item                                  -- Grouping the results by menu_item
ORDER BY inventory_items_count DESC    -- Ordering the results by inventory_items_count in descending order
LIMIT 20;            -- Limiting the output to the top 20 menu items with the highest inventory items count