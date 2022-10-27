package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository <User> {
    /**
     * @param login
     * @return User by login
     */
    Optional<User> getByLogin(String login);

    /**
     * @param userId
     * @return User by id
     */
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> getByID(int userId);

}
