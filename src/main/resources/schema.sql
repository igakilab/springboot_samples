CREATE TABLE chamber (
    id IDENTITY,
    username VARCHAR NOT NULL,
    number INT NOT NULL
);
CREATE TABLE userinfo (
    userName VARCHAR NOT NULL PRIMARY KEY,
    height DOUBLE NOT NULL
);
