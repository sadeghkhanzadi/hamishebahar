package com.hamishebahar.security.panel.teachers.service;

import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Dto.TeacherDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.commonts.utils.CourseUtils;
import com.hamishebahar.security.commonts.utils.StringUtils;
import com.hamishebahar.security.commonts.utils.VerifyObjectUtils;
import com.hamishebahar.security.panel.teachers.entity.Teachers;
import com.hamishebahar.security.panel.teachers.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseUtils courseUtils;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseUtils courseUtils) {
        this.teacherRepository = teacherRepository;
        this.courseUtils = courseUtils;
    }

    public ResultsServiceDto insertTeacher(TeacherDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getPhoneNumber() == null || dto.getFirstName() == null || dto.getLastName() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            TeacherDto teacherDto = teacherRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(teacherDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editTeacher(TeacherDto dto, Long id) throws HamisheBaharException {
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getFirstName() == null ||
                dto.getLastName() == null ||
                dto.getJobTitle() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        TeacherDto vo = findOneById(id);
        TeacherDto TeacherDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                TeacherDto = teacherRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(TeacherDto).build();
    }

    public ResultsServiceDto deleteTeacher(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        TeacherDto dto = findOneById(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                teacherRepository.save(dto.convertToEntity());
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

    public ResultsServiceDto findTeacher(Long id, String firstName, String lastName, String jobTitle, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            if (id != null) {
                if (!isExists(id)) {
                    throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                            BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
                }
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(findOneById(id))
                        .Status(HttpStatus.OK)
                        .build();
            } else {
                resultsServiceDto = findALL(firstName, lastName, jobTitle, pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }


    public TeacherDto findOneById(Long id) throws HamisheBaharException {
        try {
            TeacherDto teacherDto = null;
            if (id != null) {
                teacherDto = teacherRepository.getOne(id).convertToDto();
            }
            return teacherDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            teacherRepository.findAll(pageable)
                                    .map(Teachers::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(String firstName, String lastName,
                                     String jobTitle, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            if (StringUtils.hasText(firstName) && StringUtils.hasText(lastName) && StringUtils.hasText(jobTitle)){
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(teacherRepository.findAllByFirstNameAndLastNameAndJobTitle(firstName, lastName, jobTitle, pageable)
                                .map(Teachers::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(firstName) && StringUtils.hasText(lastName)){
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(teacherRepository.findAllByFirstNameAndLastName(firstName, lastName, pageable)
                                .map(Teachers::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(firstName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(teacherRepository.findAllByFirstName(firstName, pageable)
                                .map(Teachers::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(lastName)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(teacherRepository.findAllByLastName(lastName, pageable)
                                .map(Teachers::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(jobTitle)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(teacherRepository.findAllByJobTitle(jobTitle, pageable)
                                .map(Teachers::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else { //all of
                resultsServiceDto = findALL(pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.teacherRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}
