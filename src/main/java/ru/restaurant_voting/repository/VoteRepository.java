package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Vote;

@Repository
public interface VoteRepository<T> extends BaseEntityRepository <Vote>{
    //TODO
}
