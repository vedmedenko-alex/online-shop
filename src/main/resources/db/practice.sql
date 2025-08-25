-- Left join

-- Avg ordered price for each user
select users.name, avg(products.price) as avg_product_price, 
       sum(orderitems.quantity) as total_quantity, 
       count(distinct orders.order_id) as total_orders
from users
left join orders on users.user_id = orders.user_id
left join orderitems on orderitems.order_id = orders.order_id
left join products on products.product_id = orderitems.product_id
group by users.name;

-- Min & Max rating for each product
select products.name as product_name, min(reviews.rating) as min_rating, max(reviews.rating) as max_rating
from products
left join reviews on products.product_id = reviews.product_id
left join users on reviews.user_id = users.user_id
group by products.name;

-- Count products and Sum prices for each supplier
select suppliers.name as supplier_name, count(products.product_id) as total_products, sum(products.price) as total_price
from suppliers
left join productsuppliers on productsuppliers.supplier_id = suppliers.supplier_id
left join products on products.product_id = productsuppliers.product_id
left join productimages on productimages.product_id = products.product_id
group by suppliers.name;

-- Max discount in every category
select categories.name as category_name, max(discounts.percentage) as max_discount
from products
left join discounts on discounts.product_id = products.product_id
left join categories on categories.category_id = products.category_id
group by categories.name;

-- Right join

-- Sum all payments and avg price for each user
select users.name as user_name, sum(payments.amount) as total_paid, avg(payments.amount) as avg_payment
from shipments
right join payments on shipments.order_id = payments.order_id
right join orders on payments.order_id = orders.order_id
right join users on orders.user_id = users.user_id
right join userprofiles on users.user_id = userprofiles.user_id
group by users.name;

-- Avg rating and count reviews
select products.name as product_name, avg(reviews.rating) as avg_rating, count(reviews.review_id) as review_count
from reviews
right join products on reviews.product_id = products.product_id
right join categories on products.category_id = categories.category_id
right join discounts on products.product_id = discounts.product_id
group by products.name;

-- All productimages with products and suppliers
select productimages.image_url, products.name as product_name, products.price, suppliers.name as supplier_name 
from suppliers
right join productsuppliers on suppliers.supplier_id = productsuppliers.supplier_id
right join products on productsuppliers.product_id = products.product_id
right join productimages on products.product_id = productimages.product_id;

-- Inner join

-- All clients' orders with categories and products
select users.name as client, orders.order_date, orders.status, payments.amount, categories.name as category, 
	   products.name as product_name, orderitems.quantity 
from orders
join payments on payments.order_id = orders.order_id
join orderitems on orderitems.order_id = orders.order_id
join products on products.product_id = orderitems.product_id
join categories on categories.category_id = products.category_id
join users on users.user_id = orders.user_id;

-- All suppliers' products with reviewers and comments
select suppliers.name as supplier_name, products.name as product_name, users.name as reviewer_name, reviews.rating, reviews.comment
from suppliers
join productsuppliers on suppliers.supplier_id = productsuppliers.supplier_id
join products on productsuppliers.product_id = products.product_id
join reviews on products.product_id = reviews.product_id
join users on reviews.user_id = users.user_id;

-- All suppliers' products with discount
select suppliers.name as supplier_name, products.name as product_name, discounts.percentage as discount_percent 
from suppliers
join productsuppliers on suppliers.supplier_id = productsuppliers.supplier_id
join products on productsuppliers.product_id = products.product_id
join discounts on products.product_id = discounts.product_id;

-- Outer join ???



-- Group by & Having

select categories.category_id, categories.name, count(product_id) 
from categories
join products on products.category_id = categories.category_id
group by category_id;

select categories.category_id, categories.name, max(price) 
from categories
join products on products.category_id = categories.category_id
group by category_id
having max(price) > 100;

select categories.category_id, categories.name, sum(stock_quantity) 
from categories
join products on products.category_id = categories.category_id
group by category_id
having sum(stock_quantity) < 50;

select name, avg(rating)
from products
join reviews on reviews.product_id = products.product_id
group by products.name
having avg(rating) > 4;

-- Big query 

select 
    users.name as user_name,
    users.email,
    userprofiles.phone,
    userprofiles.address,
    orders.order_date,
    orders.status as order_status,
    orderitems.quantity,
    orderitems.price as item_price,
    products.name as product_name,
    products.description as product_description,
    products.price as product_price,
    categories.name as category_name,
    suppliers.name as supplier_name,
    suppliers.contact_info,
    shipments.shipment_date,
    shipments.delivery_date,
    shipments.status as shipment_status,
    payments.amount as payment_amount,
    payments.payment_date,
    payments.payment_method,
    payments.status as payment_status,
    reviews.rating,
    reviews.comment,
    discounts.percentage as discount_percentage,
    discounts.valid_from,
    discounts.valid_to
from users
join userprofiles on users.user_id = userprofiles.user_id
join orders on users.user_id = orders.user_id
join orderitems on orders.order_id = orderitems.order_id
join products on orderitems.product_id = products.product_id
join categories on products.category_id = categories.category_id
join productsuppliers on products.product_id = productsuppliers.product_id
join suppliers on productsuppliers.supplier_id = suppliers.supplier_id
join shipments on orders.order_id = shipments.order_id
join payments on orders.order_id = payments.order_id
left join reviews on products.product_id = reviews.product_id
left join discounts on products.product_id = discounts.product_id;


