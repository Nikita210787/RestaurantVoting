package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restourant_voting.model.BaseEntity;

public interface BaseEntityRepository extends JpaRepository<BaseEntity,Integer> {
    // TODO get by id\

    // TODO delite by id
}
