package com.hamishebahar.security.panel.aboutUs.service;

import com.hamishebahar.security.commonts.Dto.AboutUsPlansDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.hamishebahar.security.panel.aboutUs.entity.AboutUsPlans;
import com.hamishebahar.security.panel.aboutUs.repository.AboutUsPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AboutUsPlansService {
    private final AboutUsPlansRepository aboutUsPlansRepository;
    private final ConfigProperties messageBundle;
    @Autowired
    public AboutUsPlansService(AboutUsPlansRepository aboutUsPlansRepository, ConfigProperties messageBundle) {
        this.aboutUsPlansRepository = aboutUsPlansRepository;
        this.messageBundle = messageBundle;
    }

    public ResultsServiceDto insertPlans(AboutUsPlansDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        try {
            AboutUsPlansDto aboutUsPlansDto = aboutUsPlansRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(aboutUsPlansDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto editPlans(AboutUsPlansDto dto, Long id) throws HamisheBaharException {
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
        AboutUsPlansDto vo = findOneById(id);
        AboutUsPlansDto aboutUsPlansDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getText() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.is.null",null));
            }
            try {
                dto = dto.updaterFields(vo);
                aboutUsPlansDto = aboutUsPlansRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(aboutUsPlansDto).build();
    }

    public ResultsServiceDto deletePlans(Long id) throws HamisheBaharException {
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
        AboutUsPlansDto dto = findOneById(id);
        if (dto != null) {
            try {
                aboutUsPlansRepository.deleteById(dto.getId());
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

    public ResultsServiceDto findOne(Long id) throws HamisheBaharException {
        try {
            AboutUsPlansDto aboutUsPlansDto = aboutUsPlansRepository.getById(id).convertToDto();
            return new ResultsServiceDto.Builder().Result(aboutUsPlansDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public AboutUsPlansDto findOneById(Long id) throws HamisheBaharException {
        try {
            AboutUsPlansDto aboutUsPlansDto = aboutUsPlansRepository.getById(id).convertToDto();
            return aboutUsPlansDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public AboutUsPlansDto getOne() throws HamisheBaharException {
        try {
            AboutUsPlansDto aboutUsPlansDto = null;
            List<AboutUsPlans> aboutUsPlans = aboutUsPlansRepository.findAll();
            if (!aboutUsPlans.isEmpty()){
                aboutUsPlans.get(0).convertToDto();
            }
            return aboutUsPlansDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.aboutUsPlansRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                messageBundle.getArgumentValue("error.parameter.is.null",null));
    }

    public Boolean isExists() throws HamisheBaharException {
        try {
            return (this.aboutUsPlansRepository.count() > 0);
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto findAll() throws HamisheBaharException {
        try {
            List<AboutUsPlansDto> aboutUsPlansDto = aboutUsPlansRepository.findAll()
                    .stream()
                    .map(AboutUsPlans::convertToDto)
                    .collect(Collectors.toList());
            return new ResultsServiceDto.Builder().Result(aboutUsPlansDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }
}
