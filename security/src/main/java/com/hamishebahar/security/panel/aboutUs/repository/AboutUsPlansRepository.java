package com.hamishebahar.security.panel.aboutUs.repository;

import com.hamishebahar.security.panel.aboutUs.entity.AboutUsPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsPlansRepository extends JpaRepository<AboutUsPlans , Long> {

}
