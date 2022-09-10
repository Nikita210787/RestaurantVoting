package ru.restaurant_voting.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.repository.VoteRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {
    public static final LocalTime DEADLINE_FOR_VOTE = LocalTime.of(11, 0);

    VoteRepository voteRepository;

    RestaurantRepository restaurantRepository;

    public Vote voteForRestaurant(int restaurantId, User user) {
        Restaurant restaurantToVote = null;
        try {
            restaurantToVote = (Restaurant) restaurantRepository.findById(restaurantId).orElseThrow(
                    () -> new EntityNotFoundException("Restaurant with such id not present"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        Optional<Vote> usersVotingToday = voteRepository.getUserVotedToday(user);
        if (usersVotingToday.isPresent() && LocalTime.now().isAfter(DEADLINE_FOR_VOTE)) {
            log.info("Vote after deadline - user:{}", user.id());
            throw new RuntimeException("You cannot voting after 11:00 p.m.");
        }
        Vote voteToSave = usersVotingToday.orElse(new Vote(user, restaurantToVote));
        if (!usersVotingToday.get().isNew())
            voteToSave.setRestaurant(restaurantToVote);

        return (Vote) voteRepository.save(voteToSave);
    }
}
