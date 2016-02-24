insert into ROLE(ID,NAME) values (1, 'USER');

insert into ADDRESS(ID, CITY, POSTAL_CODE, STATE, STREET_ADDRESS) values (10000, 'Jakku', '92187', 'CA', '2187 Jakku Ave.');

insert into STORE(ID) values (10000);

insert into CUSTOMER(ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, EMAIL, ENABLED, NAME, PASSWORD, PHONE, ADDRESS_ID) values (10000, TRUE, TRUE, TRUE, 'rey@theresistance.com', TRUE, 'Rey', 'password', '+1(999)999-9999)', 10000);

insert into CUSTOMER_ROLES(CUSTOMER_ID, ROLES_ID) values (10000, 1);

insert into SIZE(ID, NAME, PRICE) values (10000, 'Large', 12.99);
insert into SIZE(ID, NAME, PRICE) values (10001, 'Medium', 10.99);
insert into SIZE(ID, NAME, PRICE) values (10002, 'Small', 8.99);

insert into CRUST(ID, NAME) values (10000, 'Thin');
insert into CRUST(ID, NAME) values (10001, 'Hand Tossed');
insert into CRUST(ID, NAME) values (10002, 'Deep Dish');

insert into SAUCE(ID, NAME) values (10000, 'Normal');
insert into SAUCE(ID, NAME) values (10001, 'Light');
insert into SAUCE(ID, NAME) values (10002, 'None');
insert into SAUCE(ID, NAME) values (10003, 'Heavy');

insert into ORDERS(ID, TYPE, CUSTOMER_ID) values (10000, 0, 10000);
insert into HALF(ID) values (10000);
insert into HALF(ID) values (10001)
insert into PIZZA(ID, CRUST_ID, LEFT_HALF_ID, ORDER_ID, RIGHT_HALF_ID, SAUCE_ID, SIZE_ID) values (10000,10000,10000,10000,10001,10000,10000);

insert into TOPPING(ID, NAME, PRICE) values (10000, 'Sausage', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10001, 'Pepperoni', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10002, 'Ham', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10003, 'Beef', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10004, 'Bacon', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10005, 'Mushroom', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10006, 'Onion', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10007, 'Bell Pepper', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10008, 'Green Olive', 0.5);
insert into TOPPING(ID, NAME, PRICE) values (10009, 'Black Olive', 0.5);
