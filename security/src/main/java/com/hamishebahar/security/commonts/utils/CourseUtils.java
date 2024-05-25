package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.Dto.CoursesDto;
import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseUtils {
    private final CourseService courseService;

    @Autowired
    public CourseUtils(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CoursesDto> findMedia(List<CoursesDto> course) throws HamisheBaharException {
        return courseService.findAll(
                course.stream().map(CoursesDto::getId).collect(Collectors.toList())
        );
    }
}
