# Online Shop Database Project

## Overview
This project is a **database system** for an Online Shop.  
It includes a **MySQL schema**, **ER diagrams**, and **sample queries** that demonstrate how users, products, orders, payments, reviews, and other entities interact with each other.

The project was created as part of a learning exercise to practice:
- Database design (15 tables with 1-to-1, 1-to-many, many-to-many relationships)  
- Writing SQL queries (JOINs, GROUP BY, HAVING, aggregate functions)  
- Working with **MySQL Workbench**, `.sql` schema files, `.mwb` model file  
- XML representation and Java DOM parsing  

---

## Project Structure


---

## Database Schema

The Online Shop schema contains **15 tables**:
- `users`, `userprofiles`
- `categories`, `products`, `productimages`
- `orders`, `orderitems`, `payments`, `shipments`
- `carts`, `cartitems`
- `reviews`
- `suppliers`, `productsuppliers`
- `discounts`

Relationships:
- **1-to-1**: `users` → `userprofiles`  
- **1-to-many**: `categories` → `products`, `orders` → `orderitems`  
- **many-to-many**: `products` ↔ `suppliers`  

---

## How to Run

### Import database
1. Open **MySQL Workbench**  
2. Load `online_shop.mwb` to view the ERD  
3. Run `init.sql` to create and insert data into tables  
4. Run `practice.sql` to practice different types of SQL joins and aggregations

### XML & Java
- XML data (`suppliers+products_suppliers.xml`) represent additional tables (`suppliers`, `productsuppliers`)  
- `SupplierDomParser.java` parses the XML file and logs information  

Run DOM parser:
```bash
mvn clean compile
mvn exec:java -D"exec.mainClass=com.solvd.online_shop.App"
```


