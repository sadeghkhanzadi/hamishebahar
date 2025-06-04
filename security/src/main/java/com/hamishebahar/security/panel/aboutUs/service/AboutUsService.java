package com.hamishebahar.security.panel.aboutUs.service;

import com.hamishebahar.security.commonts.Dto.AboutUsDto;
import com.hamishebahar.security.commonts.Dto.AboutUsPlansDto;
import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.hamishebahar.security.panel.aboutUs.entity.AboutUs;
import com.hamishebahar.security.panel.aboutUs.repository.AboutUsRepository;
import com.hamishebahar.security.panel.media.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AboutUsService {
    private final AboutUsRepository aboutUsRepository;
    private final AboutUsPlansService aboutUsPlansService;
    private final MediaStorageService mediaStorageService;
    private final ConfigProperties messageBundle;

    @Autowired
    public AboutUsService(AboutUsRepository aboutUsRepository, AboutUsPlansService aboutUsPlansService, MediaStorageService mediaStorageService, ConfigProperties messageBundle) {
        this.aboutUsRepository = aboutUsRepository;
        this.aboutUsPlansService = aboutUsPlansService;
        this.mediaStorageService = mediaStorageService;
        this.messageBundle = messageBundle;
    }

    public ResultsServiceDto insertAboutUs(AboutUsDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        if (isExists()) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST, "در حال حاضر یک عدد از این موجودیت موجود میباشد لطفا در صورت امکان آن را ویرایش کنید و یا ابتدا آن را حذف و سپس مجدد تلاش فرمایید");
        }
        try {
            if (dto.getAboutUsPlans() != null && !dto.getAboutUsPlans().isEmpty()) {
                List<AboutUsPlansDto> aboutUsPlansDtos = new ArrayList<>();
                for (AboutUsPlansDto plan : dto.getAboutUsPlans()) {
                    aboutUsPlansDtos.add(aboutUsPlansService.findOneById(plan.getId()));
                }
                dto.getAboutUsPlans().clear();
                dto.setAboutUsPlans(aboutUsPlansDtos);
            }
            if (dto.getMedias() != null && !dto.getMedias().isEmpty()) {
                List<MediasDto> mediasDtos = new ArrayList<>();
                for (MediasDto media : dto.getMedias()) {
                    mediasDtos.add(mediaStorageService.findOneById(media.getId()));
                }
                dto.getMedias().clear();
                dto.setMedias(mediasDtos);
            }
            AboutUsDto aboutUsDto = aboutUsRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(aboutUsDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto editAboutUs(AboutUsDto dto, Long id) throws HamisheBaharException {
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
        AboutUsDto vo = getOne();
        AboutUsDto aboutUsDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getText() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.is.null",null));
            }
            try {
                if (dto.getAboutUsPlans() != null && !dto.getAboutUsPlans().isEmpty()) {
                    List<AboutUsPlansDto> aboutUsPlansDtos = new ArrayList<>();
                    for (AboutUsPlansDto plan : dto.getAboutUsPlans()) {
                        aboutUsPlansDtos.add(aboutUsPlansService.findOneById(plan.getId()));
                    }
                    dto.getAboutUsPlans().clear();
                    dto.setAboutUsPlans(aboutUsPlansDtos);
                }
                if (dto.getMedias() != null && !dto.getMedias().isEmpty()) {
                    List<MediasDto> mediasDtos = new ArrayList<>();
                    for (MediasDto media : dto.getMedias()) {
                        mediasDtos.add(mediaStorageService.findOneById(media.getId()));
                    }
                    dto.getMedias().clear();
                    dto.setMedias(mediasDtos);
                }

                dto = dto.updaterFields(vo);
                aboutUsDto = aboutUsRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(aboutUsDto).build();
    }

    public ResultsServiceDto deleteAboutUs(Long id) throws HamisheBaharException {
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
        AboutUsDto dto = getOne();
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                aboutUsRepository.save(dto.convertToEntity());
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

    public ResultsServiceDto findOne() throws HamisheBaharException {
        try {
            AboutUsDto aboutUsDto = null;
            List<AboutUs> aboutUs = aboutUsRepository.findAll();
            if (!aboutUs.isEmpty()) {
                aboutUsDto = aboutUs.get(0).convertToDto();
            }
            return new ResultsServiceDto.Builder().Result(aboutUsDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public AboutUsDto getOne() throws HamisheBaharException {
        try {
            AboutUsDto aboutUsDto = null;
            List<AboutUs> aboutUs = aboutUsRepository.findAll();
            if (!aboutUs.isEmpty()) {
                aboutUsDto = aboutUs.get(0).convertToDto();
            }
            return aboutUsDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.aboutUsRepository.existsById(id);
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
            return (this.aboutUsRepository.count() > 0);
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }
}
