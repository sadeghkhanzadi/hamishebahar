package com.hamishebahar.security.panel.Links.repository;

import com.hamishebahar.security.panel.Links.entity.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends JpaRepository<Links,Long> {
}
