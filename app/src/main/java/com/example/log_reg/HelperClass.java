package com.example.log_reg;

public class HelperClass {
    String email, phone, number, address, password;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public HelperClass(String email, String phone, String number, String address, String password) {
        this.email = email;
        this.phone = phone;
        this.number = number;
        this.address = address;
        this.password = password;
    }

    public HelperClass() {
    }
}
