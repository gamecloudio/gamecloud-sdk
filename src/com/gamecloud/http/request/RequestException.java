package com.gamecloud.http.request;

/**
 * @author mateusz
 */
public class RequestException extends Exception {

    private static final long serialVersionUID = 1L;

    public RequestException(String msg) {
        super(msg);
    }
}
