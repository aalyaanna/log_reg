package com.example.log_reg;

public class HelperClass {
    String username, phone, email, address, password;

    public String setUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String username, String phone, String email, String address, String password) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public HelperClass() {
    }
}
