package com.example.niteshpractice.internoutput.dto;


import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;


public class PersonDTO {
    private long id;
    private UUID uuid = UUID.randomUUID();
    private String firstname;
    private String lastname;
    private String primaryEmail;
    private String secondaryEmail;
    private String contact;
    private String education;
    private String password;
    private LocalDate created_at;
    private String status;
//    private String profile_type;
//    private boolean isExpand;

    public PersonDTO() {}

    public PersonDTO(long id, UUID uuid, String firstname, String lastname,
                     String primaryEmail, String secondaryEmail, String contact, String education,
                     String password, LocalDate created_at, String status) {
        this.id = id;
        this.firstname = firstname;
        this.uuid = uuid;
        this.lastname = lastname;
        this.primaryEmail = primaryEmail;
        this.secondaryEmail = secondaryEmail;
        this.contact = contact;
        this.education = education;
        this.password = password;
        this.created_at = created_at;
        this.status = status;
//        this.isExpand = isExpand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public boolean isExpand() {
//        return isExpand;
//    }
//
//    public void setExpand(boolean expand) {
//        isExpand = expand;
//    }
}

