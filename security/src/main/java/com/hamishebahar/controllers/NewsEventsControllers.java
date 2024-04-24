package com.hamishebahar.controllers;

import com.commonts.Dto.NewsEventsDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.exeption.HamisheBaharException;
import com.hamishebahar.panel.news_events.service.NewsEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.commonts.Constans.UriConstants.*;

@RestController
public class NewsEventsControllers {
    private final NewsEventsService newsEventsService;

    @Autowired
    public NewsEventsControllers(NewsEventsService newsEventsService) {
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

    //edite news
    @PutMapping(NEWS_UPDATE)
    public ResponseEntity<ResultsServiceDto> editeNews(@RequestBody NewsEventsDto dto,
                                                       @PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.editeNews(dto,id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
    //delete
    @PutMapping(NEWS_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> editeNews(@PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.deleteNews(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
    //FIND With Filters
    @GetMapping(NEWS_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> editeNews(@RequestParam(value = "id", required = false) Long id,
                                                       @RequestParam(value = "startTime", required = false) String startTime,
                                                       @RequestParam(value = "endTime", required = false) String endTime,
                                                       @PageableDefault Pageable pageable,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.newsEventsService.findNews(id,startTime,endTime,pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
