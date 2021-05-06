package com.gci.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object representing an individual client grooming card.
 */
public class Client {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("phoneNumberAlt")
    private String phoneNumberAlt;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("pets")
    private List<Pet> pets;
    @JsonProperty("appointments")
    private List<Appointment> appointments;

    public Client() {
        // no args constructor
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberAlt() {
        return this.phoneNumberAlt;
    }

    public void setPhoneNumberAlt(String phoneNumberAlt) {
        this.phoneNumberAlt = phoneNumberAlt;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
