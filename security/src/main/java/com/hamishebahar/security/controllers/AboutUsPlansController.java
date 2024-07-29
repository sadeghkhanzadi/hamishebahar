package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.AboutUsPlansDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.aboutUs.service.AboutUsPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
@CrossOrigin
public class AboutUsPlansController {
    private final AboutUsPlansService aboutUsPlansService;

    @Autowired
    public AboutUsPlansController(AboutUsPlansService aboutUsPlansService) {
        this.aboutUsPlansService = aboutUsPlansService;
    }

    @PostMapping(ABOUT_US_PLANS_INSERT)
    public ResponseEntity<ResultsServiceDto> insertAboutUsPlans(@RequestBody AboutUsPlansDto dto,
                                                                HttpServletResponse response,
                                                                HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsPlansService.insertPlans(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite AboutUs
    @PutMapping(ABOUT_US_PLANS_UPDATE)
    public ResponseEntity<ResultsServiceDto> editAboutUsPlans(@RequestBody AboutUsPlansDto dto,
                                                              @PathVariable(value = "id", required = true) Long id,
                                                              HttpServletResponse response,
                                                              HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsPlansService.editPlans(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(ABOUT_US_PLANS_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteAboutUsPlans(@PathVariable(value = "id", required = true) Long id,
                                                                HttpServletResponse response,
                                                                HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsPlansService.deletePlans(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ABOUT_US_ALL_PLANS_VIEW_FIND)
    public ResponseEntity<ResultsServiceDto> findAboutUsPlans(HttpServletResponse response,
                                                              HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsPlansService.findAll();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ABOUT_US_PLANS_VIEW_FIND)
    public ResponseEntity<ResultsServiceDto> findAboutUsPlansPermitAll(@PathVariable(value = "id", required = true) Long id,
                                                                       HttpServletResponse response,
                                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = aboutUsPlansService.findOne(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
