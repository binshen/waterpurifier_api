package com.waterpurifier.model;

import java.io.Serializable;

/**
 * Created by bin.shen on 11/12/2016.
 */
public class Result implements Serializable {

    public int code;
    public String message;
    public Serializable content;

    public Result(int code, String message, Serializable content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}
