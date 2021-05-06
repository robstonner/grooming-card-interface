package com.gci.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AppointmentMapper implements RowMapper<Appointment> {
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setClientId(rs.getInt("client_id"));
        appointment.setDate(rs.getDate("date").toLocalDate());
        appointment.setTime(rs.getTime("time").toLocalTime());
        appointment.setDescription(rs.getString("description"));

        return appointment;
     }
}