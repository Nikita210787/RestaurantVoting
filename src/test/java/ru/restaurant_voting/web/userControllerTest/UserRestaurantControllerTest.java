package ru.restaurant_voting.web.userControllerTest;

import org.assertj.core.error.ShouldBeInSameDay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.webjars.NotFoundException;
import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.JsonUtil;
import ru.restaurant_voting.web.AbstractControllerTest;
import ru.restaurant_voting.web.MatcherFactory;
import ru.restaurant_voting.web.testData.MenuTestData;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.util.RestaurantUtil.getTOsIncludeMenu;
import static ru.restaurant_voting.web.testData.MenuTestData.MENU_ID_1;
import static ru.restaurant_voting.web.testData.MenuTestData.MENU_MATCHER;
import static ru.restaurant_voting.web.testData.RestaurantTestData.*;
import static ru.restaurant_voting.web.testData.UserTestData.TEST_USER;
import static ru.restaurant_voting.web.testData.UserTestData.USER_LOGIN;
import static ru.restaurant_voting.web.userControllers.UserRestaurantController.RESTAURANT_URL;


class UserRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;
    /*
     * @return Menu by Id
     */

    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getMenuById() throws Exception {
        MvcResult res = perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/menu/" + MENU_ID_1))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menuRepository.findById(MENU_ID_1).get()))
                /*   MatcherFactory.usingEqualsComparator(Menu.class).assertMatch(
               JsonUtil.readValues(res.getResponse().getContentAsString(),Menu.class),
               menuRepository.findById(MENU_ID_1).orElseThrow(() -> new NotFoundException("Such Id is not present"))
       );*/
                .andDo(print())
                .andReturn();

    }

    /**
     * @return All Restaurants with menu for today
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getRestaurantTodayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/today"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_INCLUDE_MENU_MATCHER_WITHOUT_MEALS.contentJson(getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu())))
                .andDo(print());

    }
    /**
     * @return Today Menu by restaurant Id
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getTodayMenuByRestaurantId() throws Exception {
        MvcResult res = perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/" + RESTAURANT_ID_1))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menuRepository.getMenuByRestaurantIdAndDate(RESTAURANT_ID_1, LocalDate.now())))
                .andDo(print())
                .andReturn();
    }
}