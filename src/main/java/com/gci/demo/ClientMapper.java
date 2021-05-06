package com.gci.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientMapper implements RowMapper<Client> {
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        address.setLine1(rs.getString("line1"));
        address.setLine2(rs.getString("line2"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setZipCode(rs.getString("zip_code"));

        client.setId(rs.getInt("id"));
        client.setName(rs.getString("name"));
        client.setPhoneNumber(rs.getString("phone_number"));
        client.setPhoneNumberAlt(rs.getString("phone_number_alt"));
        client.setAddress(address);
        return client;
     }
}
