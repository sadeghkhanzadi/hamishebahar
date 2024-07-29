package com.hamishebahar.security.panel.media.service;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Enums.MediaStates;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.commonts.utils.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import com.hamishebahar.security.commonts.utils.VerifyObjectUtils;
import com.hamishebahar.security.panel.media.entity.Medias;
import com.hamishebahar.security.panel.media.repository.MediaRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



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
        if (dto.getId() != null) {
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
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(mediasDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto editMedia(MediasDto dto, Long id) throws HamisheBaharException {
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
        if (dto.getName() == null ||
                dto.getStates() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (dto.getFile() != null){
            dto.setPathFile(saveFile(dto.getFile()));
        }
        MediasDto vo = findOneById(id);
        MediasDto mediasDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                mediasDto = mediaRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(mediasDto).build();
    }

    public ResultsServiceDto deleteMedia(Long id) throws HamisheBaharException {
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
            if (id != null) {
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
            MediasDto mediasDto = null;
            if (id != null) {
                mediasDto = mediaRepository.getOne(id).convertToDto();
            }
            return mediasDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public List<MediasDto> findAll(List<Long> mediaIds) throws HamisheBaharException{
        try {
            List<MediasDto> medias = new ArrayList<>();
            for (Long id : mediaIds){
                medias.add(findOneById(id));
            }
            return medias;
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
            if (StringUtils.hasText(name) && states != null){
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByNameAndStates(name, states, pageable)
                                .map(Medias::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(name)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByName(name, pageable)
                                .map(Medias::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (states != null) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(mediaRepository.findAllByStates(states, pageable)
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

    public Resource findFile(String fileName) {
        File dir = new File(fileUploadDir + fileName);
        try {
            if (dir.exists()) {
                return new UrlResource(dir.toURI());
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public ResultsServiceDto downloadFile(String path) {
        Resource file = findFile(path);
        return new ResultsServiceDto.Builder()
                .Result(file)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
    }
}