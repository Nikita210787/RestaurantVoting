package ru.restaurant_voting.web.userController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.JsonUtil;
import ru.restaurant_voting.web.AbstractControllerTest;
import ru.restaurant_voting.web.testData.MenuTestData;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.restaurant_voting.util.RestaurantUtil.getTOsIncludeMenu;
import static ru.restaurant_voting.web.testData.MenuTestData.MENU_ID_1;
import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_ID_1;
import static ru.restaurant_voting.web.testData.RestaurantTestData.RESTAURANT_TO_MATCHER;
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

    /**
    @GetMapping("/menu/{id}")
    ResponseEntity<Menu> getMenuById(@PathVariable int id) {
        log.info("get Menu by Id :{}", id);
        return ResponseEntity.ok(menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Such Id is not present")));
    }
*/
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getMenuById() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/menu/"+MENU_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menuRepository.findById(MENU_ID_1).get()))
                .andDo(print());
    }

    /**
     * @return All Restaurants with menu for today
     */
    @Test
    @WithUserDetails(value = USER_LOGIN)
    void getRestaurantTodayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(RESTAURANT_URL + "/today"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu())))
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