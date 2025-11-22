package com.skillmentor.service.entities;

import lombok.Data;

@Data
public class Mentor {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String title;
    private String profession;
    private String company;
    private int experienceYears;
    private String bio;
}
