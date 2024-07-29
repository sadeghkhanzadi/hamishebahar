package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.AboutUsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.aboutUs.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
@CrossOrigin
public class AboutUsController {
    private final AboutUsService aboutUsService;

    @Autowired
    public AboutUsController(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @PostMapping(ABOUT_US_INSERT)
    public ResponseEntity<ResultsServiceDto> insertAboutUs(@RequestBody AboutUsDto dto,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsService.insertAboutUs(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite AboutUs
    @PutMapping(ABOUT_US_UPDATE)
    public ResponseEntity<ResultsServiceDto> editAboutUs(@RequestBody AboutUsDto dto,
                                                         @PathVariable(value = "id", required = true) Long id,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsService.editAboutUs(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(ABOUT_US_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteAboutUs(@PathVariable(value = "id", required = true) Long id,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsService.deleteAboutUs(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ABOUT_US_FIND)
    public ResponseEntity<ResultsServiceDto> findAboutUs(HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsService.findOne();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ABOUT_US_VIEW_FIND)
    public ResponseEntity<ResultsServiceDto> findAboutUsPermitAll(HttpServletResponse response,
                                                                  HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsService.findOne();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
