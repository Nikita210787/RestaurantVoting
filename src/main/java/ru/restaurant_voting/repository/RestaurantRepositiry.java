package ru.restaurant_voting.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Restaurant;

@Repository
public interface RestaurantRepositiry  extends JpaRepository<Restaurant,Integer> {
    //TODO
}
