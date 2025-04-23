package com.hamishebahar.security.users.repository;

import com.hamishebahar.security.users.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.id = :userId")
    Users findByUserId(@Param("userId") Long userId);

    Users findByEmail(String email);

    Users findByPhoneNumber(String phoneNumber);

    boolean existsUsersByEmail(String identity);
    boolean existsUsersByPhoneNumber(String identity);

    Page<Users> findAllByNameAndPhoneNumberAndNationalCode(String name,
                                                           String phoneNumber,
                                                           String nationalCode,
                                                           Pageable pageable);

    Page<Users> findAllByNameAndPhoneNumber(String name,
                                            String phoneNumber,
                                            Pageable pageable);

    Page<Users> findAllByNameAndNationalCode(String name,
                                             String nationalCode,
                                             Pageable pageable);

    Page<Users> findAllByPhoneNumberAndNationalCode(String phoneNumber,
                                                    String nationalCode,
                                                    Pageable pageable);

    Page<Users> findAllByName(String name,
                              Pageable pageable);

    Page<Users> findAllByPhoneNumber(String phoneNumber,
                                     Pageable pageable);

    Page<Users> findAllByNationalCode(String nationalCode,
                                      Pageable pageable);

    Page<Users> findAllById(Long id ,
                            Pageable pageable);
}
