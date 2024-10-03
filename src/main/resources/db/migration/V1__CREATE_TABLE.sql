CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100),
    password VARCHAR(100),
    name VARCHAR(100),
    admin INT default 0
);

CREATE TABLE ticket (
    ticketId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) not null,
    venue VARCHAR(100) not null,
    price INT not null,
    availCount INT,
    startDate DATE,
    endDate DATE
);

CREATE TABLE ticketSituation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticketId INT,
    userEmail VARCHAR(255),
    purchaseDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PURCHASED', 'CANCELLED') NOT NULL
);





