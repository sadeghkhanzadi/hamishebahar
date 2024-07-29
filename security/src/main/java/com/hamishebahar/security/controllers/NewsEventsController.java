package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.NewsEventsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.news_events.service.NewsEventsService;
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
public class NewsEventsController {
    private final NewsEventsService newsEventsService;

    @Autowired
    public NewsEventsController(NewsEventsService newsEventsService) {
        this.newsEventsService = newsEventsService;
    }

    //new news
    @PostMapping(NEWS_INSERT)
    public ResponseEntity<ResultsServiceDto> insertNews(@RequestBody NewsEventsDto dto,
                                                        HttpServletResponse response,
                                                        HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.insertNews(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edit news
    @PutMapping(NEWS_UPDATE)
    public ResponseEntity<ResultsServiceDto> editNews(@RequestBody NewsEventsDto dto,
                                                      @PathVariable(value = "id", required = true) Long id,
                                                      HttpServletResponse response,
                                                      HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.editeNews(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(NEWS_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteNews(@PathVariable(value = "id", required = true) Long id,
                                                        HttpServletResponse response,
                                                        HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.deleteNews(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(NEWS_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findNews(@RequestParam(value = "id", required = false) Long id,
                                                      @RequestParam(value = "startTime", required = false) String startTime,
                                                      @RequestParam(value = "endTime", required = false) String endTime,
                                                      @PageableDefault Pageable pageable,
                                                      HttpServletResponse response,
                                                      HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.findNews(id, startTime, endTime, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND With Filters
    @GetMapping(NEWS_FIND_VIEW_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findNewsPermitAll(@RequestParam(value = "id", required = false) Long id,
                                                               @PageableDefault Pageable pageable,
                                                               HttpServletResponse response,
                                                               HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.findNews(id, null , null, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
