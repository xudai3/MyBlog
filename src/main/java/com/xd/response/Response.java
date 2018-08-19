package com.xd.response;

import java.io.Serializable;

public class Response implements Serializable{
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Integer code;
    private String message;
    private Object data;

    public Response() {}

    public Response(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Response success(){
//        Response resp = new Response();
//        resp.setStatusCode(StatusCode.SUCCESS);
        setStatusCode(StatusCode.SUCCESS);
        return this;
    }

    public Response success(Object data){
        setStatusCode(StatusCode.SUCCESS);
        this.data = data;
        return this;
    }

    public Response failure(StatusCode statusCode){
        setStatusCode(statusCode);
        return this;
    }

    public Response failure(StatusCode statusCode, Object data){
        setStatusCode(statusCode);
        this.data = data;
        return this;
    }

    public void setStatusCode(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public Object getData(){
        return data;
    }

}
