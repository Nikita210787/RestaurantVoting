package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restourant_voting.model.Restaurant;
@Repository
public interface RestaurantRepositiry  extends JpaRepository<Restaurant,Integer> {
    //TODO
}
