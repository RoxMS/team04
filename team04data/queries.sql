-- '''"52 weeks of sales history": select the count of orders grouped by week
-- e.g. "week 1 has 123 orders"'''
SELECT
     week, COUNT(DISTINCT orderID) AS total_orders -- selects the week column from the restaurant_orders table and calculates the count of distinct orderID values for each week. 
FROM 
    orders --gets it from the orders table`
GROUP BY week; --group each count by a week


-- '''"Realistic sales history": select count of orders, sum of order total grouped by hour
-- e.g. "12pm has 12345 orders totaling $86753"'''
SELECT 
    hour,
    COUNT(DISTINCT orderID) AS total_orders, --count of the total orders based on their distinct ids and save it under total_orders for each hours
    SUM(sale) AS total_sales --add up the total sales for each hours
FROM 
    orders --get from the orders table
GROUP BY 
    hour; --group it by hours


-- '''"2 peak days": select top 10 sums of order total grouped by day in descending order by order total
-- e.g. "30 August has $123456 of sales"'''
SELECT day, month, year, SUM(sales)
FROM orders
GROUP BY day, month, year
ORDER BY year, month, day
LIMIT 10;


-- '''"Inventory items for 20 menu items": select count of inventory items from inventory and menu grouped by menu item
-- e.g. "chicken fingers uses 12 items"'''

SELECT item, COUNT(*) AS inventory_items_count    --  Counting the occurrences of each menu item in the Inventory table
FROM ingredients
JOIN inventory ON ingredients.item = inventory.ingredient  -- Joining Ingredients and Inventory tables on menu_item
GROUP BY item                                  -- Grouping the results by menu_item
ORDER BY inventory_items_count DESC    -- Ordering the results by inventory_items_count in descending order
LIMIT 20;            -- Limiting the output to the top 20 menu items with the highest inventory items count


-- '''"Top 10 Busiest Hours of the Day": select count of orders grouped by hour of the day
-- e.g. "The top selling hour was 2pm"'''

SELECT 
    hour, --this selects the hour column from the orders table
    COUNT(*) AS total_orders --the COUNT(*) function calculates the total rows for each hour and assigns it to total_orders
FROM 
    orders --gets those values from the orders table
GROUP BY 
    hour --groups the total_orders by each hour
ORDER BY 
    total_orders DESC; --order table results in descending order
LIMIT 10;            -- Limiting the output to the top 10 

--Manisha
-- '''"Highest sold item of the day"'''
SELECT 
    day, month, year, item, COUNT(item) as itemsSold
FROM orders
GROUP BY day, month, yeaR, item
ORDER BY year DESC, month DESC, day DESC, itemsSold DESC
LIMIT 1;        


-- '''"Highest sold item of the month"'''
SELECT month, year, item, COUNT(item) as itemsSold
FROM orders
GROUP BY month, year, item
ORDER BY year DESC, month DESC, itemsSold DESC
LIMIT 1;        


-- '''"Most profitable month"'''
SELECT month, year, SUM(sale) as totalSale          -- This selects the month and year calculates the sum of sales for each month and year, labeling this sum as totalSale
FROM orders
GROUP BY month, year                -- ensures that sales are aggregated for each specific month within each year
ORDER BY totalSale DESC
LIMIT 1;


--Roxana
-- '''"Highest Valued Order Sales": select count of orders grouped by hour of the day
-- e.g. "The second largest sale total was $15.00"'''

SELECT 
    orderID, SUM(sale) AS total_sale --sums up the total sales
FROM 
    orders --get values from orders table
GROUP BY 
    orderID --order them by order id
ORDER BY  
    total_sale DESC; --sort them in descending order
LIMIT 30;

-- '''"Ingredient Items for a Specific Menu Item": select inventory items grouped by a menu item they correspond to
-- e.g. "Revs Burger requires Burger Bread, a Patty, ...."'''

SELECT 
    ingredient, count --get the ingredient column and count column
FROM 
    ingredients -- get them from the ingredients table
WHERE 
    item = 'Double Stack Cheese Burger'; --do it only for the double stack cheese burger

---Lowest Inventory Items
SELECT 
    item, remaining --- selects the item and its remaining quantity
FROM 
    inventory ---item is obtained from inventory table
ORDER BY 
    remaining ASC  ---it is obtained in an ascending order
LIMIT 5;  --- we are only looking at the top lowest 5 items but could be updated for more

-- Most used Ingredient
SELECT
    ingredient, SUM(count) as total_count
FROM
    ingredients
GROUP BY
    ingredient
ORDER BY
    total_count DESC;

-- Total Inventory amount in Stock

-- Most used Inventory Items
