package com.biplav.projectmanager.dto;

public class AppUserResponse {
    private int userid;
    private String name;
    private String username;
    private String role;

    public AppUserResponse(int userid, String name, String username, String role) {
        this.userid = userid;
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
