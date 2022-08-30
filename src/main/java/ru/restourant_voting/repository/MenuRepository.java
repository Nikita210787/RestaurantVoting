package ru.restourant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restourant_voting.model.Menu;

public interface MenuRepository extends JpaRepository <Menu,Integer> {
    //
}
