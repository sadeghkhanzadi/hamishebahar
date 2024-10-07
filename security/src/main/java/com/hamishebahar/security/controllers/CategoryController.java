package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.CategoryDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.category.service.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(CATEGORY_INSERT)
    public ResponseEntity<ResultsServiceDto> insertCategory(@RequestBody CategoryDto dto,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.insertCategory(dto);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //edit Category
    @PutMapping(CATEGORY_UPDATE)
    public ResponseEntity<ResultsServiceDto> editeCategory(@RequestBody CategoryDto dto,
                                                           @PathVariable(value = "id", required = true) Long id,
                                                           HttpServletResponse response,
                                                           HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.editCategory(dto, id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //delete
    @DeleteMapping(CATEGORY_DELETE_WITH_ID)
    public ResponseEntity<ResultsServiceDto> deleteCategory(@PathVariable(value = "id", required = true) Long id,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.deleteCategory(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(CATEGORY_FIND)
    public ResponseEntity<ResultsServiceDto> findCategory(@PathVariable(value = "id", required = false) Long id,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.findOne(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND ONE
    @GetMapping(CATEGORY_FIND_VIEW)
    public ResponseEntity<ResultsServiceDto> findCategoryPermitAll(@PathVariable(value = "id", required = false) Long id,
                                                                   HttpServletResponse response,
                                                                   HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.findOne(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND All
    @GetMapping(CATEGORY_FIND_ALL)
    public ResponseEntity<ResultsServiceDto> findCategory(@PageableDefault Pageable pageable,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.findALLByIsActiveAndIsDeleted(pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //FIND All
    @GetMapping(CATEGORY_FIND_VIEW_ALL)
    public ResponseEntity<ResultsServiceDto> findCategoryPermitAll(@PageableDefault Pageable pageable,
                                                                   HttpServletResponse response,
                                                                   HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.findALLByIsActiveAndIsDeleted(pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
