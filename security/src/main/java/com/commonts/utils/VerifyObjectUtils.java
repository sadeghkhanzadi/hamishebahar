package com.commonts.utils;

import com.commonts.Dto.NewsEventsDto;
import com.commonts.bundel.BundleManager;
import com.commonts.exeption.HamisheBaharException;

public class VerifyObjectUtils {
    public static <T> T requireNonNull(T obj, String entryType) throws HamisheBaharException {
        if (obj == null || obj == "")
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER, BundleManager.wrapKey("error.parameter.is.null"));
        return obj;
    }

    public static boolean isNewEvents(NewsEventsDto dto) throws HamisheBaharException {
        if (dto != null){
            return StringUtils.isNullOrEmpty(String.valueOf(dto.getId()!= null ? dto.getId() : null));
        } throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER, BundleManager.wrapKey("error.json.model.is.null"));
    }
}
