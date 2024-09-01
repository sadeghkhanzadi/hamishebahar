package com.hamishebahar.security.panel.contactUs.service;

import com.hamishebahar.security.commonts.Dto.ContactUsDto;
import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.contactUs.entity.ContactUs;
import com.hamishebahar.security.panel.contactUs.repository.ContactUsRepository;
import com.hamishebahar.security.panel.media.entity.Medias;
import com.hamishebahar.security.panel.media.repository.MediaRepository;
import com.hamishebahar.security.panel.media.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactUsService {
    private final ContactUsRepository contactUsRepository;
    private final MediaStorageService mediaStorageService;

    @Autowired
    public ContactUsService(ContactUsRepository contactUsRepository, MediaStorageService mediaStorageService) {
        this.contactUsRepository = contactUsRepository;
        this.mediaStorageService = mediaStorageService;
    }

    public ResultsServiceDto insertContactUs(ContactUsDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            List<MediasDto> medias = mediaSet(dto.getMedias());
            if (!medias.isEmpty()){
                dto.setMedias(medias);
            }
            ContactUs contactUs = contactUsActive();
            if (contactUs == null){
                ContactUsDto contactUsDto = contactUsRepository.save(dto.convertToEntity()).convertToDto();
                return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(contactUsDto).build();
            }
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    String.format("Data for this %s , exists in DB.", String.valueOf(contactUs.getId())));
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }


    private ContactUs contactUsActive(){
        return contactUsRepository.findAll()
                .stream()
                .filter(ContactUs::getIs_active)
                .collect(Collectors.toList())
                .get(0);
    }

    private List<MediasDto> mediaSet(List<MediasDto> mediasDtoList) throws HamisheBaharException {
        List<MediasDto> medias = new ArrayList<>();
        if (mediasDtoList != null && !mediasDtoList.isEmpty()){
            for (MediasDto M : mediasDtoList){
                if (this.mediaStorageService.isExists(M.getId())){
                    medias.add(this.mediaStorageService.findOneById(M.getId()));
                }
            }
        }
        return medias;
    }

    public ResultsServiceDto editContactUs(ContactUsDto dto, Long id) throws HamisheBaharException {
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        ContactUsDto vo = getOne();
        ContactUsDto contactUsDto = null;
        if (vo != null) {
            List<MediasDto> medias = mediaSet(dto.getMedias());
            if (!medias.isEmpty()){
                dto.setMedias(medias);
            }
            if (dto.getId() == null || dto.getText() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        BundleManager.wrapKey("error.parameter.is.null"));
            }
            try {
                dto = dto.updaterFields(vo);
                contactUsDto = contactUsRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(contactUsDto).build();
    }

    public ResultsServiceDto deleteContactUs(Long id) throws HamisheBaharException {
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
        ContactUsDto dto = getOne();
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                contactUsRepository.save(dto.convertToEntity());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id)
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findOne() throws HamisheBaharException {
        try {
            ContactUsDto contactUsDto = contactUsRepository.findAll().get(0).convertToDto();
            return new ResultsServiceDto.Builder().Result(contactUsDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ContactUsDto getOne() throws HamisheBaharException {
        try {
            return contactUsRepository.findAll().get(0).convertToDto();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.contactUsRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}
