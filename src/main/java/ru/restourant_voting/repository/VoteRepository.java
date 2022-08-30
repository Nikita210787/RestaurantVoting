package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restourant_voting.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
