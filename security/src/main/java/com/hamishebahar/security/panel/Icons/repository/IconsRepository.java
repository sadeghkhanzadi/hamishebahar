package com.hamishebahar.security.panel.Icons.repository;

import com.hamishebahar.security.panel.Icons.entity.Icons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconsRepository extends JpaRepository<Icons,Long> {
}
