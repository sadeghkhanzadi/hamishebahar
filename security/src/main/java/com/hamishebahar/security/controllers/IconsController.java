package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.IconsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.Icons.service.IconsService;
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
public class IconsController {
    private final IconsService iconsService;

    @Autowired
    public IconsController(IconsService iconsService) {
        this.iconsService = iconsService;
    }

    @PostMapping(ICON_INSERT)
    public ResponseEntity<ResultsServiceDto> insertIcons(@RequestBody IconsDto dto,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.insertIcons(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edite
    @PutMapping(ICON_UPDATE)
    public ResponseEntity<ResultsServiceDto> editIcons(@RequestBody IconsDto dto,
                                                       @PathVariable(value = "id", required = true) Long id,
                                                       HttpServletResponse response,
                                                       HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.editIcons(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(ICON_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteIcons(@PathVariable(value = "id", required = true) Long id,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.deleteIcons(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ALL
    @GetMapping(ICON_FIND)
    public ResponseEntity<ResultsServiceDto> findAllIcons(@PageableDefault Pageable pageable,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.findIcons(pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ICON_FIND_ONE)
    public ResponseEntity<ResultsServiceDto> findIcon(@PathVariable(value = "id", required = false) Long id,
                                                      HttpServletResponse response,
                                                      HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.findIcons(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(ICON_FIND_ONE_VIEW)
    public ResponseEntity<ResultsServiceDto> findIconPermitAll(@PathVariable(value = "id", required = false) Long id,
                                                               HttpServletResponse response,
                                                               HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = iconsService.findIcons(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
