package ru.restaurant_voting.web.userControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.RESTAURANT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@Tag(name = "User restaurant Controller")
public class UserRestaurantController {

    public static final String RESTAURANT_URL = "/v1/api/user/restaurants";
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;

    /**
     * @return All Restaurants with menu for today
     */
    @GetMapping("/today")
    List<RestaurantIncludeMenu> getRestaurantTodayMenu() {
        log.info("getAllRestaurantTodayMenu. RestaurantForUserController");
        return RestaurantUtil.getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
    }

    /**
     * @return Today Menu by restaurant Id
     */
    @GetMapping("/{id}")
    List<Menu> getTodayMenuByRestaurantId(@PathVariable int id) {
        log.info("getAll Menu in Restaurant Id :{}", id);
        return menuRepository.getMenuByRestaurantIdAndDate(id, LocalDate.now());
    }


    /**
     * @return Menu by Id
     */
    @GetMapping("/menu/{id}")
    ResponseEntity<Menu> getMenuById(@PathVariable int id) {
        log.info("get Menu by Id :{}", id);
        return ResponseEntity.ok(menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Such Id is not present")));
    }

}

