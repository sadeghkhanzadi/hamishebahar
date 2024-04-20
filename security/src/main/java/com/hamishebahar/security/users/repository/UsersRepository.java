package com.hamishebahar.security.users.repository;

import com.hamishebahar.security.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}
