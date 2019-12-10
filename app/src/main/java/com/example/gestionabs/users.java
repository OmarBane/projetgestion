package com.example.gestionabs;

public class users {
    String  userid;
    String  email;
    String  password;
    String  type;

    public users(){

    }

    public users(String userid, String email, String password, String type) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
