package com.gci.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PetMapper implements RowMapper<Pet> {
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getInt("id"));
        pet.setClientId(rs.getInt("client_id"));
        pet.setName(rs.getString("name"));
        pet.setBreed(rs.getString("breed"));
        pet.setAge(rs.getInt("age"));
        pet.setVaccinated(rs.getBoolean("vaccinated"));
        pet.setMedicalNotes(rs.getString("medical_notes"));

        return pet;
     }
}
