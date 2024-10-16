package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.CoursesDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //new Course
    @PostMapping(COURSE_INSERT)
    public ResponseEntity<ResultsServiceDto> insertCourse(@RequestBody CoursesDto dto,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.insertCourse(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite Course
    @PutMapping(COURSE_UPDATE)
    public ResponseEntity<ResultsServiceDto> editCourse(@RequestBody CoursesDto dto,
                                                        @PathVariable(value = "id", required = true) Long id,
                                                        HttpServletResponse response,
                                                        HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.editCourse(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(COURSE_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteCourse(@PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.deleteCourse(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(COURSE_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findCourse(@RequestParam(value = "courseCode", required = false) String courseCode,
                                                        @RequestParam(value = "teacher", required = false) String teacherId,
                                                        @RequestParam(value = "teacherFirstName", required = false) String teacherFirstName,
                                                        @RequestParam(value = "teacherLastName", required = false) String teacherLastName,
                                                        @PageableDefault Pageable pageable,
                                                        HttpServletResponse response,
                                                        HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.findCourse(
                courseCode,
                teacherId,
                teacherFirstName,
                teacherLastName,
                pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(COURSE_FIND_VIEW_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findCoursePermitAll(@RequestParam(value = "courseCode", required = false) String courseCode,
                                                                 @RequestParam(value = "teacher", required = false) String teacherId,
                                                                 @RequestParam(value = "teacherFirstName", required = false) String teacherFirstName,
                                                                 @RequestParam(value = "teacherLastName", required = false) String teacherLastName,
                                                                 @PageableDefault Pageable pageable,
                                                                 HttpServletResponse response,
                                                                 HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.findCourse(
                courseCode,
                teacherId,
                teacherFirstName,
                teacherLastName,
                pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(COURSE_FIND)
    public ResponseEntity<ResultsServiceDto> findCourse(@PathVariable(value = "id", required = false) Long id,
                                                        HttpServletResponse response,
                                                        HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.findOne(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(COURSE_VIEW_FIND)
    public ResponseEntity<ResultsServiceDto> findCoursePermitAll(@PathVariable(value = "id", required = false) Long id,
                                                                 HttpServletResponse response,
                                                                 HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.findOne(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND Categories Usage
    @GetMapping(COURSE_FIND_CATEGORIES_USAGE_VIEW_FIND)
    public ResponseEntity<ResultsServiceDto> findCourseCategoriesUsagePermitAll(@RequestParam(value = "id", required = false) Long id,
                                                                                HttpServletResponse response,
                                                                                HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = courseService.findCourseCategoriesUsage(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
