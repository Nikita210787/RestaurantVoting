package ru.restaurant_voting.web.AdminControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.error.IllegalRequestDataException;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.util.RestaurantUtil;
import ru.restaurant_voting.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin restaurant Controller ")
public class AdminRestaurantController {
    RestaurantRepository restaurantRepository;

    /**
     * @return all restaurant with menu for today
     */
    @GetMapping("/today")
    List<RestaurantIncludeMenu> getAllrestaurantWithMenuForToday() {
        log.info("get All RestaurantIncludeMenu for today");
        return RestaurantUtil.getTOsIncludeMenu(restaurantRepository.getAllRestaurantWithTodayMenu());
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
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createRestaurantWithoutMenu(@Valid @RequestBody Restaurant restaurant) {

        log.info("Admin add new Restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant createdRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(createdRestaurant);
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
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTitle( @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant id {}", id);
        Restaurant restaurantForUpdate = restaurantRepository.getByID(id).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant with specified ID does not exist"));
        if(restaurant.isNew()) restaurantForUpdate.setId(id);
        else if(restaurant.id()!= restaurantForUpdate.getId())
            throw new ResponseStatusException( HttpStatus.UNPROCESSABLE_ENTITY, "Restaurant id cannot be changed");
        restaurantForUpdate.setTitle(restaurant.getTitle());
        restaurantRepository.save(restaurantForUpdate);
    }
}
