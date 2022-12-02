
package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static MatcherFactory.Matcher<RestaurantIncludeMenu> RESTAURANT_TO_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(RestaurantIncludeMenu.class, "todayMenu.restaurant");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Restaurant.class, "votes");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER_WITHOUT_ID_AND_MENU_AND_VOTES = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Restaurant.class, "menus", "id", "votes");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITHOUT_MENU_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Restaurant.class, "menu");

    public static final int RESTAURANT_ID_1 = 1;
    public static final Restaurant RESTAURANT_TEST = new Restaurant(
            "rest1",
            List.of(new Menu()),
            null);
}

