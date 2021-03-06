CREATE TABLE IF NOT EXISTS TUser(
    login VARCHAR (25) PRIMARY KEY NOT NULL,
    tpass VARCHAR (80) NOT NULL,
    trole VARCHAR (25) NOT NULL

);

CREATE TABLE IF NOT EXISTS BankAccount (

    accountNumber VARCHAR(80)  PRIMARY KEY NOT NULL,
    status VARCHAR(25) NOT NULL,
    balance DOUBLE NOT NULL,
    currency VARCHAR(16) not null,
    login varchar(25) not null,

    FOREIGN KEY (login) REFERENCES TUser(login)
);



CREATE TABLE IF NOT EXISTS Card (

    cardNumber VARCHAR(80) PRIMARY KEY NOT NULL,
    pin INT NOT NULL,
    status VARCHAR(24) NOT NULL,
    accountNumber VARCHAR(80) NOT NULL,

    FOREIGN KEY (accountNumber) REFERENCES BankAccount(accountNumber)
);




CREATE TABLE IF NOT EXISTS Transfer (

    id VARCHAR(80) PRIMARY KEY NOT NULL,
    senderId VARCHAR(50) NOT NULL,
    receiverId VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL,
    transferDate DATE NOT NULL
);