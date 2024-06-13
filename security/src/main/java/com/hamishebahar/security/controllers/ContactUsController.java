package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.CategoryDto;
import com.hamishebahar.security.commonts.Dto.ContactUsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.contactUs.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
public class ContactUsController {
    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping(CONTACT_US_INSERT)
    public ResponseEntity<ResultsServiceDto> insertContactUs(@RequestBody ContactUsDto dto,
                                                             HttpServletResponse response,
                                                             HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = contactUsService.insertContactUs(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite Student
    @PutMapping(CONTACT_US_UPDATE)
    public ResponseEntity<ResultsServiceDto> editContactUs(@RequestBody ContactUsDto dto,
                                                           @PathVariable(value = "id", required = true) Long id,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = contactUsService.editContactUs(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(CONTACT_US_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteContactUs(@PathVariable(value = "id", required = true) Long id,
                                                             HttpServletResponse response,
                                                             HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = contactUsService.deleteContactUs(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(CONTACT_US_FIND)
    public ResponseEntity<ResultsServiceDto> findContactUs(HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = contactUsService.findOne();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
