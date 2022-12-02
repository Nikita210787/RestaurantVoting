package ru.restaurant_voting.web.adminControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.service.MenuService;
import ru.restaurant_voting.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.restaurant_voting.util.MenuUtil.checkDate;
import static ru.restaurant_voting.util.RestaurantUtil.checkExistRestaurantById;
import static ru.restaurant_voting.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.URL_ADMIN_MENU_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin menu Controller ")
public class AdminMenuController {
    static final String URL_ADMIN_MENU_CONTROLLER = "/v1/api/admin/menus";
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;
    MenuService menuService;

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

    /**
     * Add Menu to restaurant by restaurant id
     */
    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@PathVariable int restaurantId, @RequestBody Menu menu) {
        checkDate(menu);
        ValidationUtil.checkNew(menu);
        menu.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        log.info("Add  new menu {} for restaurant {}", menu, restaurantId);
        return ResponseEntity.ok(menuRepository.save(menu));

    }

    /**
     * @return update menu by id in restaurant by id
     */
    @PutMapping(value = "/{menuId}/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    ResponseEntity<Menu> update(@PathVariable int menuId, @RequestBody Menu menu, @PathVariable int restaurantId) {
        assureIdConsistent(menu, menuId);
        checkDate(menu);
        checkExistRestaurantById(restaurantRepository, restaurantId);
        menu.setRestaurant(restaurantRepository.getByID(restaurantId).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant with specified ID does not exist")));
        menuService.checkBeforUpdate(menu);
        log.info("updated {} for Restaurant:{}", menu, restaurantId);
        Menu menuAfterUpdate = menuRepository.save(menu);
        return ResponseEntity.ok(menuAfterUpdate);
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
