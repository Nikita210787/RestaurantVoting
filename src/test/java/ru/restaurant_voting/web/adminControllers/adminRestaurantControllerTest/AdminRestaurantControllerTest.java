package ru.restaurant_voting.web.adminControllers.adminRestaurantControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.JsonUtil;
import ru.restaurant_voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.util.RestaurantUtil.getTOsIncludeMenu;
import static ru.restaurant_voting.web.adminControllers.adminRestaurantController.AdminRestaurantController.URL_ADMIN_RESTAURANT_CONTROLLER;
import static ru.restaurant_voting.web.testData.RestaurantTestData.*;
import static ru.restaurant_voting.web.testData.UserTestData.ADMIN_LOGIN;

class AdminRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;


    /**
     * @return All Restaurants with menu
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void getAllrestaurantWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(URL_ADMIN_RESTAURANT_CONTROLLER))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurantRepository.findAll()))
                .andDo(print());
    }

    /**
     * add new restaurant
     */
    @Test
    @WithUserDetails(ADMIN_LOGIN)
    void createRestaurantWithoutMenu() throws Exception {
        perform(MockMvcRequestBuilders.post(URL_ADMIN_RESTAURANT_CONTROLLER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_TEST)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITHOUT_ID_AND_MENU_AND_VOTES.contentJson(RESTAURANT_TEST))
                .andExpect(status().isCreated());
    }

    /**
     * delite restaurant by id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void delete() throws Exception {
        MvcResult result = perform(MockMvcRequestBuilders.delete(URL_ADMIN_RESTAURANT_CONTROLLER + "/" + RESTAURANT_ID_1))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(restaurantRepository.findById(RESTAURANT_ID_1).isEmpty());
        assertTrue(menuRepository.getMenusByRestaurantId(RESTAURANT_ID_1).isEmpty());
    }

    /**
     * Update Menu menu restaurant by restaurant id
     */
    @Test
    @WithUserDetails(value = ADMIN_LOGIN)
    void updateTitle() throws Exception {
        Restaurant newNamdeRestauran = restaurantRepository.findById(RESTAURANT_ID_1).orElseThrow(null);
        newNamdeRestauran.setTitle("newNAme");
        MvcResult res = perform(MockMvcRequestBuilders.put(URL_ADMIN_RESTAURANT_CONTROLLER + "/" + RESTAURANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newNamdeRestauran)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        RESTAURANT_MATCHER_WITHOUT_ID_AND_MENU_AND_VOTES.assertMatch(restaurantRepository.getByID(RESTAURANT_ID_1).get(), newNamdeRestauran);
    }
}
