package com.gci.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A collections of demo REST endpoints to test/learn Spring functionality
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private ClientDao clientDao;

    /**
     * Demo REST GET endpoint that returns a string
     * 
     * @return a string
     */
    @GetMapping
    @RequestMapping("/")
    @ResponseBody
    public String demoGet() {
        return "Hello World";
    }

    /**
     * Demo REST GET endpoint that returns a JSON object
     * 
     * @return a JSON {{@link Client}} object
     */
    @GetMapping
    @RequestMapping("/object")
    @ResponseBody
    public Client demoObject() {
        Client client = new Client();
        client.setName("Robert");
        client.setPhoneNumber("6604736583");
        Address address = new Address();
        address.setLine1("1001 E Madison St");
        address.setLine2("");
        address.setCity("Springfield");
        address.setState("MO");
        address.setZipCode("65802");
        client.setAddress(address);
        return client;
    }

    /**
     * Demo REST POST endpoint that takes a {{@link Client}} 
     * object and returns a string
     * 
     * @param client a {{@link Client}}
     * @return a string
     */
    @PostMapping
    @RequestMapping("/post")
    @ResponseBody
    public String demoPost(@RequestBody Client client) {
        clientDao.createClient(client);
        return "Hello " + client.getName() + "! I can call you at " + client.getPhoneNumber() + ".";
    }
}