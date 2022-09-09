package ru.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean       //https://stackoverflow.com/questions/11576831/understanding-the-spring-data-jpa-norepositorybean-interface
public interface BaseEntityRepository<T> extends JpaRepository<T,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    default void deleteIfExist(int id) throws Exception {
        if (id == 0)  {throw new IllegalArgumentException("Entity with id=" + id + " not found");}
    }


}
