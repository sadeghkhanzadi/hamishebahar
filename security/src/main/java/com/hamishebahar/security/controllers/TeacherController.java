package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.TeacherDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.teachers.service.TeacherService;
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
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //new Teacher
    @PostMapping(TEACHER_INSERT)
    public ResponseEntity<ResultsServiceDto> insertTeacher(@RequestBody TeacherDto dto,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.teacherService.insertTeacher(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edit Teacher
    @PutMapping(TEACHER_UPDATE)
    public ResponseEntity<ResultsServiceDto> editTeacher(@RequestBody TeacherDto dto,
                                                         @PathVariable(value = "id", required = true) Long id,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.teacherService.editTeacher(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(TEACHER_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteTeacher(@PathVariable(value = "id", required = true) Long id,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.teacherService.deleteTeacher(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(TEACHER_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findTeacher(@RequestParam(value = "id", required = false) Long id,
                                                         @RequestParam(value = "firstName", required = false) String firstName,
                                                         @RequestParam(value = "lastName", required = false) String lastName,
                                                         @RequestParam(value = "jobTitle", required = false) String jobTitle,
                                                         @PageableDefault Pageable pageable,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.teacherService.findTeacher(id, firstName, lastName, jobTitle, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(TEACHER_FIND_VIEW_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findTeacherPermitAll(@RequestParam(value = "id", required = false) Long id,
                                                                  @RequestParam(value = "firstName", required = false) String firstName,
                                                                  @RequestParam(value = "lastName", required = false) String lastName,
                                                                  @RequestParam(value = "jobTitle", required = false) String jobTitle,
                                                                  @PageableDefault Pageable pageable,
                                                                  HttpServletResponse response,
                                                                  HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.teacherService.findTeacher(id, firstName, lastName, jobTitle, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
