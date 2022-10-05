/* userやgroupはSQLでは予約語で使えないため，userNameとしている */
CREATE TABLE chamber (
    id IDENTITY,
    userName VARCHAR NOT NULL,
    chamberName VARCHAR NOT NULL
);
CREATE TABLE userinfo (
    userName VARCHAR NOT NULL PRIMARY KEY,
    age INT,
    height DOUBLE NOT NULL
);
