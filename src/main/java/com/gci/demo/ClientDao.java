package com.gci.demo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(ClientDao.class);

    public ClientDao() {
        // no args constructor
    }

    public void createClient(Client client) {
        String sql = "INSERT INTO client (name, phone_number, phone_number_alt, address_id) VALUES (?, ?, ?, ?)";
         
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2, client.getPhoneNumber());
            ps.setString(3, client.getPhoneNumberAlt());
            ps.setInt(4, client.getAddress().getId());
            return ps;
        }, keyHolder);

        client.setId((int) keyHolder.getKeys().get("id")); 
    }

    public void createAddress(Address address) {
        String sql = "INSERT INTO address (line1, line2, city, state, zip_code) VALUES (?, ?, ?, ?, ?)";
         
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getLine1());
            ps.setString(2, address.getLine2());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getState());
            ps.setString(5, address.getZipCode());
            return ps;
        }, keyHolder);

        address.setId((int) keyHolder.getKeys().get("id")); 
    }

    public void createPet(Pet pet) {
        String sql = "INSERT INTO pet (name, breed, age, vaccinated, medical_notes, client_id) VALUES (?, ?, ?, ?, ?, ?)";
         
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getBreed());
            ps.setInt(3, pet.getAge());
            ps.setBoolean(4, pet.getVaccinated());
            ps.setString(5, pet.getMedicalNotes());
            ps.setInt(6, pet.getClientId());
            return ps;
        }, keyHolder);

        pet.setId((int) keyHolder.getKeys().get("id")); 
    }

    public void createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (date, time, description, client_id) VALUES (?, ?, ?, ?)";
         
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(appointment.getDate()));
            ps.setTime(2, Time.valueOf(appointment.getTime()));
            ps.setString(3, appointment.getDescription());
            ps.setInt(4, appointment.getClientId());
            return ps;
        }, keyHolder);

        appointment.setId((int) keyHolder.getKeys().get("id")); 
    }

    public List<Client> getAllClients() {
        String sql = "SELECT client.id id, name, phone_number, phone_number_alt, " 
                + "address_id, line1, line2, city, state, zip_code "
                + "FROM client JOIN address ON client.address_id = address.id";
        
        List<Client> clientList = jdbcTemplate.query(sql, new ClientMapper());

        for (Client client : clientList) {
            client.setPets(getAllPetsForClient(client.getId()));
            client.setAppointments(getAllAppointmentsForClient(client.getId()));
        }

        return clientList;
    }

    public List<Pet> getAllPetsForClient(Integer clientId) {
        String sql = "SELECT * FROM pet WHERE client_id = " + clientId;

        return jdbcTemplate.query(sql, new PetMapper());
    }

    public List<Appointment> getAllAppointmentsForClient(Integer clientId) {
        String sql = "SELECT * FROM appointment WHERE client_id = " + clientId;

        return jdbcTemplate.query(sql, new AppointmentMapper());
    }
}