package com.paractice.rest.webservices.restfulwebservices.helloworld;

public class HelloWorlBean {


    private String message;
    public HelloWorlBean(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorlBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
