package com.gci.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class DemoResource {

    @GetMapping
    @RequestMapping("/")
    public String demo() {
        return "Hello World";
    }

    @GetMapping
    @RequestMapping("/object")
    public Client demoObject() {
        Client client = new Client();
        client.setName("Robert");
        client.setPhoneNumber("6604736583");
        return client;
    }
}