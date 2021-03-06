package com.example.coffeetime.model;

import com.example.coffeetime.common.Constants;

import static com.example.coffeetime.common.Constants.DEFAULT_PROFILE;
import static com.example.coffeetime.common.Constants.DEFAULT_QR;
import static com.example.coffeetime.common.Constants.ROL_USER;

public class User {
    String name;
    String lastName;
    String email;
    String phone;
    String dateBirth;
    String photoUri;
    String qrUser;
    String rol;

    public User() {
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.dateBirth = "";
        this.photoUri = DEFAULT_PROFILE;
        this.qrUser = DEFAULT_QR;
        this.rol = ROL_USER;
    }

    public User(String name, String lastName, String email, String phone, String dateBirth, String photoUri, String qrUser) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.photoUri = photoUri;
        this.qrUser = qrUser;
        this.rol = ROL_USER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public String getQrUser() {
        return qrUser;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setQrUser(String qrUser) {
        this.qrUser = qrUser;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
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
