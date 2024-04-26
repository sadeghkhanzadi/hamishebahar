package com.hamishebahar.controllers;

import com.commonts.Dto.ResultsServiceDto;
import com.commonts.Dto.StudentDto;
import com.commonts.exeption.HamisheBaharException;
import com.hamishebahar.panel.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.commonts.Constans.UriConstants.*;
import static com.commonts.Constans.UriConstants.STUDENT_FIND_WITH_FILTER;

@RestController
public class StudentsController {
    private final StudentService StudentService;

    @Autowired
    public StudentsController(StudentService StudentService) {
        this.StudentService = StudentService;
    }

    //new Student
    @PostMapping(STUDENT_INSERT)
    public ResponseEntity<ResultsServiceDto> insertStudent(@RequestBody StudentDto dto,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.StudentService.insertStudent(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite Student
    @PutMapping(STUDENT_UPDATE)
    public ResponseEntity<ResultsServiceDto> editeStudent(@RequestBody StudentDto dto,
                                                          @PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.StudentService.editeStudent(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @PutMapping(STUDENT_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> editeStudent(@PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.StudentService.deleteStudent(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(STUDENT_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> editeStudent(@RequestParam(value = "id", required = false) Long id,
                                                          @RequestParam(value = "nationalCode", required = false) String nationalCode,
                                                          @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                          @RequestParam(value = "studentAge", required = false) String studentAge,
                                                          @RequestParam(value = "studentCode", required = false) String studentCode,
                                                          @PageableDefault Pageable pageable,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.StudentService.findStudent(id, nationalCode, phoneNumber,studentAge ,studentCode, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
