package com.biplav.projectmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;
    private String username;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    public AppUser() {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public AppUser(int userid,
                   String name,
                   String username,
                   String password,
                   String role,
                   List<Task> tasks) {

        this.userid = userid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
    }
}
