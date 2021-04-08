package com.gci.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class DemoResource {

    @GetMapping
    @RequestMapping("/")
    public String demoGet() {
        return "Hello World";
    }

    @GetMapping
    @RequestMapping("/object/")
    public Client demoObject() {
        Client client = new Client();
        client.setName("Robert");
        client.setPhoneNumber("6604736583");
        return client;
    }

    @PostMapping
    @RequestMapping("/post/")
    public String demoPost(@RequestBody Client client) {
        return "Hello " + client.getName() + "! I can call you at " + client.getPhoneNumber() + ".";
    }
}