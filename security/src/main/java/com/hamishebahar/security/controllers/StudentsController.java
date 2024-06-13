package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Dto.StudentDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
public class StudentsController {
    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService StudentService) {
        this.studentService = StudentService;
    }

    //new Student
    @PostMapping(STUDENT_INSERT)
    public ResponseEntity<ResultsServiceDto> insertStudent(@RequestBody StudentDto dto,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.studentService.insertStudent(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edit Student
    @PutMapping(STUDENT_UPDATE)
    public ResponseEntity<ResultsServiceDto> editeStudent(@RequestBody StudentDto dto,
                                                          @PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.studentService.editeStudent(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(STUDENT_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteStudent(@PathVariable(value = "id", required = true) Long id,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.studentService.deleteStudent(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(STUDENT_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findStudent(@RequestParam(value = "id", required = false) Long id,
                                                         @RequestParam(value = "nationalCode", required = false) String nationalCode,
                                                         @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                         @RequestParam(value = "studentAge", required = false) String studentAge,
                                                         @RequestParam(value = "studentCode", required = false) String studentCode,
                                                         @PageableDefault Pageable pageable,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.studentService.findStudent(id, nationalCode, phoneNumber, studentAge, studentCode, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
