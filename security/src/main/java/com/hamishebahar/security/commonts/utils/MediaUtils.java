package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.media.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MediaUtils {
    private final MediaStorageService mediaStorageService;

    @Autowired
    public MediaUtils(MediaStorageService mediaStorageService) {
        this.mediaStorageService = mediaStorageService;
    }

    public List<MediasDto> findMedia(List<MediasDto> medias) throws HamisheBaharException {
        return mediaStorageService.findAll(
                medias.stream().map(MediasDto::getId).collect(Collectors.toList())
        );
    }
}
