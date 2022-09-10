
package ru.restaurant_voting.web.forUserControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.repository.VoteRepository;
import ru.restaurant_voting.service.VoteService;
import ru.restaurant_voting.web.AuthUser;

@RestController
@RequestMapping(value = "/api/votes", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    VoteRepository voteRepository;
    VoteService voteService;

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> getUsersVotedToday(@AuthenticationPrincipal AuthUser authUser) {
        log.info("getUserVoteForToday - user:{}", authUser.id());
        return ResponseEntity.of(voteRepository.getUserVotedToday(authUser.getUser()));
    }

    //TODO check POST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vote> vote(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        Vote vote = voteService.voteForRestaurant(restaurantId, authUser.getUser());
        log.info("Vote - user:{} restaurant:{}", authUser.id(), restaurantId);
        return ResponseEntity.ok(vote);
    }
}

