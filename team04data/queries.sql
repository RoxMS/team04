-- '''"52 weeks of sales history": select the count of orders grouped by week
-- e.g. "week 1 has 123 orders"'''
SELECT week, COUNT(DISTINCT orderID) AS total_orders FROM orders GROUP BY week; 
-- selects the week column from the restaurant_orders table and calculates the count of distinct orderID values for each week. 
--gets it from the orders table`
--group each count by a week

-- '''"Realistic sales history": select count of orders, sum of order total grouped by hour
-- e.g. "12pm has 12345 orders totaling $86753"'''
SELECT hour, COUNT(DISTINCT orderID) AS total_orders, SUM(sale) AS total_sales FROM orders GROUP BY hour; 

--count of the total orders based on their distinct ids and save it under total_orders for each hours
--get from the orders table
--add up the total sales for each hours
--group it by hours


-- '''"2 peak days": select top 10 sums of order total grouped by day in descending order by order total
-- e.g. "30 August has $123456 of sales"'''
SELECT day, month, year, SUM(sale) as total_sale FROM orders GROUP BY day, month, year ORDER BY total_sale DESC LIMIT 2;


-- '''"Inventory items for 20 menu items": select count of inventory items from inventory and menu grouped by menu item
-- e.g. "chicken fingers uses 12 items"'''

SELECT menu_item, COUNT(*) AS inventory_items_count FROM menu JOIN inventory ON menu.item = inventory.ingredient GROUP BY menu_item ORDER BY inventory_items_count DESC LIMIT 20;            -- Limiting the output to the top 20 menu items with the highest inventory items count
 --  Counting the occurrences of each menu item in the Inventory table
 -- Joining Ingredients and Inventory tables on menu_item
 -- Grouping the results by menu_item
 -- Ordering the results by inventory_items_count in descending order

-- '''"Top 10 Busiest Hours of the Day": select count of orders grouped by hour of the day
-- e.g. "The top selling hour was 2pm"'''

SELECT hour, COUNT(*) AS total_orders FROM orders GROUP BY hour ORDER BY total_orders DESC; LIMIT 10;             
--this selects the hour column from the orders table
--the COUNT(*) function calculates the total rows for each hour and assigns it to total_orders
--gets those values from the orders table
--groups the total_orders by each hour
--order table results in descending order
-- Limiting the output to the top 10

-- Highest sold item of each day over 52 weeks
WITH ranked_items AS ( SELECT day, month, year, menu_item, COUNT(menu_item) AS itemsSold, RANK() OVER (PARTITION BY day, month, year ORDER BY COUNT(menu_item) DESC) AS rank FROM orders GROUP BY day, month, year, menu_item)
SELECT day, month, year, menu_item, itemsSold FROM ranked_items WHERE rank = 1;


-- '''"Highest sold item of the month"'''
SELECT month, year, menu_item, COUNT(menu_item) as itemsSold FROM orders GROUP BY month, year, menu_item ORDER BY year DESC, month DESC, itemsSold DESC LIMIT 1;        


-- '''"Most profitable month"'''
SELECT month, year, SUM(sale) as totalSale FROM orders GROUP BY month, year ORDER BY totalSale DESC LIMIT 1;
-- This selects the month and year calculates the sum of sales for each month and year, labeling this sum as totalSale
 -- ensures that sales are aggregated for each specific month within each year

-- '''"Highest Valued Order Sales": select count of orders grouped by hour of the day
-- e.g. "The second largest sale total was $15.00"'''

SELECT orderID, SUM(sale) AS total_sale FROM orders GROUP BY orderID ORDER BY  total_sale DESC; LIMIT 30;
--sums up the total sales
--get values from orders table
--order them by order id

-- '''"Ingredient Items for a Specific Menu Item": select inventory items grouped by a menu item they correspond to
-- e.g. "Revs Burger requires Burger Bread, a Patty, ...."'''

SELECT ingredient, count FROM menu WHERE menu_item = 'Double Stack Cheese Burger'; 
--get the ingredient column and count column
--get the ingredient column and count column
-- get them from the ingredients table
--do it only for the double stack cheese burger

---Lowest Inventory Items
SELECT menu_item, remaining FROM inventory ORDER BY remaining ASC LIMIT 5; 
-- selects the item and its remaining quantity
-- item is obtained from inventory table
-- it is obtained in an ascending order
-- we are only looking at the top lowest 5 items but could be updated for more

-- Most used Ingredient
SELECT ingredient, SUM(count) as total_count FROM menu GROUP BY ingredient ORDER BY total_count DESC; 
    ---select ingredient column and sum of count column named as total_count
    ---obtained from ingredients table
    --- grouped by ingredients to aggregate the counts
    --- the count of ingredients is than ordered in descending 

-- Total Inventory amount in Stock
SELECT SUM(amount) AS total_inventory_amount FROM inventory; 
---select sum of column of amount as total_inventory_amount
--- the total will be obtained from inventory in stock

-- Most used Inventory Items
SELECT menu_item, SUM(sale) AS total_sales FROM orders GROUP BY menu_item ORDER BY total_sales DESC; 
    --- select from items and find the sum of the sale as total_sales
    ---obtaining the sales information from orders
    ---it is than grouped by item to aggregate sales
    ---it is than ordered and resulted in descending order for the total_sales