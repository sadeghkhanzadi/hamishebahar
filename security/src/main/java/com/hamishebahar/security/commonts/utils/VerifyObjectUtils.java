package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.Dto.*;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;

public class VerifyObjectUtils {
    public static <T> T requireNonNull(T obj, String entryType) throws HamisheBaharException {
        if (obj == null || obj == "")
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        return obj;
    }
}
