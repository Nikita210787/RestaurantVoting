
package ru.restaurant_voting.web.UserControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.repository.VoteRepository;
import ru.restaurant_voting.service.VoteService;
import ru.restaurant_voting.web.AuthUser;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/api/user/votes")
@Slf4j
@AllArgsConstructor
@Tag(name = "User vote controller")
public class UserVoteController {
    RestaurantRepository restaurantRepository;
    VoteRepository voteRepository;
    VoteService voteService;

    /**
     * @return vote authorized user for today
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> getTodayVoteForUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getUserVoteForToday - user:{}", authUser.id());
        return ResponseEntity.of(voteRepository.getTodayVoteForUser(authUser.getUser()));
    }

    /**
     * ;
     * User vote or change self voice for today
     */
    @PostMapping(value = "/restaurant/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Vote> vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        Vote vote = voteService.voteForRestaurant(id, authUser.getUser());
        log.info("Vote - user:{} restaurant:{}", authUser.id(), id);
        /*URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/api/user/votes" + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
*/  return ResponseEntity.ok(vote);
    }
}

