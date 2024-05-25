package com.hamishebahar.security.panel.category.repository;

import com.hamishebahar.security.panel.category.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CourseCategory , Long> {
}
