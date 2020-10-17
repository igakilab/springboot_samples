CREATE TABLE chamber (
    id INT IDENTITY,
    user CHAR NOT NULL,
    number INT NOT NULL
);
CREATE TABLE userinfo (
    user CHAR NOT NULL PRIMARY KEY,
    height FLOAT NOT NULL
);
