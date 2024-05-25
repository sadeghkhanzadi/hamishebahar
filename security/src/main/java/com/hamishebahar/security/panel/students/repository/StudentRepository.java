package com.hamishebahar.security.panel.students.repository;

import com.hamishebahar.security.panel.students.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
    @Query(value = "select C from Students C where C.nationalCode = :nationalCode " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Students> findAllByNationalCode(@Param("nationalCode") String nationalCode, Pageable pageable);

    @Query(value = "select C from Students C where C.phoneNumber = :phoneNumber " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Students> findAllByPhoneNumber(@Param("phoneNumber") String phoneNumber, Pageable pageable);

    @Query(value = "select C from Students C where C.studentAge = :studentAge " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Students> findAllByStudentAge(@Param("studentAge") String studentAge, Pageable pageable);

    @Query(value = "select C from Students C where C.studentCode = :studentCode " +
            "and C.is_active = true " +
            "and C.is_deleted = false")
    Page<Students> findAllByStudentCode(@Param("studentCode") String studentCode, Pageable pageable);
}
