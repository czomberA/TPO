CREATE TABLE AUTHOR (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        FIRST_NAME VARCHAR(255),
                        LAST_NAME VARCHAR(255)
);

CREATE TABLE PUBLISHER (
                           ID INT AUTO_INCREMENT PRIMARY KEY,
                           NAME VARCHAR(255)
);

CREATE TABLE BOOK (
                      ID INT AUTO_INCREMENT PRIMARY KEY,
                      TITLE VARCHAR(255),
                      PRICE double precision,
                      PUBLISHER_ID INT,
                      FOREIGN KEY (PUBLISHER_ID) REFERENCES PUBLISHER(ID)
);

CREATE TABLE BOOK_AUTHORS (
                             ID INT AUTO_INCREMENT PRIMARY KEY,
                             BOOK_ID INT,
                             AUTHORS_ID INT,
                             FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ID),
                             FOREIGN KEY (AUTHORS_ID) REFERENCES AUTHOR(ID)
);
