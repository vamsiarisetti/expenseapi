DROP TABLE IF EXISTS TRANSACTIONS;

CREATE TABLE TRANSACTIONS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_name VARCHAR(250) NOT NULL,
    transaction_type VARCHAR(250) NOT NULL,
    transaction_date VARCHAR(250) NOT NULL,
    transaction_amount DECIMAL,
    transaction_month VARCHAR(20) NOT NULL,
    transaction_category VARCHAR(250) NOT NULL,
    transaction_year VARCHAR(4) NOT NULL
);