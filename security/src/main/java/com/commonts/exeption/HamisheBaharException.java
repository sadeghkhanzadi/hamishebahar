package com.commonts.exeption;

import lombok.Data;

@Data
public class HamisheBaharException extends Exception {
    //FreakException.PARAMETER_ABSENT, BundleManager.wrapKey("error.param.is.empty", entryType)

    public static final int TIMEOUT = 408;
    private static final long serialVersionUID = 3340591057214373572L;
    public static int NO_ERROR = 0;
    public static int UNKNOWN_ERROR = 999;
    public static int PROCESS_REQUEST_ERROR = 998;
    public static int INVALID_SERVICE = 997;
    public static int CONCURRENT_MODIFICATION = 996;
    public static int INVALID_IDENTIFIER = 1;
    public static int SERVICE_UNAVAILABLE = 2;
    public static int INVALID_USERNAME_PASSWORD = 3;
    public static int PERMISSION_DENIED = 4;
    public static int USER_PROFILE_NOT_COMPLETED = 5;
    public static int INVALID_NICKNAME = 6;
    public static int DUPLICATE_NICKNAME = 7;
    public static int LOGIN_FAILED = 8;
    public static int CHANGE_PASSWORD_FAILED = 9;
    public static int INVALID_DATE = 10;
    public static int ACCESS_DENIED = 11;
    public static int EMPTY_ID = 12;
    public static int INVALID_TOKEN = 21;
    public static int INVALID_FORMAT = 31;
    public static int ERROR_UNEXPECTED_VALUE = 32;
    public static int SSO_COMMUNICATION = 33;
    public static int USER_NOT_REGISTERED = 34;
    public static int SSO_USER_NOT_CREATED = 35;
    public static int INVALID_ELEMENT_CODE = 37;
    public static int CORRUPTED_JSON = 38;
    public static int REPLAY_ATTACK = 39;
    public static int TOKEN_NOT_AUTHORIZED = 40;
    public static int MESSAGE_IS_EXPIRED = 42;
    public static int DUPLICATE_USER = 44;
    public static int SSO_USER_NOT_UPDATED = 68;
    public static int SSO_INVALID_CLIENT_DETAILS = 69;
    public static int TOO_MANY_CONNECTION = 76;
    public static int OBJECT_NOT_FOUND = 78;
    public static int REQUIRE_NON_NULL = 79;
    public static int INVALID_LOCATION_COORDINATES = 88;
    public static int DATA_CONFLICT = 93;
    public static int INVALID_SECRET_CODE = 95;
    public static int INVALID_REQUEST = 101;
    public static int SSO_CLIENT_NOT_CREATED = 102;
    public static int INVALID_DATE_FORMAT = 103;
    public static int INVALID_ELEMENT_ID = 129;
    public static int INVALID_SIGNATURE = 130;
    public static int INVALID_NUMBER = 141;
    public static int INVALID_REQUEST_PARAMETER = 194;
    public static int CAN_NOT_CALL_MULTI = 197;
    public static int DUPLICATE_INFO = 222;
    public static int SIGNATURE_USER_MISMATCH = 243;
    public static int INSUFFICIENT_TOKEN_SCOPE = 245;
    public static int SSO_SOCIAL_CONNECTION_NOT_LINKED = 251;
    public static int VALUE_TOO_LARGE = 296;
    public static int LIMIT_REQUEST_BY_IP_ADDRESS = 300;
    public static int THINGS_OPERATION_EXCEPTION = 333;
    public static int TEMPORARY_DELETED = 338;
    public static int ACL_ERROR = 339;

    public static int PARAMETER_ABSENT = 340;
    public static int PARENT_NOT_FOUND = 341;
    public static int RESOURCE_NOT_FOUND = 342;
    public static int ACCESS_NAME_NOT_FOUND = 343;
    public static int RESOURCE_HOLDER_NOT_FOUND = 344;
    public static int SERVER_ERROR = 345;
    public static int NOT_FOUND = 346;
    public static int CONFLICT = 347;
    public static int SSO_COMMUNICATION_ERROR = 348;
    public static int UNAUTHORIZED = 349;
    public static int INVALID_CLIENT = 350;
    public static int INVALID_PARAMETER = 351;
    public static int NULL_POINTER_EXCEPTION = 352;
    public static int DATABASE_EXCEPTION = 353;
    public static int TOO_MANY_REQUESTS = 354;
    public static int IS_EXISTS_FILE = 355;

    private String error, error_description, error_uri, state;
    private int http_status_code;
    private int aclErrorCode;

    protected int code;
    protected Object object;
    private Object errorObject;
    private String referenceNumber;

    public HamisheBaharException(int code) {
        super();
        this.code = code;
    }

    public HamisheBaharException(int code, String message) {
        super(message);
        this.code = code;
    }

    public HamisheBaharException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public HamisheBaharException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public HamisheBaharException(int code, String message, Object object) {
        super(message);
        this.code = code;
        this.object = object;
    }

    public HamisheBaharException(int code, String message, Throwable cause, Object object) {
        super(message, cause);
        this.code = code;
        this.object = object;
    }

    public HamisheBaharException(int code, Throwable cause, Object object) {
        super(cause);
        this.code = code;
        this.object = object;
    }

    protected HamisheBaharException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

}
