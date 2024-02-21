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

