package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Meal;
import ru.restaurant_voting.model.Menu;

import java.util.List;

@Repository
public interface MealRepository extends BaseEntityRepository<Meal> {

}
