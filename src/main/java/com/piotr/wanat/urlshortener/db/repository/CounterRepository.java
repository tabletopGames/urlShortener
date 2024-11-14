package com.piotr.wanat.urlshortener.db.repository;

import com.piotr.wanat.urlshortener.db.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}
