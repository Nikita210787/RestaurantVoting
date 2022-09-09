package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.User;

import java.util.Optional;

@Repository
public interface UserRepository<T> extends BaseEntityRepository <User> {
    Optional<User> getByLogin(String login);
}
