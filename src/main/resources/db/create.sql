SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS entries (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  phoneNumber VARCHAR,
  mailingAddress VARCHAR,
  emailAddress VARCHAR
);