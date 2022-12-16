INSERT INTO USERS (NAME, LOGIN, PASSWORD)
VALUES ('User', 'user', '{noop}userPassword'),
       ('Petr', 'PetrLogin', '{noop}userPassword'),
       ('Vasia', 'VasiaLogin', '{noop}userPassword'),
       ('Admin', 'admin', '{noop}adminPassword'),
       ('User1', '1', '{noop}1'),
       ('User2', '2', '{noop}2');

INSERT INTO USER_ROLES (USER_ID, ROLE )
VALUES (1, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (2, 'USER'),
       (4, 'ADMIN'),
       (5, 'USER'),
       (6, 'USER'),
       (6, 'ADMIN');

INSERT INTO RESTAURANT (TITLE)
VALUES ('Shaurma_na_Kazanskom'),
       ('Shaurma_na_Leningradskom1'),
       ('Shaurma_na_Leningradskom2'),
       ('Shaurma_na_Yaroslavskom1'),
       ('Shaurma_na_Yaroslavskom2'),
       ('Shaurma_na_Yaroslavskom3'),
       ('Shaurma_na_Yaroslavskom4'),
       ('Shaurma_na_Yaroslavskom5'),
       ('U_Ashota');

INSERT INTO MENU (RESTAURANT_ID, DATE)
VALUES (1, '2022-08-23'),
       (1, '2022-08-26'),
       (1, CURRENT_DATE),
       (2, '2022-08-25'),
       (2, CURRENT_DATE),
       (3, '2022-08-25'),
       (3, CURRENT_DATE);
INSERT INTO MEAL(TITLE,PRICE)
VALUES ('sssssss','10'),
       ('aaaaaaa','20'),
       ('xxxxxxx','30'),
       ('jjjjjjj','40'),
       ('zzzzzzz','50'),
       ('eeeeeee','60'),
       ('hhhhhhh','60');
INSERT INTO MENU_MEAL(MENU_ID, MEAL_ID)
VALUES (1,2 ),
    (1,1),
    (1,3),
    (2,2),
    (3,5),
    (4,6);
INSERT INTO VOTE (RESTAURANT_ID, USER_ID)
VALUES (2, 1),
       (2, 4),
       (1, 3),
       (3, 2);