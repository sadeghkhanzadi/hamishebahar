package com.hamishebahar.security.panel.contactUs.repository;

import com.hamishebahar.security.panel.contactUs.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
