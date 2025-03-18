INSERT INTO PUBLISHER (NAME) VALUES
                                 ('RUSSIAN LITERATURE LLC.'),
                                 ('THEATRE TODAY'),
                                 ('JULIUS AND SONS'),
                                 ('FRENCH CLASSICS'),
                                 ('VICTORIAN SP. ZOO'),

                                 ('RUPERT & SONS'),
                                 ('PEDRO Y HIJOS'),
                                 ('CLASSICS'),
                                 ('ABC BOOKS'),
                                 ('XYZ BOOKS'),

                                 ('ALL IN ONE'),
                                 ('PENGUIN PUBLISHING HOUSE'),
                                 ('RAVEN PUBLISHING HOUSE'),
                                 ('MONKEY BUSINESS'),
                                 ('BEST PUBLISHER'),

                                 ('FANTASY & CO'),
                                 ('VERY SCARY BOOKS'),
                                 ('BOOK PUBLISHERS'),
                                 ('GREAT BOOK PUBLISHERS'),
                                 ('MONEY LAUNDERING SCHEME');

INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES
                                               ('WILLIAM', 'SHAKESPEARE'),
                                               ('JANE', 'AUSTEN'),
                                               ('CHARLES', 'DICKENS'),
                                               ('GEORGE', 'ORWELL'),
                                               ('FRANZ', 'KAFKA'),

                                               ('VICTOR', 'HUGO'),
                                               ('OSCAR', 'WILDE'),
                                               ('NEIL', 'GAIMAN'),
                                               ('TERRY', 'PRATCHETT'),
                                               ('STEPHEN', 'KING'),

                                               ('MARK', 'TWAIN'),
                                               ('VIRGINIA', 'WOOLF'),
                                               ('ANTON', 'CHEKHOV'),
                                               ('FYODOR', 'DOSTOEVSKY'),
                                               ('PETER', 'STRAUB'),

                                               ('AGATHA', 'CHRISTIE'),
                                               ('JAMES', 'JOYCE'),
                                               ('EDGAR', 'POE'),
                                               ('JOHN', 'STEINBECK'),
                                               ('ERNEST', 'HEMINGWAY');

INSERT INTO BOOK (TITLE, PRICE, PUBLISHER_ID) VALUES
                                                  ('HAMLET', 29.00, 2),
                                                  ('MACBETH', 29.00, 2),
                                                  ('ROMEO AND JULIET', 29.00, 2),
                                                  ('THE CHERRY ORCHARD', 19.00, 2),
                                                  ('CRIME AND PUNISHMENT', 29.00, 1),

                                                  ('THE BROTHERS KARAMAZOV', 29.00, 1),
                                                  ('LES MISERABLES', 39.00, 4),
                                                  ('THE HUNCHBACK OF NOTRE DAME', 19.00, 5),
                                                  ('A CHRISTMAS CAROL', 9.00, 5),
                                                  ('A TALE OF TWO CITIES', 5.00, 5),

                                                  ('AMERICAN GODS', 11.00, 3),
                                                  ('CORALINE', 16.50, 3),
                                                  ('GOOD OMENS', 1.00, 6),
                                                  ('MURDER ON THE ORIENT EXPRESS', 55.00, 6),
                                                  ('THE RAVEN', 11.45, 7),

                                                  ('OF MICE AND MEN', 77.99, 7),
                                                  ('THE MURDER OF ROGER ACKROYD', 12.99, 8),
                                                  ('ULYSSES', 99.99, 9),
                                                  ('THE SEAGULL', 12.89, 10),
                                                  ('RUSSIAN DRAMA', 150.00, 11),

                                                  ('BRITISH CLASSICS', 150.00, 11),
                                                  ('1984', 19.99, 12),
                                                  ('ANIMAL FARM', 14.99, 13),
                                                  ('PRIDE AND PREJUDICE', 29.80, 14),
                                                  ('THE TRIAL', 18.99, 14),

                                                  ('THE PICTURE OF DORIAN GRAY', 68.99, 15),
                                                  ('MORT', 9.99, 16),
                                                  ('THE COLOUR OF MAGIC', 9.99, 16),
                                                  ('SOUL MUSIC', 9.99, 16),
                                                  ('THE SHINING', 49.99, 17),

                                                  ('IT', 89.99, 17),
                                                  ('GHOST STORY', 79.99, 17),
                                                  ('OLD MAN AND THE SEA', 12.99, 18),
                                                  ('TO THE LIGHT HOUSE', 49.99, 18),
                                                  ('POEMS', 79.99, 19),

                                                  ('THE HAPPY PRINCE ND OTHER STORIES', 99.99, 19),
                                                  ('THE TALISMAN', 39.49, 17);

INSERT INTO BOOK_AUTHORS (BOOK_ID, AUTHORS_ID) VALUES
                                                   (1,1),
                                                   (2, 1),
                                                   (3,1),
                                                   (4, 13),
                                                   (5, 14),
                                                   (6, 14),
                                                   (7, 6),
                                                   (8, 6),
                                                   (9,3),
                                                   (10, 3),
                                                   (11, 8),
                                                   (12, 8),
                                                   (13, 8),
                                                   (13, 9),
                                                   (14, 16),
                                                   (15, 18),
                                                   (16, 19),
                                                   (17, 16),
                                                   (18, 17),
                                                   (19, 13),
                                                   (20, 13),
                                                   (20, 14),
                                                   (21, 1),
                                                   (21, 3),
                                                   (21, 16),
                                                   (22, 4),
                                                   (23, 4),
                                                   (24, 2),
                                                   (25, 5),
                                                   (26, 7),
                                                   (27, 9),
                                                   (28, 9),
                                                   (29,9),
                                                   (30, 10),
                                                   (31, 10),
                                                   (32,15),
                                                   (33, 20),
                                                   (34,12),
                                                   (35, 7),
                                                   (36, 7),
                                                   (37, 15),
                                                   (37, 10);







