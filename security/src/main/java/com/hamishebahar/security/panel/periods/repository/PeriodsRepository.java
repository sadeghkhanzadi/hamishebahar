package com.hamishebahar.security.panel.periods.repository;

import com.hamishebahar.security.panel.periods.entity.Periods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodsRepository extends JpaRepository<Periods , Long> {
}
