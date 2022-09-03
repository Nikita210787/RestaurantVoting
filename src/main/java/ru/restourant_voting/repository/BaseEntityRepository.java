package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restourant_voting.model.BaseEntity;
@Repository
public interface BaseEntityRepository extends JpaRepository<BaseEntity,Integer> {
    // TODO get by id\

    // TODO delite by id
}
