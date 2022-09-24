package ru.restaurant_voting.web.AdminControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;
import ru.restaurant_voting.util.ValidationUtil;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

@RestController
@RequestMapping(value = "/api/admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Menu Controller (for Admin)")
public class AdminRestaurantController {
    RestaurantRepository restaurantRepository;

    /**
     * @return all restaurant with menu for today
     */
    @GetMapping("/today")
    List<RestaurantIncludeMenu> getAllRestaurant() {
        log.info("getAllRestaurantIncludeMenu.");
        return RestaurantUtil.getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
    }

    /**
     * @return all restaurant
     */
    @GetMapping()
    List<Restaurant> getAllrestaurantWithMenuForToday() {
        log.info("get all Restaurant with menu for today");
        return restaurantRepository.findAll();
    }

    /**
     * add new restaurant
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {

        log.info("Admin add new Restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant createdRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    /**
     * delite restaurant by id
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete any user # {}", id);
        restaurantRepository.deleteById(id);
    }

    /**
     * update restaurant by id
     */
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant id {}", id);
        Restaurant restaurantForUpdate = restaurantRepository.getByID(id).orElseThrow(
                () -> new EntityNotFoundException("Restaurant with specified ID does not exist"));
        if(restaurant.isNew()) restaurantForUpdate.setId(restaurant.getId());
        else if(restaurant.id()!= restaurantForUpdate.getId())
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY, "Restaurant id cannot be changed");
        restaurantForUpdate.setTitle(restaurant.getTitle());
        restaurantRepository.save(restaurantForUpdate);
    }
}
