package com.adam.exception;

/**
 * @author AdamSun
 * @date 2020/8/14 21:45
 */
public class DataAbnormalException extends AppException {

    public DataAbnormalException() {
    }

    public DataAbnormalException(String message) {
        super(message);
    }

    public DataAbnormalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAbnormalException(Throwable cause) {
        super(cause);
    }

    public DataAbnormalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
