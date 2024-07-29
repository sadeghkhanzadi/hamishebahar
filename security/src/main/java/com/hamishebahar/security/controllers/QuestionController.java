package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.QuestionDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
@CrossOrigin
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(QUESTION_INSERT)
    public ResponseEntity<ResultsServiceDto> insertQuestion(@RequestBody QuestionDto dto,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = questionService.insertQuestion(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite
    @PutMapping(QUESTION_UPDATE)
    public ResponseEntity<ResultsServiceDto> editQuestion(@RequestBody QuestionDto dto,
                                                          @PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = questionService.editQuestion(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(QUESTION_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteQuestion(@PathVariable(value = "id", required = true) Long id,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = questionService.deleteQuestion(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND BY Filter
    @GetMapping(QUESTION_FIND)
    public ResponseEntity<ResultsServiceDto> findQuestion(@RequestParam(value = "id", required = false) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = questionService.findByFilter(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
