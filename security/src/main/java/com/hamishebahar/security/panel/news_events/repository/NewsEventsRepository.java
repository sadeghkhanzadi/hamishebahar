package com.hamishebahar.security.panel.news_events.repository;

import com.hamishebahar.security.panel.news_events.entity.Events;
import com.hamishebahar.security.panel.teachers.entity.Teachers;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface NewsEventsRepository extends JpaRepository<Events, Long> {
    @Query(value = "select C from Events C where C.startDate = :startTime " +
            "and C.endDate = :endTime " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Events> findALL(@Param("startTime") String startTime,
                         @Param("endTime") String endTime,
                         Pageable pageable);
    @Query(value = "select C from Events C where C.startDate = :startTime " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Events> findALL(@Param("startTime") String startTime,
                         Pageable pageable);

    @Query("select T from Events T where T.is_deleted = false and T.is_active = true")
    Page<Events> findAllIsActive(Pageable pageable);
}
