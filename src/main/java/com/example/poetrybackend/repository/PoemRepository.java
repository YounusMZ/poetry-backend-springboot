package com.example.poetrybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.poetrybackend.model.Poem;

@Repository
public interface PoemRepository extends JpaRepository<Poem, Number>{
}
