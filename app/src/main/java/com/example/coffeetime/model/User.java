package com.example.coffeetime.model;

public class User {
    String uid;
    String name;
    String lastName;
    String email;
    String phone;
    String dateBirth;

    public User() {
        this.uid = "";
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.dateBirth = "";
    }

    public User(String uid ,String name, String lastName, String email, String phone, String dateBirth) {
        this.uid = uid;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
