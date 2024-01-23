-- Insert Seller
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('SELLER', 'Seller1', 'test@gmail.com', null, null, 'seller1');

-- Insert Buyer
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('BUYER', 'Company1', 'john.doe@example.com', 'Credit Card', null, 'buyer1');

-- Insert Seller
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('SELLER', 'Seller2', 'seller2@example.com', null, null, 'seller2');

-- Insert Buyer
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('BUYER', 'Company2', 'buyer2@example.com', 'PayPal', null, 'buyer2');

-- Insert Seller
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('SELLER', 'Seller3', 'seller3@example.com', null, null, 'seller3');

-- Insert Buyer
INSERT INTO auction_user (USER_TYPE, COMPANY_NAME, EMAIL, PREFERRED_PAYMENT_METHOD, TOKEN, USERNAME)
VALUES ('BUYER', 'Company3', 'buyer3@example.com', 'Credit Card', null, 'buyer3');

-- Insert Products
INSERT INTO product (MIN_BID_PRICE, ID, SELLER_ID, NAME)
VALUES (100.00, 1, 1, 'Product1');

INSERT INTO product (MIN_BID_PRICE, ID, SELLER_ID, NAME)
VALUES (150.00, 2, 1, 'Product2');

INSERT INTO product (MIN_BID_PRICE, ID, SELLER_ID, NAME)
VALUES (120.00, 3, 3, 'Product3');

INSERT INTO product (MIN_BID_PRICE, ID, SELLER_ID, NAME)
VALUES (200.00, 4, 3, 'Product4');


-- Insert Bids
INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (120.00, 2, 1, 1);

INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (180.00, 2, 2, 1);

INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (200.00, 4, 3, 2);

INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (220.00, 4, 4, 2);

INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (250.00, 6, 5, 3);

INSERT INTO bid (AMOUNT, BUYER_ID, ID, PRODUCT_ID)
VALUES (300.00, 6, 6, 3);
