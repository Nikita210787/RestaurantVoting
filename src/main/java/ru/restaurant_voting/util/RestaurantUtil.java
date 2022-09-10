package ru.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.dto.RestaurantTodayMenu;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTodayMenu> getTOsTodayMenu(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(r -> r.getMenus().size() > 0)
                .map(r -> new RestaurantTodayMenu(
                        r.id(),
                        r.getTitle(),
                        r.getMenus().stream().findAny().orElseThrow()))
                .collect(Collectors.toList());
    }
}

