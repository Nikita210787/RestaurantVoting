package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.model.Vote;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository<T> extends BaseEntityRepository<Vote> {
    /**
     * return User who voting today .It usd in VoteServis
     **/
    @Query("SELECT v FROM Vote v WHERE v.user = :user AND v.date = CURRENT_DATE")
    Optional<Vote> getUserVotedToday(User user);
    @Query("SELECT v FROM Vote v WHERE v.date = CURRENT_DATE ")
    List<Vote> getAllUsersVotedToday(User user);
}
