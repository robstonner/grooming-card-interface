package com.gci.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object representing a grooming appointment for a client.
 */
public class Appointment {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("client_id")
    private Integer clientId;
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime time;
    @JsonProperty("description")
    private String description;

    public Appointment() {
        // no args constructor
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
