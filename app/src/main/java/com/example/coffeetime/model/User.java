package com.example.coffeetime.model;

public class User {
    String name;
    String lastName;
    String email;
    String phone;
    String dateBirth;

    public User() {
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.dateBirth = "";
    }

    public User(String name, String lastName, String email, String phone, String dateBirth) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}
