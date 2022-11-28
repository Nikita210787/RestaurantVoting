package ru.restaurant_voting.web.userControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(UserMenuController.URL)
@AllArgsConstructor
@Tag(name = "User Menu Controller")
public class UserMenuController {
    static final String URL = "/v1/api/user/menus";

    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;

    /**
     * @return All Menu with restaurant id for today
     */
    @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Menu> getAllMenusWithRestaurantIdForToday() {
        log.info("getAllMenuForToday");
        return menuRepository.getMenusByDate(LocalDate.now());
    }

    /**
     * @return Today Menu by restaurant Id
     */
    @GetMapping(value = "/restaurant/{id}")
    List<Menu> getTodayMenuByRestaurantId(@PathVariable int id) {
        log.info("getAll Menu in Restaurant Id :{}", id);
        return menuRepository.getMenuByRestaurantIdAndDate(id, LocalDate.now());
    }


}
