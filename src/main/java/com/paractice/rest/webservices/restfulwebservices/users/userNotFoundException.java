package com.paractice.rest.webservices.restfulwebservices.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class userNotFoundException extends RuntimeException {
    public userNotFoundException(String message) {

        super(message);
    }
}

 class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {

        super(message);
    }
}
