package ru.restaurant_voting.web.UserControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/user/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "User restaurant Controller")
public class UserRestaurantController {

    RestaurantRepository restaurantRepository;

    /**
     * @return All Restaurants with menu for today
     */
    @GetMapping("/today")
    List<RestaurantIncludeMenu> getRestaurantTodayMenu() {
        log.info("getAllRestaurantTodayMenu. RestaurantForUserControllerJob");
        return RestaurantUtil.getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
    }

}

