package com.hamishebahar.security.panel.teachers.repository;

import com.hamishebahar.security.panel.teachers.entity.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeacherRepository extends JpaRepository<Teachers , Long> {
    @Query(value = "select T from Teachers T where T.firstName = :firstName " +
            "and T.firstName = :firstName " +
            "and T.lastName = :lastName " +
            "and T.jobTitle = :jobTitle " +
            "and T.is_active = true " +
            "and T.is_deleted = false")
    Page<Teachers> findAllByFirstNameAndLastNameAndJobTitle(@Param("firstName") String firstName,@Param("lastName") String lastName,
                                                            @Param("jobTitle") String jobTitle, Pageable pageable);

    Page<Teachers> findAllByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    Page<Teachers> findAllByFirstName(String firstName, Pageable pageable);

    Page<Teachers> findAllByLastName(String lastName, Pageable pageable);

    Page<Teachers> findAllByJobTitle(String jobTitle, Pageable pageable);
}
