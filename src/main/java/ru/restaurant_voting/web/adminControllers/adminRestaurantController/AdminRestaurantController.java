package ru.restaurant_voting.web.adminControllers.adminRestaurantController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.URL_ADMIN_RESTAURANT_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin restaurant Controller ")
public class AdminRestaurantController {
    public static final String URL_ADMIN_RESTAURANT_CONTROLLER = "/v1/api/admin/restaurants";
    RestaurantRepository restaurantRepository;

    /**
     * @return restaurant By id
     */
    @GetMapping("/{id}")
    ResponseEntity<Restaurant> getRestaurantWithMenuById(@PathVariable int id) {
        log.info("get Restaurant by id : ", id);
        return ResponseEntity.ok().body(restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with such id not Exists")));
    }

    /**
     * @return all restaurant
     */
    @GetMapping()
    List<Restaurant> getAllrestaurantWithMenu() {
        log.info("get all Restaurant");
        return restaurantRepository.findAll();
    }

    /**
     * add new restaurant
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createRestaurantWithoutMenu(@RequestParam String title) {
        log.info("Admin add new Restaurant {}", title);
        Restaurant createdRestaurant = new Restaurant(
                title
                , List.of()
                , List.of());
        restaurantRepository.save(createdRestaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    /**
     * delite restaurant by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete any user # {}", id);
        restaurantRepository.deleteById(id);
    }

    /**
     * update restaurant by id
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurant> updateTitle(@RequestParam String name, @PathVariable int id) {
        log.info("update restaurant id {}", id);
        Restaurant restaurantForUpdate = restaurantRepository.getByID(id).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant with specified ID does not exist"));
        restaurantForUpdate.setTitle(name);
        restaurantRepository.save(restaurantForUpdate);
        return ResponseEntity.ok(restaurantForUpdate);
    }
}
