package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restourant_voting.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    //TODO
}
