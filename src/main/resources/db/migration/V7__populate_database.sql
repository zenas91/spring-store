INSERT INTO categories (id, name)
VALUES (1, 'Fruits'),
       (2, 'Vegetables'),
       (3, 'Dairy'),
       (4, 'Bakery'),
       (5, 'Beverages');

-- Insert products (10 real-world items)
INSERT INTO products (name, price, description, category_id)
VALUES ('Bananas', 0.59, 'Fresh ripe bananas sold per pound.', 1),
       ('Honeycrisp Apples', 2.99, 'Crisp, sweet apples perfect for snacking.', 1),

       ('Carrots', 1.49, 'Whole organic carrots, 1 lb bag.', 2),
       ('Broccoli Crowns', 1.89, 'Fresh green broccoli crowns, sourced locally.', 2),

       ('Whole Milk', 3.49, '1 gallon of whole milk from local dairy farms.', 3),
       ('Greek Yogurt', 4.79, '32 oz plain Greek yogurt, high in protein.', 3),

       ('Sourdough Bread', 4.25, 'Freshly baked sourdough loaf made with natural starter.', 4),
       ('Blueberry Muffins', 5.99, '4-pack of bakery-made blueberry muffins.', 4),

       ('Orange Juice', 3.99, '100% pure squeezed orange juice, 52 fl oz.', 5),
       ('Sparkling Water', 0.99, '12 fl oz can of unsweetened sparkling mineral water.', 5);