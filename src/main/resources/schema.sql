CREATE TABLE Alumni (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL
);

CREATE TABLE Address (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         street VARCHAR(100) NOT NULL,
                         number VARCHAR(10) NOT NULL,
                         country VARCHAR(50) NOT NULL,
                         alumni_id INT,
                         FOREIGN KEY (alumni_id) REFERENCES Alumni(id)
);

CREATE TABLE Education (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           alumni_id INT,
                           FOREIGN KEY (alumni_id) REFERENCES Alumni(id)
);
CREATE TABLE Degree (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           university VARCHAR(100),
                           degree_year INT,
                           education_id INT,
                           FOREIGN KEY (education_id) REFERENCES Education(id)
);
