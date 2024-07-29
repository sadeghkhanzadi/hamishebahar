package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.LinksDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.Links.service.LinksService;
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
public class LinksController {
    private final LinksService linksService;

    @Autowired
    public LinksController(LinksService linksService) {
        this.linksService = linksService;
    }

    @PostMapping(LINKS_INSERT)
    public ResponseEntity<ResultsServiceDto> insertIcons(@RequestBody LinksDto dto,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = linksService.insertIcons(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite
    @PutMapping(LINKS_UPDATE)
    public ResponseEntity<ResultsServiceDto> editIcons(@RequestBody LinksDto dto,
                                                       @PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = linksService.editIcons(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(LINKS_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteIcons(@PathVariable(value = "id", required = true) Long id,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = linksService.deleteIcons(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ALL
    @GetMapping(LINKS_FIND_VIEW)
    public ResponseEntity<ResultsServiceDto> findAllIconsPermitAll(@PageableDefault Pageable pageable,
                                                                   HttpServletResponse response,
                                                                   HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = linksService.findIcons(pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(LINKS_FIND_ONE_VIEW)
    public ResponseEntity<ResultsServiceDto> findIconPermitAll(@PathVariable(value = "id", required = false) Long id,
                                                               HttpServletResponse response,
                                                               HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = linksService.findIcons(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
