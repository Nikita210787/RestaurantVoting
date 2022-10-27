package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends BaseEntityRepository<Vote> {
    /**
     * @param user
     * @return Vote for user voted today .And it used in VoteService
     */
    @Query("SELECT v FROM Vote v WHERE v.user = :user AND v.date = CURRENT_DATE")
    Optional<Vote> getTodayVoteForUser(User user);


    /**
     * @param user
     * @return Vote on that date for all Users
     */
    @Query("SELECT v FROM Vote v WHERE v.date = CURRENT_DATE ")
    List<Vote> getTAllTodayVote(User user);

}
