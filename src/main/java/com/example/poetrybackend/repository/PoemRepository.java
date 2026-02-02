package com.example.poetrybackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.poetrybackend.model.Poem;

@Repository
public interface PoemRepository extends JpaRepository<Poem, Long>{
    Page<Poem> findByTitleContainingIgnoreCaseOrPoetContainingIgnoreCase(String searchKeysTitle, String searchKeysPoet, Pageable pageable);

    Page<Poem> findAllByIsBookmarked(Number isBookmarked, Pageable pageable);
}
