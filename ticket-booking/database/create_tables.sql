CREATE DATABASE ticket_booking;

CREATE TABLE events
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50)   NOT NULL,
    date  TIMESTAMP     NOT NULL,
    ticket_price DECIMAL(6, 2) NOT NULL DEFAULT (0)
);

CREATE TABLE users
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE tickets
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT,
    event_id BIGINT,
    place    INT         NOT NULL,
    category VARCHAR(50) NOT NULL
);

CREATE TABLE user_accounts
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    money   DECIMAL(6, 2) NOT NULL
);