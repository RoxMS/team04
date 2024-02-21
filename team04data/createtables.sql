DROP TABLE orders;
DROP TABLE ingredients;
DROP TABLE inventory;
CREATE TABLE orders (hour varchar(255), day int, week int, month int, year int, item varchar(255), sale int, orderID int);
CREATE TABLE ingredients (item varchar(255), ingredient varchar(255), count int);
CREATE TABLE inventory (ingredient varchar(255), amount int, capacity int);
