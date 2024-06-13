package com.hamishebahar.security.panel.aboutUs.repository;

import com.hamishebahar.security.panel.aboutUs.entity.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsRepository extends JpaRepository<AboutUs , Long> {
}
