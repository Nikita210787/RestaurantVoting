package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restourant_voting.model.Restaurant;

public interface RestaurantRepositiry  extends JpaRepository<Restaurant,Integer> {
}
