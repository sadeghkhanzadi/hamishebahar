package com.hamishebahar.security.panel.Links.service;

import com.hamishebahar.security.commonts.Dto.LinksDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.Icons.service.IconsService;
import com.hamishebahar.security.panel.Links.entity.Links;
import com.hamishebahar.security.panel.Links.repository.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinksService {
    private final LinksRepository linksRepository;
    private final IconsService iconsService;

    @Autowired
    public LinksService(LinksRepository linksRepository, IconsService iconsService) {
        this.linksRepository = linksRepository;
        this.iconsService = iconsService;
    }

    public ResultsServiceDto insertLinks(LinksDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getName() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (dto.getIcon() != null && dto.getIcon().getId() != null && iconsService.isExists(dto.getIcon().getId())){
            dto.setIcon(iconsService.getOne(dto.getIcon().getId()));
        }
        try {
            LinksDto linksDto = linksRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(linksDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editLinks(LinksDto dto, Long id) throws HamisheBaharException {
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getName() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        if (dto.getIcon() != null && dto.getIcon().getId() != null && iconsService.isExists(dto.getIcon().getId())){
            dto.setIcon(iconsService.getOne(dto.getIcon().getId()));
        }
        LinksDto vo = getOne(id);
        LinksDto linksDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getIcon() == null || dto.getName() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        BundleManager.wrapKey("error.parameter.is.null"));
            }
            try {
                dto = dto.updaterFields(vo);
                linksDto = linksRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(linksDto).build();
    }

    public ResultsServiceDto deleteLinks(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        LinksDto dto = getOne(id);
        if (dto != null) {
            try {
                linksRepository.deleteById(dto.getId());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id + " physical deleted.")
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findLinks(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            linksRepository.findAll(pageable)
                                    .map(Links::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findLinks(Long id) throws HamisheBaharException {
        try {
            if (id == null){
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
            }
            LinksDto linksDto;
            linksDto = getOne(id);
            return new ResultsServiceDto.Builder().Result(linksDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public LinksDto getOne(Long id) throws HamisheBaharException {
        try {
            Optional<Links> links = linksRepository.findById(id);
            if (links.isPresent()){
                return links.get().convertToDto();
            } else {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.linksRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}
