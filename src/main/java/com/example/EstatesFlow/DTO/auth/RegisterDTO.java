package com.example.EstatesFlow.DTO.auth;



public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    public RegisterDTO(String fistName, String lastName, String email , String phoneNumber  , String password) {
        this.firstName = fistName;
        this.lastName  = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
