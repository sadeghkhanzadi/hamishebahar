package com.hamishebahar.panel.news_events.service;

import com.commonts.Dto.NewsEventsDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.bundel.BundleManager;
import com.commonts.exeption.HamisheBaharException;
import com.commonts.utils.StringUtils;
import com.hamishebahar.panel.news_events.entity.Events;
import com.hamishebahar.panel.news_events.repository.NewsEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.id;

import static com.commonts.utils.VerifyObjectUtils.isNewEvents;

@Service
public class NewsEventsService {
    private final NewsEventsRepository newsEventsRepository;

    @Autowired
    public NewsEventsService(NewsEventsRepository newsEventsRepository) {
        this.newsEventsRepository = newsEventsRepository;
    }

    public ResultsServiceDto insertNews(NewsEventsDto dto) throws HamisheBaharException {
        if (!isNewEvents(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getTitle() == null || dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            NewsEventsDto newsEventsDto = newsEventsRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Result(newsEventsDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editeNews(NewsEventsDto dto, Long id) throws HamisheBaharException {
        if (isNewEvents(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!StringUtils.hasText(String.valueOf(id))) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        if (dto.getTitle() == null || dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        NewsEventsDto vo = findOneByid(id);
        NewsEventsDto NewsEventsDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                NewsEventsDto = newsEventsRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Result(NewsEventsDto).build();
    }

    public ResultsServiceDto deleteNews(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder().Result(null).Status(HttpStatus.BAD_REQUEST).build();
        if (!StringUtils.hasText(String.valueOf(id))) {
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
            if (StringUtils.hasText(String.valueOf(id))) {
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
            if (!StringUtils.isNullOrEmpty(String.valueOf(id))) {
                NewsEventsDto = newsEventsRepository.getById(id).convertToDto();
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
            ).build();
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
                        .Result(newsEventsRepository.findALL(startTime,endTime,pageable)
                                .map(Events::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(startTime)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(newsEventsRepository.findALL(startTime,pageable)
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
