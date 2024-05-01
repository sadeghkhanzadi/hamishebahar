package com.hamishebahar.controllers;

import com.commonts.Dto.MediasDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.Enums.MediaStates;
import com.commonts.exeption.HamisheBaharException;
import com.hamishebahar.panel.media.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.commonts.Constans.UriConstants.*;
import static com.commonts.Constans.UriConstants.MEDIA_FIND_WITH_FILTER;

@RestController
public class MediaController {

    private final MediaStorageService mediaStorageService;

    @Autowired
    public MediaController(MediaStorageService mediaStorageService) {
        this.mediaStorageService = mediaStorageService;
    }

    @PostMapping(MEDIA_INSERT)
    public ResponseEntity<ResultsServiceDto> insertFile(@RequestParam("file") MediasDto dto) throws HamisheBaharException {
        ResultsServiceDto resultsVO = mediaStorageService.insertFile(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }


    //edite news
    @PutMapping(MEDIA_UPDATE)
    public ResponseEntity<ResultsServiceDto> editMedia(@RequestBody MediasDto dto,
                                                       @PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.mediaStorageService.editMedia(dto,id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
    //delete
    @DeleteMapping(MEDIA_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteMedia(@PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.mediaStorageService.deleteMedia(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
    //FIND With Filters
    @GetMapping(MEDIA_FIND_WITH_FILTER)
    public ResponseEntity<ResultsServiceDto> findMedia(@RequestParam(value = "id", required = false) Long id,
                                                       @RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "states", required = false) MediaStates states,
                                                       @PageableDefault Pageable pageable,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = this.mediaStorageService.findMedia(id,name,states,pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
