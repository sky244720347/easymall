package com.easymall.exception;

public class MsgException extends Exception {
    public MsgException() {

    }

    public MsgException(String msg) {
        super(msg);
    }

    public MsgException(Exception e) {
        super(e);
    }
}