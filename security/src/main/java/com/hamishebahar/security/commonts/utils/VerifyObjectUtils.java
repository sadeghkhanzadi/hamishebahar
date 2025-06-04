package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.exeption.HamisheBaharException;

public class VerifyObjectUtils {
    public static <T> T requireNonNull(T obj, String entryType) throws HamisheBaharException {
        if (obj == null || obj == "")
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    "یکی از پارامتر های ورودی خالی میباشد.");
        return obj;
    }
}
