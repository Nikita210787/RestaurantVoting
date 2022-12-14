
package ru.restaurant_voting.web.userControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.webjars.NotFoundException;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.repository.VoteRepository;
import ru.restaurant_voting.service.VoteService;
import ru.restaurant_voting.web.AuthUser;

import java.net.URI;

@RestController
@RequestMapping(UserVoteController.URL_VOTES)
@Slf4j
@AllArgsConstructor
@Tag(name = "User vote controller")
public class UserVoteController {
    public static final String URL_VOTES = "/v1/api/user/votes";

    RestaurantRepository restaurantRepository;
    VoteRepository voteRepository;
    VoteService voteService;

    /**
     * @return vote authorized user for today
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getTodayVoteForUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getUserVoteForToday - user:{}", authUser.id());
        Vote vote = voteRepository.getTodayVoteForUser(authUser.getUser()).orElseThrow(()->new NotFoundException("The authenticated user is nit vote today"));
        System.out.println(vote);
        return ResponseEntity.ok((Integer) vote.getRestaurant().id());
    }

    /**
     * User vote or change self voice for today
     */
    @PostMapping(value = "/restaurant/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Vote> vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        Vote vote = voteService.voteForRestaurant(id, authUser.getUser());
        log.info("Vote - user:{} restaurant:{}", authUser.id(), id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL_VOTES + "/restaurant/" + id)
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}

