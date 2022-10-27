package ru.restaurant_voting.error;

import static ru.restaurant_voting.service.VoteService.DEADLINE_FOR_VOTE;

public class DeadLineRuntimeEx extends RuntimeException {
    public DeadLineRuntimeEx() {
        super("You cannot vote or change self vote after "+DEADLINE_FOR_VOTE);
    }
}
