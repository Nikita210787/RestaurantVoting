package ru.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.dto.RestaurantIncludeMenu;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static void checkExistRestaurantById(RestaurantRepository restaurantRepository, int restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new EntityNotFoundException("Restaurant with specified ID not exists");
        }

    }

    public static List<RestaurantIncludeMenu> getTOsIncludeMenu(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(r -> r.getMenus().size() > 0)
                .map(r -> new RestaurantIncludeMenu(
                        r.id(),
                        r.getTitle(),
                        r.getMenus().stream().findAny().orElseThrow()))
                .collect(Collectors.toList());
    }
}

