insert into ROLE(ID,NAME) values (1, 'USER');

insert into ADDRESS(ID, CITY, POSTAL_CODE, STATE, STREET_ADDRESS) values (10000, 'Jakku', '92187', 'CA', '2187 Jakku Ave.');

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