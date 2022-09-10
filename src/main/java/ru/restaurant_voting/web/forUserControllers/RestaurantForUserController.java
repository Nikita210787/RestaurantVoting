package ru.restaurant_voting.web.forUserControllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;
import ru.restaurant_voting.dto.RestaurantTodayMenu;

import java.util.List;

@RestController
@RequestMapping(value = "api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantForUserController {

    RestaurantRepository restaurantRepository;

    /**
     * @return All Restaurants with menu for today
     */
    @GetMapping("/today")
    List<RestaurantTodayMenu> getRestaurantTodayMenu() {
        log.info("getAllRestaurantTodayMenu. RestaurantForUserControllerJob");
        return RestaurantUtil.getTOsTodayMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
    }

}

