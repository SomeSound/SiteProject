CREATE TABLE CUSTOMER (
	ID BIGINT NOT NULL UNIQUE,
    NAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL UNIQUE,
    COUNTRY VARCHAR(255) NOT NULL,
    BIRTH_DATE VARCHAR(255) NOT NULL,
    AVATAR VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    ROLE SMALLINT CHECK (role BETWEEN 0 AND 2),
    SUBSCRIPTION BIGINT NOT NULL DEFAULT 1,
    CREATED_DATE TIMESTAMP WITH TIME ZONE,
    LAST_MODIFIED_DATE TIMESTAMP WITH TIME ZONE
);

CREATE SEQUENCE CUSTOMER_SEQ START WITH 1;

ALTER TABLE CUSTOMER ADD PRIMARY KEY (ID);
ALTER TABLE CUSTOMER ADD CONSTRAINT SUBSCRIPTION_ID_FK FOREIGN KEY (SUBSCRIPTION) REFERENCES SUBSCRIPTION(ID);