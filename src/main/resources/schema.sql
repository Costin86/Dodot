CREATE TABLE IF NOT EXISTS Customer (
    customer_id INT(5) NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(50) DEFAULT NULL,
    lastName VARCHAR(50) DEFAULT NULL,
    address VARCHAR(50) DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    phone VARCHAR(50) DEFAULT NULL,
    issue VARCHAR(50) DEFAULT NULL,
    invoice VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS Status (
    status_id INT(5) NOT NULL AUTO_INCREMENT,
     VARCHAR(50) DEFAULT NULL,
    customer_id INT(5) NOT NULL,
    CONSTRAINT FK_Customer_Id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    PRIMARY KEY (status_id)
);
