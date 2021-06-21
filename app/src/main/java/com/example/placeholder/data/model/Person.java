package com.example.placeholder.data.model;

public class Person {
    private String name;
    private String nickName;
    private String email;
    private String password;

    public Person() {}

    public Person(String name, String nickName, String email, String password){
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
