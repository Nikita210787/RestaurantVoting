package ru.restaurant_voting.web.adminControllers.adminRestaurantController.adminMenuController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.restaurant_voting.dto.MenuWithListIdMeal;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MealRepository;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.service.MenuService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.restaurant_voting.util.RestaurantUtil.checkExistRestaurantById;

@RestController
@RequestMapping(value = AdminMenuController.URL_ADMIN_MENU_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin menu Controller ")
public class AdminMenuController {

    public static final String URL_ADMIN_MENU_CONTROLLER = "/v1/api/admin/menus";
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;
    MenuService menuService;
    MealRepository mealRepository;


    /**
     * Add Menu to restaurant by restaurant id (menu has List id Meals)
     */

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@PathVariable int restaurantId, @RequestBody MenuWithListIdMeal menuWithListIdMeal) {
        List<Meal> tempMeal = mealRepository.findAllById(menuWithListIdMeal.listIdMeals());
        Menu createdMenu = new Menu();
        if ((menuWithListIdMeal.date() == null)) {
            createdMenu.setDate(LocalDate.now());
        } else {
            createdMenu.setDate(menuWithListIdMeal.date());
        }
        createdMenu.setMeals(tempMeal);
        createdMenu.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NotFoundException("Restaurant with such id not found")
        ));

        menuRepository.save(createdMenu);
        log.info("Add  new menu {} for restaurant {}", createdMenu, restaurantId);
        return ResponseEntity.ok(createdMenu);

    }


    /**
     * @return update menu by id in restaurant by id
     */

    @PutMapping(value = "/{menuId}/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    ResponseEntity<Menu> update(@PathVariable int menuId, @RequestBody MenuWithListIdMeal menuWithListIdMeal, @PathVariable int restaurantId) {
        checkExistRestaurantById(restaurantRepository, restaurantId);

        Menu updateMenu = menuRepository.findById(menuId).get();
        updateMenu.setMeals(mealRepository.findAllById(menuWithListIdMeal.listIdMeals()));
        menuRepository.save(updateMenu);
        log.info("Add  new menu {} for restaurant {}", updateMenu, restaurantId);
        return ResponseEntity.ok(updateMenu);
    }


    /**
     * delete any menu by id
     */

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete any menu # {}", id);
        menuRepository.deleteById(id);
    }


    /**
     * delete all menu by id restaurant
     */

    @DeleteMapping("/restaurant/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    ResponseEntity<Restaurant> deleteMenuByIdRestaurant(@PathVariable int restaurantId) {
        log.info("delete any today menu by restaurant id # {}", restaurantId);
        Restaurant restaurantForUpdate = restaurantRepository.getbyIdWithMenus(restaurantId).
                orElseThrow(() -> new IllegalRequestDataException("not found restautant with such id"));
        List<Menu> menus = restaurantForUpdate.getMenus();
        MenuService.deletingFromRepository(menuRepository, menus);
        restaurantForUpdate.setMenus(new ArrayList<>());
        return ResponseEntity.ok(restaurantRepository.save(restaurantForUpdate));
    }

}
