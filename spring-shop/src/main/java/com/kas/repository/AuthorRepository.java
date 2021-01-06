package com.kas.repository;


import com.kas.dto.AuthorDto;
import com.kas.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM author AS a WHERE a.id < ?1 ORDER BY a.id DESC LIMIT ?2",nativeQuery = true)
    List<Author> fetchAll(long id, int limit);

    @Query(value = "SELECT name, age FROM author AS a WHERE a.id < ?1 ORDER BY a.id DESC LIMIT ?2",nativeQuery = true)
    List<AuthorDto> fetchAllDto(long id, int limit);
}
