package com.paractice.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello world";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorlBean helloWorldBean() {

        return  new HelloWorlBean("Hello world");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorlBean helloWorldPathVariable(@PathVariable String name) {

        return  new HelloWorlBean(String.format("Hello world, %s", name));
    }

}

