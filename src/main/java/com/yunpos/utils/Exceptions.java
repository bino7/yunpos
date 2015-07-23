package com.yunpos.utils;

public class Exceptions {
    public static RuntimeException unchecked(Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }
}
