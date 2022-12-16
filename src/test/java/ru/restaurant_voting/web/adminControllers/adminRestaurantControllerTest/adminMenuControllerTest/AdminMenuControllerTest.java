package ru.restaurant_voting.web.adminControllers.adminRestaurantControllerTest.adminMenuControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.dto.MenuWithListIdMeal;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.JsonUtil;
import ru.restaurant_voting.web.AbstractControllerTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.web.adminControllers.adminRestaurantController.adminMenuController.AdminMenuController.URL_ADMIN_MENU_CONTROLLER;
import static ru.restaurant_voting.web.testData.MenuTestData.*;
import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_ID_1;
import static ru.restaurant_voting.web.testData.UserTestData.ADMIN_LOGIN;

class AdminMenuControllerTest extends AbstractControllerTest {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    /**
     * Add Menu to restaurant, by id restaurant
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void create() throws Exception {
        MenuWithListIdMeal menuWithListIdMeal = new MenuWithListIdMeal(List.of(6), LocalDate.MAX);

        MvcResult res = perform(MockMvcRequestBuilders.post(URL_ADMIN_MENU_CONTROLLER + "/restaurant/" + RESTAURANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuWithListIdMeal)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Menu created = MENU_MATCHER.readFromJson(res);
        created.setRestaurant(restaurantRepository.getReferenceById(RESTAURANT_ID_1));

        MENU_MATCHER_IGNORE_MEAL.assertMatch(
                created,
                menuRepository.getMenuByRestaurantIdAndDate(RESTAURANT_ID_1, LocalDate.MAX).get(0));
    }

    /**
     * Update Menu menu restaurant by restaurant id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void update() throws Exception {
        MenuWithListIdMeal menuWithListIdMeal = new MenuWithListIdMeal(List.of(6), LocalDate.MAX);

        MvcResult res = perform(MockMvcRequestBuilders.put(URL_ADMIN_MENU_CONTROLLER + "/" + MENU_ID_1 + "/restaurant/" + RESTAURANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuWithListIdMeal)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Menu created = MENU_MATCHER.readFromJson(res);
        created.setRestaurant(restaurantRepository.getReferenceById(RESTAURANT_ID_1));
    }

    /**
     * delete any menu by id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void delete() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.delete(URL_ADMIN_MENU_CONTROLLER + "/" + MENU_ID_1))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(menuRepository.findById(MENU_ID_1).isEmpty());

    }

    /**
     * delete any menu by restaurant id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void deleteMenuByIdRestaurant() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.delete(URL_ADMIN_MENU_CONTROLLER + "/restaurant/" + RESTAURANT_ID_1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(menuRepository.getMenusByRestaurantId(RESTAURANT_ID_1).isEmpty());
    }
}