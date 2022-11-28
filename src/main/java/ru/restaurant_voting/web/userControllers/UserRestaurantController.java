package ru.restaurant_voting.web.userControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.RESTAURANT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "User restaurant Controller")
public class UserRestaurantController {

    public static final String RESTAURANT_URL = "/v1/api/user/restaurants";
    RestaurantRepository restaurantRepository;

    /**
     * @return All Restaurants with menu for today
     */
    @GetMapping("/today")
    List<RestaurantIncludeMenu> getRestaurantTodayMenu() {
        log.info("getAllRestaurantTodayMenu. RestaurantForUserController");
        return RestaurantUtil.getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
    }

}

