DROP TABLE IF EXISTS person;
CREATE TABLE person (
                      id INT AUTO_INCREMENT not null PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      second_name VARCHAR(50) NOT NULL,
                      date_of_birth date NOT NULL
);