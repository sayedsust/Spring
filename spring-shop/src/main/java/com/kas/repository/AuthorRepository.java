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

import java.util.Optional;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Transactional(readOnly = true)
    Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenre(String genre, Pageable pageable);

    @Query(value = "SELECT a FROM Author a WHERE a.genre = ?1", countQuery = "SELECT COUNT(*) FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenreExplicitCount(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE genre = ?1",nativeQuery = true)
    public Page<Author> fetchByGenreNative(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE genre = ?1", countQuery = "SELECT COUNT(*) FROM author WHERE genre = ?1", nativeQuery = true)
    public Page<Author> fetchByGenreNativeExplicitCount(String genre, Pageable pageable);

    /*
    Using Slice API
     */
    @Transactional(readOnly = true)
    @Query(value = "SELECT a FROM Author a")
    Slice<Author> fetchAll(Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name as name, a.age as age FROM Author a")
    Slice<AuthorDto> fetchAllDto(Pageable pageable);
}
