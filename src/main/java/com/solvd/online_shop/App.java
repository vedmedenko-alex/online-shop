package com.solvd.online_shop;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Discount;
import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.models.Review;
import com.solvd.online_shop.models.Supplier;
import com.solvd.online_shop.models.User;
import com.solvd.online_shop.services.impl.CategoryService;
import com.solvd.online_shop.services.impl.DiscountService;
import com.solvd.online_shop.services.impl.OrderService;
import com.solvd.online_shop.services.impl.ProductService;
import com.solvd.online_shop.services.impl.ReviewService;
import com.solvd.online_shop.services.impl.SupplierService;
import com.solvd.online_shop.services.impl.UserService;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        UserService userService = new UserService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        CategoryService categoryService = new CategoryService();
        ReviewService reviewService = new ReviewService();
        DiscountService discountService = new DiscountService();

        logger.info("---- Testing UserService ----");
        try {
            // Add a new user
            User user = new User(0, "Tony Stark", "tony.stark@starkindustries.com", "password123");
            userService.addUser(user);
            logger.log(Level.INFO, "Added User: {0}", user.getName());

            // Get user by ID
            User retrievedUser = userService.getUserById(1);
            logger.log(Level.INFO, "User Retrieved: {0}", retrievedUser);

            // Get all users
            List<User> users = userService.getAllUsers();
            logger.log(Level.INFO, "All Users: {0}", users);

            // Update user
            retrievedUser.setName("Tony Stark Updated");
            userService.updateUser(retrievedUser);
            logger.log(Level.INFO, "Updated User: {0}", retrievedUser.getName());

            // Delete user
            userService.deleteUser(1);
            logger.info("Deleted User with ID: 1");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in UserService: {0}", e.getMessage());
        }

        logger.info("---- Testing ProductService ----");
        try {
            // Add new product
            Product product = new Product(0, 1, "Superhero suit", "High-quality superhero suit", 1500.00, 50);
            productService.addProduct(product);
            logger.log(Level.INFO, "Added Product: {0}", product.getName());

            // Get product by ID
            Product retrievedProduct = productService.getProductById(1);
            logger.log(Level.INFO, "Product Retrieved: {0}", retrievedProduct);

            // Get all products
            List<Product> products = productService.getAllProducts();
            logger.log(Level.INFO, "All Products: {0}", products);

            // Update product
            retrievedProduct.setPrice(1450.00);
            productService.updateProduct(retrievedProduct);
            logger.log(Level.INFO, "Updated Product Price: {0}", retrievedProduct.getPrice());

            // // Delete product
            // productService.deleteProduct(1);
            // logger.info("Deleted Product with ID: 1");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in ProductService: {0}", e.getMessage());
        }

        logger.info("---- Testing OrderService ----");
        try {
            // Add new order
            Order order = new Order(0, 1, null, "Pending", 1500.00);
            orderService.addOrder(order);
            logger.log(Level.INFO, "Added Order: {0}", order.getOrderId());

            // Get order by ID
            Order retrievedOrder = orderService.getOrderById(1);
            logger.log(Level.INFO, "Order Retrieved: {0}", retrievedOrder);

            // Get all orders
            List<Order> orders = orderService.getAllOrders();
            logger.log(Level.INFO, "All Orders: {0}", orders);

            // Update order
            retrievedOrder.setStatus("Shipped");
            orderService.updateOrder(retrievedOrder);
            logger.log(Level.INFO, "Updated Order Status: {0}", retrievedOrder.getStatus());

            // // Delete order
            // orderService.deleteOrder(1);
            // logger.info("Deleted Order with ID: 1");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in OrderService: {0}", e.getMessage());
        }

        logger.info("---- Testing CategoryService ----");
        try {
            // Add new category
            Category category = new Category(0, "Suits", "Suits for superheroes");
            categoryService.addCategory(category);
            logger.log(Level.INFO, "Added Category: {0}", category.getName());

            // Get category by ID
            Category retrievedCategory = categoryService.getCategoryById(1);
            logger.log(Level.INFO, "Category Retrieved: {0}", retrievedCategory);

            // Get all categories
            List<Category> categories = categoryService.getAllCategories();
            logger.log(Level.INFO, "All Categories: {0}", categories);

            // Update category
            retrievedCategory.setDescription("Amazing Suits for superheroes");
            categoryService.updateCategory(retrievedCategory);
            logger.log(Level.INFO, "Updated Category: {0}", retrievedCategory.getDescription());

            // // Delete category
            // categoryService.deleteCategory(1);
            // logger.info("Deleted Category with ID: 1");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in CategoryService: {0}", e.getMessage());
        }

        logger.info("---- Testing ReviewService ----");
        try {
            // Add a new review
            Review review = new Review(0, 1, 1, 5, "Amazing suit, I feel like Iron Man!", null);
            reviewService.addReview(review);
            logger.log(Level.INFO, "Added Review: {0}", review.getComment());

            // Get review by ID
            Review retrievedReview = reviewService.getReviewById(1);

            // Check if the review was found
            if (retrievedReview != null) {
                logger.log(Level.INFO, "Review Retrieved: {0}", retrievedReview);

                // Update the review rating if found
                retrievedReview.setRating(4);
                reviewService.updateReview(retrievedReview);
                logger.log(Level.INFO, "Updated Review Rating: {0}", retrievedReview.getRating());
            } else {
                // When no review was found
                logger.warning("Review with ID 1 not found.");
            }

            // Delete the review
            reviewService.deleteReview(1);
            logger.info("Deleted Review with ID: 1");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in ReviewService: {0}", e.getMessage());
        }

        logger.info("---- Testing DiscountService ----");
        try {
            // Add new discount
            Discount discount = new Discount(0, 1, 10.0, java.sql.Date.valueOf("2023-01-01"),
                    java.sql.Date.valueOf("2023-12-31"));
            discountService.addDiscount(discount);
            logger.log(Level.INFO, "Added Discount: {0}%", discount.getPercentage());

            // Get all discounts
            List<Discount> discounts = discountService.getAllDiscounts();
            logger.log(Level.INFO, "All Discounts: {0}", discounts);

            // Delete discount
            discountService.deleteDiscount(1);
            logger.info("Deleted Discount with ID: 1");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in DiscountService: {0}", e);
        }

        logger.info("---- Testing Supplierervice XML ----");
        SupplierService service = new SupplierService();

        logger.info("----  All suppliers from XML  ----");
        service.getSuppliers().forEach(s -> logger.info(s.toString()));

        logger.info("----  Supplier with ID 2  ----");
        Supplier supplier = service.getSupplierById(2);
        if (supplier != null) {
            logger.info(supplier.toString());
        } else {
            logger.warning("Supplier with ID 2 not found.");
        }
    }
}
