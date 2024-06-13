package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Enums.MediaStates;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.media.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
public class MediaController {

    private final MediaStorageService mediaStorageService;

    @Autowired
    public MediaController(MediaStorageService mediaStorageService) {
        this.mediaStorageService = mediaStorageService;
    }

    @PostMapping(MEDIA_INSERT)
    public ResponseEntity<ResultsServiceDto> insertFile(@RequestParam("name") String name,
                                                        @RequestParam("states") MediaStates states,
                                                        @RequestParam("file") MultipartFile file) throws HamisheBaharException {
        MediasDto dto = new MediasDto.Builder()
                .Name(name)
                .States(states)
                .File(file)
                .build();
        ResultsServiceDto resultsVO = mediaStorageService.insertFile(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }


    //edit Media
    @PutMapping(MEDIA_UPDATE)
    public ResponseEntity<ResultsServiceDto> editMedia(@RequestParam(value = "name" , required = false) String name,
                                                       @RequestParam(value = "states" , required = false) MediaStates states,
                                                       @RequestParam(value = "file" , required = false) MultipartFile file,
                                                       @PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        MediasDto dto = new MediasDto.Builder()
                .Id(id)
                .Name(name)
                .States(states)
                .File(file)
                .build();
        ResultsServiceDto resultsVO = this.mediaStorageService.editMedia(dto, id);
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
        ResultsServiceDto resultsVO = this.mediaStorageService.findMedia(id, name, states, pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
