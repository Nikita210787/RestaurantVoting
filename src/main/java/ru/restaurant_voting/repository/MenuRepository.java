package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;

@Repository
public interface MenuRepository<T> extends BaseEntityRepository <Menu> {
    //TODO
}
