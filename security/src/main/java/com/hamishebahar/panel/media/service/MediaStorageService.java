package com.hamishebahar.panel.media.service;

import com.commonts.Dto.MediasDto;
import com.commonts.Dto.ResultsServiceDto;
import com.commonts.Enums.MediaStates;
import com.commonts.bundel.BundleManager;
import com.commonts.exeption.HamisheBaharException;
import com.commonts.utils.StringUtils;
import com.hamishebahar.panel.media.entity.Medias;
import com.hamishebahar.panel.media.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static com.commonts.utils.VerifyObjectUtils.isNewMedia;


@Service
public class MediaStorageService {
    @Value(value = "${file.upload.dir}")
    String fileUploadDir;
    private final MediaRepository mediaRepository;

    @Autowired
    public MediaStorageService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    public String saveFile(MultipartFile file) throws HamisheBaharException {
        try {
            String PATH = ResourceUtils.getFile(fileUploadDir).getAbsolutePath();
            byte[] bytes = file.getBytes();
            String name = UUID.randomUUID() + "." + Objects.requireNonNull(file.getContentType()).split("/")[1];
            Files.write(Paths.get(PATH + File.separator + name), bytes);
            return name;
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new HamisheBaharException(HamisheBaharException.IS_EXISTS_FILE, "Could not upload the file: "
                        + file.getOriginalFilename() +
                        ". Error:  A file of that name already exists.");
            }
            throw new HamisheBaharException(HamisheBaharException.SERVER_ERROR, e.getMessage());
        }
    }

    public ResultsServiceDto insertFile(MediasDto dto) throws HamisheBaharException {
        if (!isNewMedia(dto)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (dto.getFile().isEmpty() || dto.getName() == null || dto.getStates() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            dto.setPathFile(saveFile(dto.getFile()));
            MediasDto mediasDto = mediaRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Result(mediasDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editMedia(MediasDto dto, Long id) throws HamisheBaharException {
        if (isNewMedia(dto)) {
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
        if (!dto.getId().equals(id) ||
                dto.getName() == null ||
                dto.getPathFile() == null ||
                dto.getStates() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        MediasDto vo = findOneById(id);
        MediasDto MediasDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                MediasDto = mediaRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Result(MediasDto).build();
    }

    public ResultsServiceDto deleteMedia(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (!StringUtils.hasText(String.valueOf(id))) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        MediasDto dto = findOneById(id);
        if (dto != null) {
            try {
                dto.setIs_deleted(true);
                dto.setIs_active(false);
                mediaRepository.save(dto.convertToEntity());
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

    public ResultsServiceDto findMedia(Long id, String name, MediaStates states, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            if (StringUtils.hasText(String.valueOf(id))) {
                if (!isExists(id)) {
                    throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                            BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
                }
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(findOneById(id))
                        .Status(HttpStatus.OK)
                        .build();
            } else {
                resultsServiceDto = findALL(name, states, pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public MediasDto findOneById(Long id) throws HamisheBaharException {
        try {
            MediasDto MediasDto = null;
            if (!StringUtils.isNullOrEmpty(String.valueOf(id))) {
                MediasDto = mediaRepository.getById(id).convertToDto();
            }
            return MediasDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            mediaRepository.findAll(pageable)
                                    .map(Medias::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findALL(String name, MediaStates states, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            if (StringUtils.hasText(name) && StringUtils.hasText(states.name())){
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByNameAndState(name, states.name(), pageable)
                                .map(Medias::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(name)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByName(name, pageable)
                                .map(Medias::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(states.name())) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByState(states.name(), pageable)
                                .map(Medias::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else { //all of
                resultsServiceDto = findALL(pageable);
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
                return this.mediaRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }
}