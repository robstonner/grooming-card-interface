package com.gci.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @GetMapping
    @RequestMapping("/getallclients")
    @ResponseBody
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    @PostMapping
    @RequestMapping("/addclient")
    @ResponseBody
    public void addClient(@RequestBody Client client) {
        clientDao.createAddress(client.getAddress());
        clientDao.createClient(client);
        if (client.getPets() != null && !client.getPets().isEmpty()) {
            for (Pet pet : client.getPets()) {
                pet.setClientId(client.getId());
                clientDao.createPet(pet);
            }
        }
        if (client.getAppointments() != null && !client.getAppointments().isEmpty()) {
            for (Appointment appointment : client.getAppointments()) {
                appointment.setClientId(client.getId());
                clientDao.createAppointment(appointment);
            }
        }
    }

    @PostMapping
    @RequestMapping("/addappointment")
    @ResponseBody
    public void addAppointment(@RequestBody Appointment appointment) {
        clientDao.createAppointment(appointment);
    }

    @PostMapping
    @RequestMapping("/addpet")
    @ResponseBody
    public void addPet(@RequestBody Pet pet) {
        clientDao.createPet(pet);
    }
}
