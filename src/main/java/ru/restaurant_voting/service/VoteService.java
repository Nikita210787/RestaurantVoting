package ru.restaurant_voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restaurant_voting.error.DeadLineRuntimeEx;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.repository.VoteRepository;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {
    public static final LocalTime DEADLINE_FOR_VOTE = LocalTime.of(11, 0);
    VoteRepository voteRepository;
    RestaurantRepository restaurantRepository;
    TimeService timeService ;

    public Vote voteForRestaurant(int restaurantId, User user)  {
        Restaurant restaurantToVote = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new EntityNotFoundException("Restaurant with such id not present")
        );

        Optional<Vote> todayVoteForUser = voteRepository.getTodayVoteForUser(user);
        if (todayVoteForUser.isPresent() && LocalTime.now(timeService.getClock()).isAfter(DEADLINE_FOR_VOTE)) {
            log.info("User want change self voice after deadline - user:{}", user.id());
            throw new DeadLineRuntimeEx();
        }
        Vote voteToSave = todayVoteForUser.orElse(new Vote(user, restaurantToVote));
        return voteRepository.save(voteToSave);
    }
}
