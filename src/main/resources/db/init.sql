DROP DATABASE IF EXISTS online_shop;

CREATE DATABASE online_shop;
USE online_shop;

DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Shipments;
DROP TABLE IF EXISTS ProductSuppliers;
DROP TABLE IF EXISTS Suppliers;
DROP TABLE IF EXISTS Discounts;
DROP TABLE IF EXISTS CartItems;
DROP TABLE IF EXISTS Carts;
DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS ProductImages;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS UserProfiles;
DROP TABLE IF EXISTS Users;


CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE UserProfiles (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    date_of_birth DATE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

CREATE TABLE ProductImages (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    image_url VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    total_amount DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE OrderItems (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(10,2),
    payment_method VARCHAR(50),
    status varchar(50),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Carts (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE CartItems (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

CREATE TABLE Reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    user_id INT,
    rating INT,
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    contact_info VARCHAR(255)
);

CREATE TABLE ProductSuppliers (
    product_id INT,
    supplier_id INT,
    PRIMARY KEY (product_id, supplier_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id)
);

CREATE TABLE Shipments (
    shipment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    shipment_date DATE,
    delivery_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Discounts (
    discount_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    percentage DECIMAL(5,2),
    valid_from DATE,
    valid_to DATE,
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);


INSERT INTO Users (name, email, password) VALUES
('Tony Stark', 'ironman@starkindustries.com', 'arc_reactor123'),
('Peter Parker', 'spiderman@dailybugle.com', 'webshooter456'),
('Bruce Wayne', 'batman@wayneenterprises.com', 'darkknight789');

INSERT INTO UserProfiles (user_id, phone, address, date_of_birth) VALUES
(1, '555-1234', '10880 Malibu Point, Malibu, CA', '1970-05-29'),
(2, '555-5678', '20 Ingram Street, Forest Hills, NY', '2001-08-10'),
(3, '555-9999', '1007 Mountain Drive, Gotham', '1972-02-19');

INSERT INTO Categories (name, description) VALUES
('Gadgets', 'High-tech superhero gadgets and equipment'),
('Costumes', 'Superhero suits and accessories'),
('Comics', 'Comic books and graphic novels'),
('Collectibles', 'Superhero figurines and memorabilia');

INSERT INTO Products (category_id, name, description, price, stock_quantity) VALUES
(1, 'Iron Man Helmet', 'Mark 50 helmet replica with LED lights', 299.99, 10),
(1, 'Batarang', 'Metal batarang set with display stand', 49.99, 50),
(2, 'Spider-Man Suit', 'Classic red and blue spandex costume', 149.99, 20),
(3, 'Avengers #1', 'First edition reprint of Avengers comic', 19.99, 100),
(4, 'Infinity Gauntlet', 'Full-scale gauntlet with light-up stones', 399.99, 5);

INSERT INTO ProductImages (product_id, image_url) VALUES
(1, 'ironman_helmet.jpg'),
(2, 'batarang.jpg'),
(3, 'spiderman_suit.jpg'),
(4, 'avengers1.jpg'),
(5, 'infinity_gauntlet.jpg');

INSERT INTO Orders (user_id, status, total_amount) VALUES
(1, 'Shipped', 349.98),
(2, 'Processing', 149.99),
(3, 'Delivered', 399.99);

INSERT INTO OrderItems (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 299.99),
(1, 2, 1, 49.99),
(2, 3, 1, 149.99),
(3, 5, 1, 399.99);

INSERT INTO Payments (order_id, amount, payment_method, status) VALUES
(1, 349.98, 'Credit Card', 'Completed'),
(2, 149.99, 'PayPal', 'Pending'),
(3, 399.99, 'Bank Transfer', 'Completed');

INSERT INTO Carts (user_id) VALUES
(1),
(2),
(3);

INSERT INTO CartItems (cart_id, product_id, quantity) VALUES
(1, 4, 2),
(2, 2, 1),
(3, 1, 1);

INSERT INTO Reviews (product_id, user_id, rating, comment) VALUES
(1, 1, 5, 'Perfect helmet! Feels like I’m in the suit.'),
(3, 2, 4, 'Great costume, but mask visibility could be better.'),
(5, 3, 5, 'The gauntlet is incredible! Now I just need the stones.');

INSERT INTO Suppliers (name, contact_info) VALUES
('Stark Industries', 'supplies@starkindustries.com'),
('Wayne Enterprises', 'gadgets@wayneenterprises.com'),
('Oscorp', 'tech@oscorp.com');

INSERT INTO ProductSuppliers (product_id, supplier_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2);

INSERT INTO Shipments (order_id, shipment_date, delivery_date, status) VALUES
(1, '2025-08-10', '2025-08-12', 'Delivered'),
(2, '2025-08-12', NULL, 'In Transit'),
(3, '2025-08-05', '2025-08-08', 'Delivered');

INSERT INTO Discounts (product_id, percentage, valid_from, valid_to) VALUES
(2, 15.00, '2025-08-01', '2025-08-31'),
(4, 5.00, '2025-08-15', '2025-09-01');
