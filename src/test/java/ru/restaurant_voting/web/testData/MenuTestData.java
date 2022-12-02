
package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_TEST;

public class MenuTestData {
    public static MatcherFactory.Matcher<Menu> MENU_MATCHER_IGNORE_ID_AND_REST = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Menu.class, "id", "restaurant");
    public static MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingEqualsComparator(Menu.class);
    public static final int MENU_ID_1 = 1;

    public static final List<Meal> MEALS_TEST = List.of(new Meal("Apple", 100L), new Meal("Banana", 200L), new Meal("Potatoe", 50L));
    public static final Menu MENU_TEST = new Menu(RESTAURANT_TEST, LocalDate.of(2022, 6, 16), MEALS_TEST);
}

