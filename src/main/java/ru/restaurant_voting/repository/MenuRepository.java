package ru.restaurant_voting.repository;

import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends BaseEntityRepository<Menu> {

    List<Menu> getMenuByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    /**
     * @return menu date
     **/
    List<Menu> getMenusByDate(LocalDate localDate);
}
