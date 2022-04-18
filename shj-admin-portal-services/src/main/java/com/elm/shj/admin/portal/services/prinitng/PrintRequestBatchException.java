package com.elm.shj.admin.portal.services.prinitng;

public class PrintRequestBatchException extends RuntimeException{
    public PrintRequestBatchException() {
        super();
    }

    public PrintRequestBatchException(String message) {
        super(message);
    }

    public PrintRequestBatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrintRequestBatchException(Throwable cause) {
        super(cause);
    }

    protected PrintRequestBatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
