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
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {


}
