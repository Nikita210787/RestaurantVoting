
package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.web.MatcherFactory;

import java.util.List;

import static ru.restaurant_voting.web.testData.MenuTestData.*;

public class RestaurantTestData {
    public static MatcherFactory.Matcher<RestaurantIncludeMenu> RESTAURANT_TO_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(RestaurantIncludeMenu.class, "currentMenu.restaurant");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Restaurant.class, "menus.restaurant", "votes");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITHOUT_MENU_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Restaurant.class, "menus");

    public static final int RESTAURANT_ID_1 = 1;
    public static final int RESTAURANT_ID_2 = 2;
    public static final int RESTAURANT_ID_3 = 3;
    public static final int RESTAURANT_ID_4 = 4;
    public static final int RESTAURANT_ID_5 = 5;
    public static final int RESTAURANT_ID_6 = 6;
    public static final int RESTAURANT_ID_7 = 7;

    public static final List<Menu> RESTAURANT_1_MENUS = List.of(MENU_1, MENU_2, MENU_3);
    public static final List<Menu> RESTAURANT_2_MENUS = List.of(MENU_4, MENU_5);
    public static final List<Menu> RESTAURANT_3_MENUS = List.of(MENU_6, MENU_7);

    public static final Restaurant RESTAURANT_1 = new Restaurant("rest1", RESTAURANT_1_MENUS, null).addMenusAndGetInstance(RESTAURANT_1_MENUS);
    public static final Restaurant RESTAURANT_2 = new Restaurant("rest2", RESTAURANT_2_MENUS, null).addMenusAndGetInstance(RESTAURANT_2_MENUS);
    public static final Restaurant RESTAURANT_3 = new Restaurant("rest3", RESTAURANT_3_MENUS, null).addMenusAndGetInstance(RESTAURANT_3_MENUS);
    public static final Restaurant RESTAURANT_4 = new Restaurant("rest4", RESTAURANT_1_MENUS, null);
    public static final Restaurant RESTAURANT_5 = new Restaurant("rest5", RESTAURANT_2_MENUS, null);
    public static final Restaurant RESTAURANT_6 = new Restaurant("rest6", RESTAURANT_3_MENUS, null);

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4);
}

