package com.gci.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientDao() {
        // no args constructor
    }

    public void createClient(Client client) {
        String sql = "INSERT INTO client (name, phone_number) VALUES ('"
                + client.getName() + "', '"
                + client.getPhoneNumber() + "')";
         
        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
    }

    public List<Client> getAllClients() {
        jdbcTemplate.queryForList("");
        return null;
    }
}