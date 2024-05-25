package com.hamishebahar.security.panel.news_events.service;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.NewsEventsDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.commonts.utils.MediaUtils;
import com.hamishebahar.security.commonts.utils.StringUtils;
import com.hamishebahar.security.commonts.utils.VerifyObjectUtils;
import com.hamishebahar.security.panel.media.service.MediaStorageService;
import com.hamishebahar.security.panel.news_events.entity.Events;
import com.hamishebahar.security.panel.news_events.repository.NewsEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class NewsEventsService {
    private final NewsEventsRepository newsEventsRepository;
    private final MediaUtils mediaUtils;

    @Autowired
    public NewsEventsService(NewsEventsRepository newsEventsRepository, MediaStorageService mediaStorageService, MediaUtils mediaUtils) {
        this.newsEventsRepository = newsEventsRepository;
        this.mediaUtils = mediaUtils;
    }

    public ResultsServiceDto insertNews(NewsEventsDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getTitle() == null || dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (dto.getMedias() != null && !dto.getMedias().isEmpty()) {
            dto.setMedias(mediaUtils.findMedia(dto.getMedias()));
        }
        try {
            NewsEventsDto newsEventsDto = newsEventsRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(newsEventsDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editeNews(NewsEventsDto dto, Long id) throws HamisheBaharException {
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
        if (dto.getTitle() == null ||
                dto.getTitle().isEmpty() ||
                dto.getText() == null ||
                dto.getText().isEmpty() ||
                !dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        NewsEventsDto vo = findOneByid(id);
        NewsEventsDto newsEventsDto = null;
        if (vo != null) {
            if (dto.getMedias() != null && !dto.getMedias().isEmpty()) {
                dto.setMedias(mediaUtils.findMedia(dto.getMedias()));
            }
            try {
                dto = dto.updaterFields(vo);
                newsEventsDto = newsEventsRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(newsEventsDto).build();
    }

    public ResultsServiceDto deleteNews(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        NewsEventsDto dto = findOneByid(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                newsEventsRepository.save(dto.convertToEntity());
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

    public ResultsServiceDto findNews(Long id, String startTime, String endTime, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        try {
            if (id != null) {
                if (!isExists(id)) {
                    throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                            BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
                }
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(findOneByid(id))
                        .Status(HttpStatus.OK)
                        .build();
            } else {
                if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
                    resultsServiceDto = findALL(startTime, endTime, pageable);
                } else if (StringUtils.hasText(startTime)) {
                    resultsServiceDto = findALL(startTime, null, pageable);
                } else { //all of
                    resultsServiceDto = findALL(pageable);
                }
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public NewsEventsDto findOneByid(Long id) throws HamisheBaharException {
        try {
            NewsEventsDto NewsEventsDto = null;
            if (id != null) {
                NewsEventsDto = newsEventsRepository.getOne(id).convertToDto();
            }
            return NewsEventsDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            newsEventsRepository.findAll(pageable)
                                    .map(Events::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(String startTime, String endTime, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        try {
            if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(newsEventsRepository.findALL(startTime, endTime, pageable)
                                .map(Events::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(startTime)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(newsEventsRepository.findALL(startTime, pageable)
                                .map(Events::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.newsEventsRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}
