CREATE TABLE CART (
	CUSTOMER_ID VARCHAR(20) NOT NULL,
    TOTAL_PRICE DECIMAL(4,2) NOT NULL,
    TOTAL_ITEMS INT NOT NULL
);

ALTER TABLE CART ADD PRIMARY KEY (CUSTOMER_ID);
ALTER TABLE CART ADD CONSTRAINT CART_CUSTOMER_ID_FK FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID);