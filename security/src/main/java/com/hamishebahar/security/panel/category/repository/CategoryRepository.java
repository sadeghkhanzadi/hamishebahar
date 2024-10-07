package com.hamishebahar.security.panel.category.repository;

import com.hamishebahar.security.panel.category.entity.CourseCategory;
import com.hamishebahar.security.panel.news_events.entity.Events;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CourseCategory, Long> {
    @Query(value = "select C from CourseCategory C where C.is_active = :isActive and C.is_deleted = :isDeleted")
    Page<CourseCategory> findALLByIsActiveAndIsDeleted(@Param("isActive") Boolean isActive,
                                                       @Param("isDeleted") Boolean isDeleted,
                                                       Pageable pageable);
}
