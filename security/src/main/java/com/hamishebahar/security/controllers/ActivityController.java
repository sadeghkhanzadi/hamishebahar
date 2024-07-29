package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.ActivityDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;
import static com.hamishebahar.security.commonts.Constans.UriConstants.ACTIVITY_FIND;

@RestController
@CrossOrigin
public class ActivityController {
    
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @PostMapping(ACTIVITY_INSERT)
    public ResponseEntity<ResultsServiceDto> insertActivity(@RequestBody ActivityDto dto,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = activityService.insertActivity(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite
    @PutMapping(ACTIVITY_UPDATE)
    public ResponseEntity<ResultsServiceDto> editActivity(@RequestBody ActivityDto dto,
                                                          @PathVariable(value = "id", required = true) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = activityService.editActivity(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(ACTIVITY_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteActivity(@PathVariable(value = "id", required = true) Long id,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = activityService.deleteActivity(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND BY Filter
    @GetMapping(ACTIVITY_FIND)
    public ResponseEntity<ResultsServiceDto> findActivity(@RequestParam(value = "id", required = false) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = activityService.findByFilter(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
