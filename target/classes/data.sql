INSERT INTO USERS (NAME, LOGIN, PASSWORD)
VALUES ('User', 'user', 'userPassword'),
       ('Petr', 'PetrLogin', 'userPassword'),
       ('Vasia', 'VasiaLogin', 'userPassword'),
       ('Admin', 'admin', 'adminPassword');

INSERT INTO USER_ROLES (USER_ID, ROLE)
VALUES (1, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (2, 'USER'),
       (2, 'ADMIN');

INSERT INTO RESTAURANT (TITLE)
VALUES ('Shaurma_na_Kazanskom'),
       ('Shaurma_na_Leningradskom'),
       ('Shaurma_na_Yaroslavskom'),
       ('U_Ashota');

INSERT INTO MENU (RESTAURANT_ID, DATE, MEALS)
VALUES (1, '2022-08-23', 'eggs    85    apple    70    cracker    22        juice    40'),
       (1, '2022-08-26', 'dish5    340        meall08266    660        meall08267    760        meall08268    530'),
       (1, CURRENT_DATE, 'mealToDay    75        meallToDay    330        meallToDay    45'),
       (2, '2022-08-25', 'meal08_25    48        meal08_25    780        meal08_25    88        meall08_254    740'),
       (2, CURRENT_DATE, 'mealToDay    34        mealToDay    660        mealToDay    760'),
       (3, '2022-08-25', 'meal08_25    48        meal08_25    79        meal08_25    880'),
       (3, CURRENT_DATE, 'mealToDay    74        mealToDay    34        mealToDay    66        mealToDay    760');


INSERT INTO VOTE (RESTAURANT_ID, USER_ID)
VALUES (2, 1),
       (2, 4),
       (1, 3),
       (3, 2);