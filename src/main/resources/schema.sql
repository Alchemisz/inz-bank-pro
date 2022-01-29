CREATE TABLE IF NOT EXISTS TUser(
    login VARCHAR (25) PRIMARY KEY NOT NULL,
    tpass VARCHAR (25) NOT NULL,
    trole VARCHAR (25) NOT NULL

);

CREATE TABLE IF NOT EXISTS BankAccount (

    accountNumber VARCHAR(24)  PRIMARY KEY NOT NULL,
    status VARCHAR(16) NOT NULL,
    balance DOUBLE NOT NULL,
    currency VARCHAR(16) not null,
    login varchar(25) not null,

    FOREIGN KEY (login) REFERENCES TUser(login)
);



CREATE TABLE IF NOT EXISTS Card (

    cardNumber VARCHAR(25) PRIMARY KEY NOT NULL,
    bankAccount varchar(24),
    FOREIGN KEY (bankAccount) REFERENCES BankAccount(accountNumber)
    );




CREATE TABLE IF NOT EXISTS Transfer (

    id INTEGER PRIMARY KEY NOT NULL,
    senderId VARCHAR(24) NOT NULL,
    receiverId VARCHAR(24) NOT NULL,
    amount DOUBLE NOT NULL

    );