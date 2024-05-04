package com.hamishebahar.panel.category.repository;

import com.hamishebahar.panel.category.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CourseCategory , Long> {
}
