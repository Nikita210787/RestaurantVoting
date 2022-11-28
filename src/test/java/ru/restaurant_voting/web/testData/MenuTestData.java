
package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.restaurant_voting.web.testData.MealTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingRecursiveIgnoreFieldsComparator(Menu.class);

  /*  public static final int MENU_ID_1 = 1;
    public static final int MENU_ID_2 = 2;
    public static final int MENU_ID_3 = 3;
    public static final int MENU_ID_4 = 4;
    public static final int MENU_ID_5 = 5;
    public static final int MENU_ID_6 = 6;
    public static final int MENU_ID_7 = 7;
    public static final int MENU_ID_NONEXISTENT = Integer.MAX_VALUE;

    public static final List<Meal> MENU_1_MealES = List.of(MEAL_1, MEAL_2, MEAL_3, MEAL_4);
    public static final List<Meal> MENU_2_MealES = List.of(MEAL_5, MEAL_6, MEAL_7, MEAL_8);
    public static final List<Meal> MENU_3_MealES = List.of(MEAL_9, MEAL_10, MEAL_11);
    public static final List<Meal> MENU_4_MealES = List.of(MEAL_1, MEAL_2, MEAL_3, MEAL_4);
    public static final List<Meal> MENU_5_MealES = List.of(MEAL_5, MEAL_6, MEAL_7);
    public static final List<Meal> MENU_6_MealES = List.of(MEAL_1, MEAL_2, MEAL_3);
    public static final List<Meal> MENU_7_MealES = List.of(MEAL_4, MEAL_5, MEAL_6, MEAL_7);

    public static final Menu MENU_1 = new Menu(RestaurantTestData.RESTAURANT_1, LocalDate.of(2022, 6, 16), MENU_1_MealES);
    public static final Menu MENU_2 = new Menu(RestaurantTestData.RESTAURANT_2, LocalDate.of(2022, 6, 15), MENU_2_MealES);
    public static final Menu MENU_3 = new Menu(RestaurantTestData.RESTAURANT_3, LocalDate.now(), MENU_3_MealES);
    public static final Menu MENU_4 = new Menu(RestaurantTestData.RESTAURANT_4, LocalDate.of(2022, 6, 15), MENU_4_MealES);
    public static final Menu MENU_5 = new Menu(RestaurantTestData.RESTAURANT_5, LocalDate.now(), MENU_5_MealES);
    public static final Menu MENU_6 = new Menu(RestaurantTestData.RESTAURANT_1, LocalDate.of(2022, 6, 15), MENU_6_MealES);
    public static final Menu MENU_7 = new Menu(RestaurantTestData.RESTAURANT_1, LocalDate.now(), MENU_7_MealES);
*/}

