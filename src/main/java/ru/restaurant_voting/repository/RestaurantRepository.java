package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends BaseEntityRepository<Restaurant> {
    /**
     * return all Restaurant with today menu. used in VoteService
     */
    //SQL Query : @Query("SELECT * FROM Restaurant r JOIN menu m WHERE m.date = CURRENT_DATE")
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus m WHERE m.date = CURRENT_DATE")
    List<Restaurant> getAllRestaurantWithTodayMenu();
    /**
     * return all Restaurant with menu. For admin.
     */
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus")
    List<Restaurant> getWithMenusallREstaurant();
        /**
     * return all Restaurant with menu by ID.
     */
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus m WHERE r.id = :restaurantId")
    Optional<Restaurant> getbyIdWithMenus(int restaurantId);

    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> getByID(int id);
    Optional<Restaurant> getRestaurantById(int id);
}
