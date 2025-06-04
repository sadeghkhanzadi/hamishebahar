package com.hamishebahar.security.panel.category.service;

import com.hamishebahar.security.commonts.Dto.CategoryDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.hamishebahar.security.panel.category.entity.CourseCategory;
import com.hamishebahar.security.panel.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ConfigProperties messageBundle;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ConfigProperties messageBundle) {
        this.categoryRepository = categoryRepository;
        this.messageBundle = messageBundle;
    }

    public ResultsServiceDto insertCategory(CategoryDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (dto.getName() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null", null));
        }
        try {
            CategoryDto categoryDto = categoryRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(categoryDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto editCategory(CategoryDto dto, Long id) throws HamisheBaharException {
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.entity.is.not.exists", String.valueOf(id)));
        }
        CategoryDto vo = findOneById(id);
        CategoryDto categoryDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getName() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.is.null",null));
            }
            try {
                dto = dto.updaterFields(vo);
                categoryDto = categoryRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(categoryDto).build();
    }

    public ResultsServiceDto deleteCategory(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.entity.is.not.exists", String.valueOf(id)));
        }
        CategoryDto dto = findOneById(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                categoryRepository.save(dto.convertToEntity());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id)
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findOne(Long id) throws HamisheBaharException {
        try {
            CategoryDto categoryDto = null;
            if (id != null) {
                categoryDto = categoryRepository.getOne(id).convertToDto();
            }
            return new ResultsServiceDto.Builder().Result(categoryDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.categoryRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                messageBundle.getArgumentValue("error.parameter.is.null",null));
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            categoryRepository.findAll(pageable)
                                    .map(CourseCategory::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto findALLByIsActiveAndIsDeleted(Pageable pageable) throws HamisheBaharException {
        try {
            Page<CourseCategory> categories = categoryRepository.findALLByIsActiveAndIsDeleted(Boolean.TRUE, Boolean.FALSE, pageable);

            return new ResultsServiceDto.Builder().Result(
                            categories.map(CourseCategory::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public CategoryDto findOneById(Long id) throws HamisheBaharException {
        try {
            CategoryDto categoryDto = null;
            if (id != null) {
                categoryDto = categoryRepository.getOne(id).convertToDto();
            }
            return categoryDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }
}
