insert into role (name) values ('junior 1'),
                               ('junior 2'),
                               ('junior 3'),
                               ('junior 4'),


                               ('mid 1'),
                               ('mid 2'),
                               ('mid 3'),
                               ('mid 4'),


                               ('senior 1'),
                               ('senior 2'),
                               ('senior 3'),
                               ('senior 4'),


                               ('Legend'),
                               ('Just started'),
                               ('Deactivated'),
                               ('Banned'),
                               ('Deleted'),

                               ('Active'),
                               ('Inactive'),
                               ('Premium');




insert into blog (name) values ('Thai food'),
                               ('American kitchen'),
                               ('German cuisine'),
                               ('French dishes'),
                               ('Polish cooking'),

                               ('Learn Java'),
                               ('How to code in Python'),
                               ('C++ for beginners'),
                               ('SQL for dummies'),
                               ('ABAP lessons'),

                               ('My Diary'),
                               ('My thoughts'),
                               ('Opinions on the state of things'),
                               ('My life story'),
                               ('Writings from the soul'),

                               ('Dogs and you'),
                               ('Cats are great'),
                               ('Lizard owners'),
                               ('How to care for a parrot'),
                               ('Tips for hamster owners');

insert into users (email, managed_blog) values ('anna@mail.com', 1),
                                 ('ben@mail.com', 2),
                                 ('celine@mail.com', 3),
                                 ('damian@mail.com', 4),
                                 ('elizabeth@mail.com', null),

                                 ('franz@mail.com', 6),
                                 ('gwen@mail.com', 7),
                                 ('hilary@mail.com', 8),
                                 ('isabella@mail.com', 9),
                                 ('jordan@mail.com', null),

                                 ('kim@mail.com', 11),
                                 ('leon@mail.com', 12),
                                 ('marta@mail.com', 13),
                                 ('nate@mail.com', 14),
                                 ('olga@mail.com', null),

                                 ('peter@mail.com', 16),
                                 ('renata@mail.com', 17),
                                 ('sebastian@mail.com', 18),
                                 ('talia@mail.com', 19),
                                 ('ulrich@mail.com', null);

insert into article (title, blog_id, author_id) values ('Pad thai', 1, 1),
                                                       ('Khao soi', 1, 5),
                                                       ('Burgers', 2, 2),
                                                       ('Wurst', 3, 3),
                                                       ('Snails',4, 4),
                                                       ('Pierogi', 5, 2),
                                                       ('Kiełbasa', 5, 5),

                                                       ('Arrays', 6, 6),
                                                       ('Why python is great', 7, 7),
                                                       ('Python - is it worth it?', 7, 8),
                                                       ('What are pointers?', 8, 8),
                                                       ('Primary keys', 9, 8),
                                                       ('Foreign keys', 9, 9),

                                                       ('I am sad today', 11,11),
                                                       ('I am happy today', 11, 11),
                                                       ('Is it just me, or...?', 12, 12),
                                                       ('EVERYTHING IS BAD', 13, 13),
                                                       ('I HATE THIS THING', 13, 13),
                                                       ('Story from my childhood', 14, 14),

                                                       ('Best dog breeds', 16, 16),
                                                       ('Pictures of my cat :)', 17, 17),
                                                       ('I like both dogs and cats', 17, 16),
                                                       ('Top 10 lizards to own', 18, 18),
                                                       ('What to feed your parrot', 19, 20),
                                                       ('What NOT to feed your parrot', 19, 20);

insert into users_roles (users_id, roles_id) values (1, 1),
                                                   (1, 18),
                                                   (2, 5),
                                                   (2, 20),
                                                   (2, 18),
                                                   (3, 1),
                                                   (3, 18),
                                                   (4, 1),
                                                   (4, 14),
                                                   (5, 6),
                                                   (5, 18),
                                                   (6, 2),
                                                   (7, 1),
                                                   (8, 12),
                                                   (8, 20),
                                                   (8, 13),
                                                   (9, 3),
                                                   (10, 16),
                                                   (11, 4),
                                                   (12, 2),
                                                   (13, 7),
                                                   (14, 3),
                                                   (15, 17),
                                                   (16, 6),
                                                   (16,20),
                                                   (17, 10),
                                                   (18,4),
                                                   (19, 19),
                                                   (20,9),
                                                   (20, 13);