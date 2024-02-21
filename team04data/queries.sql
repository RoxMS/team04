'''"52 weeks of sales history": select the count of orders grouped by week
e.g. "week 1 has 123 orders"'''
SELECT 
    YEARWEEK(date) AS week_number, 
    COUNT(*) AS order_count
FROM daily_sales
GROUP BY week_number;
-- This query selects the week number using the YEARWEEK function applied to the date column, 
-- then counts the orders grouped by the week number from the daily_sales table.

'''"Realistic sales history": select count of orders, sum of order total grouped by hour
e.g. "12pm has 12345 orders totaling $86753"'''
SELECT 
    day, month, year, menu_item, COUNT(menu_item) AS orderCount, sale, COUNT(sale) AS totalSale
WHERE
    day = 1,
    month = 1,
    year = 2023

FROM 
    orders
GROUP BY 
    year, month, day, hour
ORDER BY 
    orderCount, totalSale;

-- selects the hour of the day using the DATE_FORMAT function applied to the date_time column to display it in the format like "12pm", 
-- then counts the number of orders and calculates the total sales amount grouped by the hour of the day from the hourly_sales table. 

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