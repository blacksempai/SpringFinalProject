package com.javacourse.taxApp.dao;

import com.javacourse.taxApp.model.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractAccountDAO<T extends Account> extends CrudRepository<T,Long> {

    @Query("select e from #{#entityName} as e where e.username = ?1 ")
    Optional<T> findByUsername(String username);

    @Query("select e from #{#entityName} e")
    List<T> findAll();
}
