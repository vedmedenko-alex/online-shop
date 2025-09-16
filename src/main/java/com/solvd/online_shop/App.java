package com.solvd.online_shop;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solvd.online_shop.factories.ServiceFactory;
import com.solvd.online_shop.models.*;
import com.solvd.online_shop.services.impl.CartService; // JSON parser
// import com.solvd.online_shop.services.impl.CategoryService;
// import com.solvd.online_shop.services.impl.DiscountService;
// import com.solvd.online_shop.services.impl.OrderService;
// import com.solvd.online_shop.services.impl.ProductService;
// import com.solvd.online_shop.services.impl.ReviewService;
// import com.solvd.online_shop.services.impl.UserService;
// import com.solvd.online_shop.services.mybatisimpl.CategoryService;
// import com.solvd.online_shop.services.mybatisimpl.DiscountService;
// import com.solvd.online_shop.services.mybatisimpl.OrderService;
// import com.solvd.online_shop.services.mybatisimpl.ProductService;
// import com.solvd.online_shop.services.mybatisimpl.ReviewService;
// import com.solvd.online_shop.services.mybatisimpl.UserService;
import com.solvd.online_shop.services.impl.SupplierService; // XML parser
import com.solvd.online_shop.services.impl.XmlService;
import com.solvd.online_shop.services.interfaces.IUserService;
import com.solvd.online_shop.services.interfaces.IProductService;
import com.solvd.online_shop.services.interfaces.IOrderService;
import com.solvd.online_shop.services.interfaces.ICategoryService;
import com.solvd.online_shop.services.interfaces.IReviewService;
import com.solvd.online_shop.services.interfaces.IDiscountService;


public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        ServiceFactory factory = new ServiceFactory(ServiceFactory.Implementation.MYBATIS);

        IUserService userService = factory.createUserService();
        IProductService productService = factory.createProductService();
        IOrderService orderService = factory.createOrderService();
        ICategoryService categoryService = factory.createCategoryService();
        IReviewService reviewService = factory.createReviewService();
        IDiscountService discountService = factory.createDiscountService();

        logger.info("---- Testing UserService ----");
        try {
            User user = new User(0, "Tony Stark", "tony.stark@starkindustries.com", "password123");
            userService.add(user);
            logger.log(Level.INFO, "Added User: {0}", user.getName());

            User retrievedUser = userService.getById(1);
            logger.log(Level.INFO, "User Retrieved: {0}", retrievedUser);

            List<User> users = userService.getAll();
            logger.log(Level.INFO, "All Users: {0}", users);

            retrievedUser.setName("Tony Stark Updated");
            userService.update(retrievedUser);
            logger.log(Level.INFO, "Updated User: {0}", retrievedUser.getName());

            // userService.delete(1);
            // logger.info("Deleted User with ID: 1");

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "UserService error", e);
        }

        logger.info("---- Testing ProductService ----");
        try {
            Product product = new Product(0, 1, "Superhero suit", "High-quality superhero suit", 1500.00, 50);
            productService.add(product);
            logger.log(Level.INFO, "Added Product: {0}", product.getName());

            Product retrievedProduct = productService.getById(1);
            logger.log(Level.INFO, "Product Retrieved: {0}", retrievedProduct);

            List<Product> products = productService.getAll();
            logger.log(Level.INFO, "All Products: {0}", products);

            retrievedProduct.setPrice(1450.00);
            productService.update(retrievedProduct);
            logger.log(Level.INFO, "Updated Product Price: {0}", retrievedProduct.getPrice());

            // productService.delete(1);

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "ProductService error", e);
        }

        logger.info("---- Testing OrderService ----");
        try {
            Order order = new Order(0, 1, null, "Pending", 1500.00);
            orderService.add(order);
            logger.log(Level.INFO, "Added Order: {0}", order.getOrderId());

            Order retrievedOrder = orderService.getById(1);
            logger.log(Level.INFO, "Order Retrieved: {0}", retrievedOrder);

            List<Order> orders = orderService.getAll();
            logger.log(Level.INFO, "All Orders: {0}", orders);

            retrievedOrder.setStatus("Shipped");
            orderService.update(retrievedOrder);
            logger.log(Level.INFO, "Updated Order Status: {0}", retrievedOrder.getStatus());

            // orderService.delete(1);

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "OrderService error", e);
        }

        logger.info("---- Testing CategoryService ----");
        try {
            Category category = new Category(0, "Suits", "Suits for superheroes");
            categoryService.add(category);
            logger.log(Level.INFO, "Added Category: {0}", category.getName());

            Category retrievedCategory = categoryService.getById(1);
            logger.log(Level.INFO, "Category Retrieved: {0}", retrievedCategory);

            List<Category> categories = categoryService.getAll();
            logger.log(Level.INFO, "All Categories: {0}", categories);

            retrievedCategory.setDescription("Amazing Suits for superheroes");
            categoryService.update(retrievedCategory);
            logger.log(Level.INFO, "Updated Category: {0}", retrievedCategory.getDescription());

            // categoryService.delete(1);

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "CategoryService error", e);
        }

        logger.info("---- Testing ReviewService ----");
        try {
            Review review = new Review(0, 1, 1, 5, "Amazing suit, I feel like Iron Man!", null);
            reviewService.add(review);
            logger.log(Level.INFO, "Added Review: {0}", review.getComment());

            Review retrievedReview = reviewService.getById(1);
            if (retrievedReview != null) {
                logger.log(Level.INFO, "Review Retrieved: {0}", retrievedReview);

                retrievedReview.setRating(4);
                reviewService.update(retrievedReview);
                logger.log(Level.INFO, "Updated Review Rating: {0}", retrievedReview.getRating());
            } else {
                logger.warning("Review with ID 1 not found.");
            }

            // reviewService.delete(1);
            // logger.info("Deleted Review with ID: 1");

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "ReviewService error", e);
        }

        logger.info("---- Testing DiscountService ----");
        try {
            Discount discount = new Discount(0, 1, 10.0,
                    java.sql.Date.valueOf("2023-01-01"),
                    java.sql.Date.valueOf("2023-12-31"));
            discountService.add(discount);
            logger.log(Level.INFO, "Added Discount: {0}%", discount.getPercentage());

            List<Discount> discounts = discountService.getAll();
            logger.log(Level.INFO, "All Discounts: {0}", discounts);

            // discountService.delete(1);
            // logger.info("Deleted Discount with ID: 1");

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "DiscountService error", e);
        }

        // --- XML Parsing with DOM ---
        logger.info("---- Testing SupplierService XML ----");
        SupplierService supplierService = new SupplierService();

        supplierService.getSuppliers()
                .forEach(s -> logger.info("Supplier: " + s));

        Supplier supplier = supplierService.getSupplierById(2);
        if (supplier != null) {
            logger.info("Supplier with ID 2: " + supplier);
        } else {
            logger.warning("Supplier with ID 2 not found.");
        }

        // --- XML Parsing with JAXB ---
        logger.info("---- Testing SupplierService XML + XSD ----");
        try {
            XmlService xmlService = new XmlService();
            SuppliersWrapper wrapper =
                    xmlService.parseSuppliersXml(
                            "src/main/resources/xml/suppliers+products_suppliers.xml",
                            "src/main/resources/xsd/suppliers.xsd");

            for (Supplier supplierXmlXsd : wrapper.getSuppliers()) {
                logger.info("Supplier: " + supplierXmlXsd);
                supplierXmlXsd.getProducts()
                        .forEach(product -> logger.info("  Product: " + product));
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while parsing XML", e);
        }

        // --- JSON Parsing ---
        CartService cartService = new CartService();

        logger.info("---- All Carts from JSON ----");
        cartService.getAllCarts().forEach(c -> logger.info(c.toString()));

        logger.info("---- Items from Cart 1 ----");
        cartService.getItemsByCartId(1).forEach(i -> logger.info(i.toString()));
    }
}
