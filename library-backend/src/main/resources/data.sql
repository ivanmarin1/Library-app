INSERT INTO user (id, first_name, last_name, address) VALUES
  (1, 'Ivan', 'Ivic', 'Iviceva 4'),
  (2, 'Marin', 'Marinic', 'Mariniceva 10'),
  (3, 'Ante', 'Antic', 'Anticeva 34');

INSERT INTO author (id, first_name, last_name, genre) VALUES
  (1, 'Pero', 'Peric', 'Periceva 8'),
  (2, 'Marko', 'Markic', 'Markiceva 12'),
  (3, 'Josip', 'Jozic', 'Jozina 3');

INSERT INTO book (id, isbn, title, authorID, year, note) VALUES
  (1, '1010', 'Druzba Pere Kvrzice', 1, '2019', 'Very good book'),
  (2, '1021', 'Vlak u snijegu', 2, '2011', 'I love winter!'),
  (3, '1031', 'Suma striborova', 3, '2013', 'Very good book');

INSERT INTO user_book_rent (id, user_id, book_id, loan_time, note) VALUES
  (1, 1, 1, '2019-02-18', 'Very good book'),
  (2, 2, 2, '2011-02-18', 'I love winter!'),
  (3, 3, 3, '2013-02-18', 'Very good book');
