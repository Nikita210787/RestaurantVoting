package ru.restaurant_voting.web.testData;

import ru.restaurant_voting.model.Vote;
import ru.restaurant_voting.web.MatcherFactory;

public class VoteTestData {

    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int EXISTING_RESTAURANT_ID = 3;

    public static final int NON_EXISTENT_RESTAURANT_ID = 0;

}
