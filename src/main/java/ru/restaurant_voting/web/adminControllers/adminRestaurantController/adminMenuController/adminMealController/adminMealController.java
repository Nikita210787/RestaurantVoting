package ru.restaurant_voting.web.adminControllers.adminRestaurantController.adminMenuController.adminMealController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.repository.MealRepository;
import ru.restaurant_voting.web.adminControllers.adminRestaurantController.adminMenuController.AdminMenuController;

import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.URL_ADMIN_MENU_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@Tag(name = "Admin meal Controller ")

public class adminMealController {
    public static final String URL_ADMIN_MENU_CONTROLLER = "/v1/api/admin/meals";
    private MealRepository mealRepository;

    /**
     *
     * @return all Meal
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAllMeal(){
        return mealRepository.findAll();
    }

    /**
     *
     * @param meal
     * @return created meal
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Meal> create(@RequestBody Meal meal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mealRepository.save(meal));
    }

    /**
     * delete meal by id
     * @param id
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(int id){
        mealRepository.deleteById(id);
    }

}
