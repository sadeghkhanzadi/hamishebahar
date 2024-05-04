package com.hamishebahar.panel.courses.repository;

import com.hamishebahar.panel.courses.entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Courses , Long> {

    @Query("select C from Courses C where C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByTeacherLastName(@Param("teacherLastName") String teacherLastName, Pageable pageable);

    @Query("select C from Courses C where C.teachers.firstName= :teacherFirstName")
    Page<Courses> findAllByTeacherFirstName(@Param("teacherFirstName") String teacherFirstName, Pageable pageable);

    @Query("select C from Courses C where C.teachers.teacherCode= :teacherId")
    Page<Courses> findAllByTeacherId(@Param("teacherId") String teacherId, Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode")
    Page<Courses> findAllByCourseCode(@Param("courseCode") String courseCode, Pageable pageable);

    @Query("select C from Courses C where " +
            "C.teachers.firstName= :teacherFirstName and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByTeacherFirstNameAndTeacherLastName(@Param("teacherFirstName") String teacherFirstName,
                                                              @Param("teacherLastName") String teacherLastName,
                                                              Pageable pageable);

    @Query("select C from Courses C where " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByTeacherIdeAndTeacherLastName(@Param("teacherId") String teacherId,
                                                        @Param("teacherLastName") String teacherLastName,
                                                        Pageable pageable);

    @Query("select C from Courses C where " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.firstName= :teacherFirstName")
    Page<Courses> findAllByTeacherIdAndTeacherFirstName(@Param("teacherId") String teacherId,
                                                        @Param("teacherFirstName") String teacherFirstName,
                                                        Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByCourseCodeAndTeacherLastName(@Param("courseCode") String courseCode,
                                                        @Param("teacherLastName") String teacherLastName,
                                                        Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.firstName= :teacherFirstName")
    Page<Courses> findAllByCourseCodeAndTeacherFirstName(@Param("courseCode") String courseCode,
                                                         @Param("teacherFirstName") String teacherFirstName,
                                                         Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.teacherCode= :teacherId")
    Page<Courses> findAllByCourseCodeAndTeacherId(@Param("courseCode") String courseCode,
                                                  @Param("teacherId") String teacherId,
                                                  Pageable pageable);

    @Query("select C from Courses C where " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.firstName= :teacherFirstName and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByTeacherIdAndTeacherFirstNameAndTeacherLastName(@Param("teacherId") String teacherId,
                                                                          @Param("teacherFirstName") String teacherFirstName,
                                                                          @Param("teacherLastName") String teacherLastName,
                                                                          Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.firstName= :teacherFirstName and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByCourseCodeAndTeacherFirstNameAndTeacherLastName(@Param("courseCode") String courseCode,
                                                                           @Param("teacherFirstName") String teacherFirstName,
                                                                           @Param("teacherLastName") String teacherLastName,
                                                                           Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByCourseCodeAndTeacherIdAndTeacherLastName(@Param("courseCode") String courseCode,
                                                                    @Param("teacherId") String teacherId,
                                                                    @Param("teacherLastName") String teacherLastName,
                                                                    Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.firstName= :teacherFirstName")
    Page<Courses> findAllByCourseCodeAndTeacherIdAndTeacherFirstName(@Param("courseCode") String courseCode,
                                                                     @Param("teacherId") String teacherId,
                                                                     @Param("teacherFirstName") String teacherFirstName,
                                                                     Pageable pageable);

    @Query("select C from Courses C where " +
            "C.courseCode= :courseCode and " +
            "C.teachers.teacherCode= :teacherId and " +
            "C.teachers.firstName= :teacherFirstName and " +
            "C.teachers.lastName= :teacherLastName")
    Page<Courses> findAllByCourseCodeAndTeacherIdAndTeacherFirstNameAndTeacherLastName(@Param("courseCode") String courseCode,
                                                                                       @Param("teacherId") String teacherId,
                                                                                       @Param("teacherFirstName") String teacherFirstName,
                                                                                       @Param("teacherLastName") String teacherLastName,
                                                                                       Pageable pageable);
}
