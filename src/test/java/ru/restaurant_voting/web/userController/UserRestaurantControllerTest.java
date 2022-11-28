package ru.restaurant_voting.web.userController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.util.RestaurantUtil.getTOsIncludeMenu;
import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_TO_MATCHER;
import static ru.restaurant_voting.web.userControllers.UserRestaurantController.RESTAURANT_URL;


class UserRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = "user")
    void getRestaurantTodayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/today"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu()))).andDo(print());
    }
}