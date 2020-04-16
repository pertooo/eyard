package prt.navitruck.back.app.utils;

import java.io.File;

public class Constants {

    public static final String empty_string = "";

    public static final class UploadHelpers {
        private static final String HOME = String.format("%s%s", System.getProperty("user.home"), File.separator);
        public static final String UPLOADS = String.format("%s%s%s", HOME, "uploads", File.separator);
        public static final String USER_IMG = String.format("%s%s", UPLOADS, "user");
    }

    public static final class ErrorCodes {
        public class ErrorResponse {
            public static final String ACCESS_IS_DENIED = "access_is_denied";
            public static final String UNKNOWN = "unknown";
            public static final String DUPLICATE_RECORD = "DUPLICATE_RECORD";
            public static final String RECORD_IS_USED_IN_OTHER_TABLES = "RECORD_IS_USED_IN_OTHER_TABLES";
            public static final String PERSISTENCE_EXCEPTION = "javax.persistence.PersistenceException";
        }
    }

    public enum ExceptionType{
        GLOBAL_RUNTIME,
        HTTP_REQUEST
    }

    public enum TaskStatusObj{

        ASSIGNED(1),
        ASSIGNED_TO_OTHER(2),
        NOT_ASSIGNED(3);

        private int statusCode;
        TaskStatusObj(int statusCode) {
            this.statusCode = statusCode;
        }

    }

    public enum ErrorObj {
        NONE(0),
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500),
        INVALID_USERNAME_OR_PASSWORD(1000),
        ALL_FIELDS_ARE_REQUIRED(1001),
        USERNAME_ALREADY_EXISTS(1002),
        VALIDATION_ERROR(1003),
        SOMETHING_WENT_WRONG(1004),
        INVALID_2FA_CODE(1005),
        GOOGLE_2FA_REQUIRED(1006),
        OTP_2FA_REQUIRED(1007),
        TOO_MANY_SMS_REQUESTS(1008),
        INVALID_USERNAME(1009),
        PASSWORD_NOT_EQUAL(1010),
        INVALID_OTP_CODE(1011),
        SESSION_EXPIRED(1012),
        INVALID_TOKEN(1013),
        ALREADY_EXISTS(1014),
        INVALID_PARAMS(1015),
        NO_SYSTEM_FOUND(1016),
        NO_SUCH_TABLE(1017),
        TABLE_IS_DEACTIVATED(1018),
        CUSTOMER_NOT_FOUND(1019),
        ORG_NOT_FOUND(1020),
        INVALID_PACKAGE_NAME(1021),
        ORGANISATION_HAS_NO_USERS(1022),
        OUT_OF_STOCK(1023),
        NO_PAYMENT_RECEIVED(1024),
        NO_PRODUCTS_RECEIVED(1025),
        NOT_ENOUGH_POINTS(1026),
        NOT_VALID_MONEY(1027),
        NOT_ENOUGH_MONEY(1028);

        //TODO: describe all needed error codes

        private int statusCode;
        private String msg;

        ErrorObj(int statusCode) {
            this.statusCode = statusCode;
        }

    }

    public enum QueryOperation{
        IN,
        NOT_IN,
        NOT_EQUAL,
        OR,
        EQUAL,
        SEARCH_EXPRESSION,
        LIKE,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        BETWEEN
    }

    public static final class ControllerCodes {
        public static final String STRING_EMPTY = "";
        public static final String PAGE_NUMBER = "page";
        public static final String PAGE_NUMBER_DEFAULT_VALUE = "0";
        public static final String PAGE_SIZE_DEFAULT_VALUE = "10";
        public static final String IS_ASCENDING_DEFAULT_VALUE = "true";
        public static final String DEFAULT_SORT_BY = "id";

        public static final String SLASH = "/";
        public static final String LIST = "list";
        public static final String LAYOUT = "layout";
        public static final String PUT = "put";
        public static final String DELETE = "delete";
        public static final String UPDATE = "update";
        public static final String KEY = "key";
        public static final String VALUE = "value";
    }



}
