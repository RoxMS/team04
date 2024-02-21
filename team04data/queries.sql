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
    DATE_FORMAT(date_time, '%l%p') AS hour_of_day,
    COUNT(*) AS num_orders,
    SUM(sales_amount) AS total_sales
FROM 
    hourly_sales
GROUP BY 
    hour_of_day
ORDER BY 
    hour_of_day;

-- selects the hour of the day using the DATE_FORMAT function applied to the date_time column to display it in the format like "12pm", 
-- then counts the number of orders and calculates the total sales amount grouped by the hour of the day from the hourly_sales table. 

'''"2 peak days": select top 10 sums of order total grouped by day in descending order by order total
e.g. "30 August has $123456 of sales"'''
SELECT 
    DATE(date_time) AS sales_date,
    SUM(sgitales_amount) AS total_sales
FROM 
    daily_sales
GROUP BY 
    sales_date
ORDER BY 
    total_sales DESC
LIMIT 
    10;


'''"Inventory items for 20 menu items": select count of inventory items from inventory and menu grouped by menu item
e.g. "chicken fingers uses 12 items"'''

SELECT menu_item, COUNT(*) AS inventory_items_count
FROM Menu 
JOIN Inventory ON Menu.menu_item = Inventory.menu_item
GROUP BY menu_item 
ORDER BY inventory_items_count DESC 
LIMIT 20;

'''"Highest sold item of the day"'''
SELECT day, month, year, menu_item, COUNT(menu_item) as itemsSold
FROM orders
GROUP BY day, month, yeaR, menu_item
ORDER BY year DESC, month DESC, day DESC, itemsSold DESC
LIMIT 1;        


'''"Highest sold item of the month"'''
SELECT month, year, menu_item, COUNT(menu_item) as itemsSold
FROM orders
GROUP BY month, year, menu_item
ORDER BY year DESC, month DESC, itemsSold DESC
LIMIT 1;        


'''"Most profitable month"'''
SELECT month, year, SUM(sale) as totalSale          -- This selects the month and year calculates the sum of sales for each month and year, labeling this sum as totalSale
FROM orders
GROUP BY month, year                -- ensures that sales are aggregated for each specific month within each year
ORDER BY totalSale DESC
LIMIT 1;
