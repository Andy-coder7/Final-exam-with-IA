CREATE TABLE IF NOT EXISTS account (
                                       id VARCHAR(36) NOT NULL PRIMARY KEY,
    account_type VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS transaction (
                                           id VARCHAR(36) NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    reason VARCHAR(255),
    account_id VARCHAR(36),
    CONSTRAINT fk_transaction_account
    FOREIGN KEY (account_id) REFERENCES account(id)
    );