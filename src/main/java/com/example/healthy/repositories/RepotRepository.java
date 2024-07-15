package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepotRepository extends JpaRepository<Report, Long> {
}
