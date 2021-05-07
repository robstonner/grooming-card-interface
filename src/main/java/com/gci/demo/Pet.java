package com.gci.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object representing pet information for a client.
 */
public class Pet {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("clientId")
    private Integer clientId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("breed")
    private String breed;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("vaccinated")
    private Boolean vaccinated;
    @JsonProperty("medicalNotes")
    private String medicalNotes;

    public Pet() {
        // no args contructor
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isVaccinated() {
        return this.vaccinated;
    }

    public Boolean getVaccinated() {
        return this.vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getMedicalNotes() {
        return this.medicalNotes;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

}
