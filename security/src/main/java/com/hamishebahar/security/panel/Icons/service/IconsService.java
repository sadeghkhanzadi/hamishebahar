package com.hamishebahar.security.panel.Icons.service;

import com.hamishebahar.security.commonts.Dto.IconsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.hamishebahar.security.panel.Icons.entity.Icons;
import com.hamishebahar.security.panel.Icons.repository.IconsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IconsService {
    private final IconsRepository iconsRepository;
    private final ConfigProperties messageBundle;

    @Autowired
    public IconsService(IconsRepository iconsRepository, ConfigProperties messageBundle) {
        this.iconsRepository = iconsRepository;
        this.messageBundle = messageBundle;
    }


    public ResultsServiceDto insertIcons(IconsDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (dto.getIcon() == null || dto.getName() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        try {
            IconsDto iconsDto = iconsRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(iconsDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto editIcons(IconsDto dto, Long id) throws HamisheBaharException {
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
        IconsDto vo = getOne(id);
        IconsDto iconsDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getIcon() == null || dto.getName() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.is.null",null));
            }
            try {
                dto = dto.updaterFields(vo);
                iconsDto = iconsRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(iconsDto).build();
    }

    public ResultsServiceDto deleteIcons(Long id) throws HamisheBaharException {
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
        IconsDto dto = getOne(id);
        if (dto != null) {
            try {
                iconsRepository.deleteById(dto.getId());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id + " physical deleted.")
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findIcons(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            iconsRepository.findAll(pageable)
                                    .map(Icons::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto findIcons(Long id) throws HamisheBaharException {
        try {
            if (id == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
            }
            IconsDto iconsDto = new IconsDto();
            iconsDto = getOne(id);
            return new ResultsServiceDto.Builder().Result(iconsDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public IconsDto getOne(Long id) throws HamisheBaharException {
        try {
            Optional<Icons> icon = iconsRepository.findById(id);
            if (icon.isPresent()) {
                return icon.get().convertToDto();
            } else {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.iconsRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                messageBundle.getArgumentValue("error.parameter.is.null",null));
    }

}
