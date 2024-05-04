package com.hamishebahar.controllers;

import com.commonts.Dto.CategoryDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.exeption.HamisheBaharException;
import com.hamishebahar.panel.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.commonts.Constans.UriConstants.*;

@RestController
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

    //edite Student
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
    //FIND All
    @GetMapping(CATEGORY_FIND_ALL)
    public ResponseEntity<ResultsServiceDto> findCategory(@PageableDefault Pageable pageable,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = categoryService.findALL(pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
