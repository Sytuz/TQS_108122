CREATE TABLE tqs_book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published DATE NOT NULL
);

INSERT INTO tqs_book (title, author, published) VALUES ('To Kill a Mockingbird', 'Harper Lee', '1960-07-11');
INSERT INTO tqs_book (title, author, published) VALUES ('1984', 'George Orwell', '1949-06-08');
INSERT INTO tqs_book (title, author, published) VALUES ('Pride and Prejudice', 'Jane Austen', '1813-01-28');
