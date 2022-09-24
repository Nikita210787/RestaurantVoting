package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository <User> {
    Optional<User> getByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> getByID(int userId);

}
