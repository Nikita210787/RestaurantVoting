package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.web.AuthUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserTestData {
    public static final User TEST_USER = new User(
            "userTest"
            , "userTest"
            , "userPasswordTest"
            , Collections.singleton(Role.USER)
            , new ArrayList<>());
    public static final int USER_ID = 1;
    public static final AuthUser TEST_AUTH_USER = new AuthUser(TEST_USER);
    public static final String USER_LOGIN = "user";
    public static final String USER_NAME = "User";
    public static final String USER_PASSWORD = "userPassword";

    public static final Set<Role> ROLE_SET = Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toCollection(HashSet::new));
    public static final User TEST_ADMIN = new User(
            "adminTest"
            , "adminTest"
            , "adminPasswordTest"
            , ROLE_SET
            , new ArrayList<>());
    public static final String ADMIN_LOGIN = "admin";
    public static final String ADMIN_NAME = "Admin";
    public static final String ADMIN_PASSWORD = "adminPassword";
    public static final AuthUser TEST_AUTH_ADMIN = new AuthUser(TEST_ADMIN);
    public static final int ADMIN_ID = 2;
}
