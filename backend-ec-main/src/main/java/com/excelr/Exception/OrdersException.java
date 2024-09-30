package com.excelr.Exception;

public class OrdersException extends RuntimeException {
    public OrdersException(String message) {
        super(message);
    }

    public OrdersException(String message, Throwable cause) {
        super(message, cause);
    }
}
