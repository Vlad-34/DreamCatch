package com.example.dreamcatch.repository;

import com.example.dreamcatch.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findAllByUserId(Long userId);
}