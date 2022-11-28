package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.web.AuthUser;
import ru.restaurant_voting.web.MatcherFactory;

import java.util.ArrayList;
import java.util.Collections;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final String ADMIN_LOGIN = "admin";
    public static final String USER_LOGIN = "user";
    public static final String USER_NAME = "User";
   public static final String USER_PASSWORD = "userPassword";
   public static final User TEST_USER = new User(
            "userTest"
            , "userTest"
            , "userPasswordTest"
            , Collections.singleton(Role.USER)
            , new ArrayList<>());
   public static final AuthUser TEST_AUTH_USER=new AuthUser(TEST_USER);

    public static final MatcherFactory.Matcher<User> USER_MATCHER =
            MatcherFactory.usingEqualsComparator(User.class);
}
