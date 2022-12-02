package ru.restaurant_voting.repository;

import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends BaseEntityRepository<Menu> {
    /**
     *
     * @param restaurantId
     * @param localDate
     * @return List menu restaurant by id, with date
     */
    List<Menu> getMenuByRestaurantIdAndDate(int restaurantId, LocalDate localDate);

    /**
     *
     * @param localDate
     * @return menu date.
     **/

    List<Menu> getMenusByDate(LocalDate localDate);
    /**
     *
     * @param restaurantId
     * @return List menu restaurant by id
     */
    List<Menu> getMenusByRestaurantId(int restaurantId);
}
