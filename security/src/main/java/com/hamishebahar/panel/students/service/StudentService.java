package com.hamishebahar.panel.students.service;

import com.commonts.Dto.StudentDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.bundel.BundleManager;
import com.commonts.exeption.HamisheBaharException;
import com.commonts.utils.StringUtils;
import com.hamishebahar.panel.students.entity.Students;
import com.hamishebahar.panel.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.commonts.utils.StringUtils.isDualFields;
import static com.commonts.utils.VerifyObjectUtils.isNewStudent;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResultsServiceDto insertStudent(StudentDto dto) throws HamisheBaharException {
        if (!isNewStudent(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getNationalCode() == null || dto.getPhoneNumber() == null ||
                dto.getFirst_name() == null || dto.getLast_name() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            StudentDto StudentDto = studentRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Result(StudentDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editeStudent(StudentDto dto, Long id) throws HamisheBaharException {
        if (isNewStudent(dto)) {
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
        if (dto.getNationalCode() == null || dto.getPhoneNumber() == null ||
                dto.getFirst_name() == null || dto.getLast_name() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        StudentDto vo = findOneByid(id);
        StudentDto StudentDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                StudentDto = studentRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Result(StudentDto).build();
    }

    public ResultsServiceDto deleteStudent(Long id) throws HamisheBaharException {
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
        StudentDto dto = findOneByid(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                studentRepository.save(dto.convertToEntity());
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

    public ResultsServiceDto findStudent(Long id, String nationalCode,
                                         String phoneNumber, String studentAge,
                                         String studentCode, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            isDualFields(nationalCode, phoneNumber);
            isDualFields(nationalCode, studentAge);
            isDualFields(studentAge, phoneNumber);
            isDualFields(studentAge, studentCode);
            isDualFields(phoneNumber, studentCode);
            if (StringUtils.hasText(String.valueOf(id))) {
                if (!isExists(id)) {
                    throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                            BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
                }
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(findOneByid(id))
                        .Status(HttpStatus.OK)
                        .build();
            } else {
                resultsServiceDto = findALL(nationalCode, phoneNumber, studentAge, studentCode, pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public StudentDto findOneByid(Long id) throws HamisheBaharException {
        try {
            StudentDto StudentDto = null;
            if (!StringUtils.isNullOrEmpty(String.valueOf(id))) {
                StudentDto = studentRepository.getById(id).convertToDto();
            }
            return StudentDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            studentRepository.findAll(pageable)
                                    .map(Students::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(String nationalCode,
                                     String phoneNumber, String studentAge,
                                     String studentCode, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        try {
            if (StringUtils.hasText(nationalCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(studentRepository.findAllByNationalCode(nationalCode, pageable)
                                .map(Students::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(phoneNumber)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(studentRepository.findAllByPhoneNumber(phoneNumber, pageable)
                                .map(Students::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(studentAge)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(studentRepository.findAllByStudentAge(studentAge, pageable)
                                .map(Students::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(studentCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(studentRepository.findAllByStudentCode(studentCode, pageable)
                                .map(Students::convertToDto))
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
                return this.studentRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}
