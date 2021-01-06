package com.kas.repository;

import com.kas.entity.Author;
import com.kas.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author fetchByName(String name);
}
