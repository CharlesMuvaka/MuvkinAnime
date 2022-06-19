package com.example.muvkinanim.models;

public class User {

    private String uid;
    private String name;
    private String phone;
    private String email;

    public User(){

    }

    public User(String uid, String name, String phone, String email) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
