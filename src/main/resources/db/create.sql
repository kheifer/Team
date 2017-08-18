SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    homeTown VARCHAR,
    occupation VARCHAR,
    age INTEGER,
    memberId INTEGER
    );
CREATE TABLE IF NOT EXISTS team(
    id int PRIMARY KEY auto_increment,
    teamName VARCHAR,
    teamDescription VARCHAR
);