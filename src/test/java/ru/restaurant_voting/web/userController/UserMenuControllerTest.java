package ru.restaurant_voting.web.userController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.web.AbstractControllerTest;
import ru.restaurant_voting.web.testData.MenuTestData;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_ID_1;
import static ru.restaurant_voting.web.testData.UserTestData.USER_LOGIN;
import static ru.restaurant_voting.web.userControllers.UserMenuController.URL_USER_MENU_CONTROLLER;

class UserMenuControllerTest extends AbstractControllerTest {
    @Autowired
    MenuRepository menuRepository;

    /**
     * @return All Menu with restaurant id for today
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getAllMenusWithRestaurantIdForToday() throws Exception {
        MvcResult res = perform(MockMvcRequestBuilders.get(URL_USER_MENU_CONTROLLER + "/today"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menuRepository.getMenusByDate(LocalDate.now())))
                .andDo(print())
                .andReturn();
    }

    /**
     * @return Today Menu by restaurant Id
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getTodayMenuByRestaurantId() throws Exception {
        MvcResult res = perform(MockMvcRequestBuilders.get(URL_USER_MENU_CONTROLLER + "/restaurant/" + RESTAURANT_ID_1))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menuRepository.getMenuByRestaurantIdAndDate(RESTAURANT_ID_1, LocalDate.now())))
                .andDo(print())
                .andReturn();
    }
}