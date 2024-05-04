package com.hamishebahar.panel.courses.service;

import com.commonts.Dto.CoursesDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.Dto.StudentDto;
import com.commonts.bundel.BundleManager;
import com.commonts.exeption.HamisheBaharException;
import com.commonts.utils.StringUtils;
import com.hamishebahar.panel.courses.entity.Courses;
import com.hamishebahar.panel.courses.repository.CourseRepository;
import com.hamishebahar.panel.students.entity.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.commonts.utils.VerifyObjectUtils.isNewCourse;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResultsServiceDto insertCourse(CoursesDto dto) throws HamisheBaharException {
        if (!isNewCourse(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getTitle() == null || dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            CoursesDto coursesDto = courseRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Result(coursesDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editCourse(CoursesDto dto, Long id) throws HamisheBaharException {
        if (isNewCourse(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!StringUtils.hasText(String.valueOf(id))) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        if (dto.getTitle() == null || dto.getText() == null || !dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        CoursesDto vo = findOneById(id);
        CoursesDto coursesDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                coursesDto = courseRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Result(coursesDto).build();
    }

    public ResultsServiceDto deleteCourse(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (!StringUtils.hasText(String.valueOf(id))) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        CoursesDto dto = findOneById(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                courseRepository.save(dto.convertToEntity());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id)
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findCourse(String courseCode,
                                        String teacherId,
                                        String teacherFirstName,
                                        String teacherLastName,
                                        Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        try {
            if (StringUtils.hasText(courseCode) && StringUtils.hasText(teacherId) &&
                    StringUtils.hasText(teacherFirstName) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherIdAndTeacherFirstNameAndTeacherLastName(
                                courseCode, teacherId , teacherFirstName , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherId) && StringUtils.hasText(teacherFirstName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherIdAndTeacherFirstName(
                                courseCode, teacherId , teacherFirstName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherId) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherIdAndTeacherLastName(
                                courseCode, teacherId , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherFirstName) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherFirstNameAndTeacherLastName(
                                courseCode, teacherFirstName , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherId) && StringUtils.hasText(teacherFirstName) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherIdAndTeacherFirstNameAndTeacherLastName(
                                teacherId , teacherFirstName , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherId)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherId(
                                courseCode, teacherId , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherFirstName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherFirstName(
                                courseCode, teacherFirstName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCodeAndTeacherLastName(
                                courseCode, teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherId) && StringUtils.hasText(teacherFirstName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherIdAndTeacherFirstName(
                                teacherId , teacherFirstName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherId) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherIdeAndTeacherLastName(
                                teacherId , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherFirstName) && StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherFirstNameAndTeacherLastName(
                                teacherFirstName , teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(courseCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByCourseCode(courseCode, pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherId)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherId(teacherId , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherFirstName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherFirstName(teacherFirstName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if(StringUtils.hasText(teacherLastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(courseRepository.findAllByTeacherLastName(teacherLastName , pageable)
                                .map(Courses::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            }else { //all of
                resultsServiceDto = findALL(pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findOne(Long id) throws HamisheBaharException {
        try {
            CoursesDto coursesDto = null;
            if (!StringUtils.isNullOrEmpty(String.valueOf(id))) {
                coursesDto = courseRepository.getById(id).convertToDto();
            }
            return new ResultsServiceDto.Builder().Result(coursesDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.courseRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            courseRepository.findAll(pageable)
                                    .map(Courses::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public CoursesDto findOneById(Long id) throws HamisheBaharException {
        try {
            CoursesDto coursesDto = null;
            if (!StringUtils.isNullOrEmpty(String.valueOf(id))) {
                coursesDto = courseRepository.getById(id).convertToDto();
            }
            return coursesDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }
}
